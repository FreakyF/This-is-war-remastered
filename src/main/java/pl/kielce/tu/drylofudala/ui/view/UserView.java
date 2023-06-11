package pl.kielce.tu.drylofudala.ui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import pl.kielce.tu.drylofudala.persistance.resource.IResourceRepository;
import pl.kielce.tu.drylofudala.ui.UiConfig;
import pl.kielce.tu.drylofudala.ui.UiResource;
import pl.kielce.tu.drylofudala.ui.model.ImagePanel;
import pl.kielce.tu.drylofudala.ui.service.UiComponentCreator;
import pl.kielce.tu.drylofudala.ui.service.navigation_handler.IViewNavigationHandler;
import pl.kielce.tu.drylofudala.ui.view.factory.IView;


public class UserView implements IView {
	private IViewNavigationHandler navigationHandler;
	private UiComponentCreator uiComponentCreator;

	@Override
	public JPanel createView(IViewNavigationHandler viewNavigationHandler, IResourceRepository resourceRepository) {
		this.navigationHandler = viewNavigationHandler;
		this.uiComponentCreator = new UiComponentCreator(resourceRepository);
		return initializeView();
	}

	private JPanel initializeView() {
		final JPanel view = new JPanel(new BorderLayout());

		ImagePanel backgroundPanel = uiComponentCreator.createBackgroundPanel();
		view.add(backgroundPanel);

		JPanel contentPanel = createContentPanel();
		backgroundPanel.add(contentPanel);

		return view;
	}

	private JPanel createContentPanel() {
		JPanel contentPanel = new JPanel(new GridBagLayout());
		contentPanel.setOpaque(false);


		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);

		JPanel headerPanel = createHeaderPanel();
		gbc.gridx = 0;
		gbc.gridy = 0;
		contentPanel.add(headerPanel, gbc);

		JPanel inputPanel = createInputPanel();
		gbc.gridx = 0;
		gbc.gridy = 1;
		contentPanel.add(inputPanel, gbc);


		return contentPanel;
	}

	private JPanel createHeaderPanel() {
		JPanel headerPanel = new JPanel(new GridBagLayout());
		headerPanel.setOpaque(false);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);

		JLabel titleLabel = uiComponentCreator.createLabel(UiResource.GAME_TITLE, UiConfig.TITLE_FONT);
		gbc.gridx = 0;
		gbc.gridy = 0;
		headerPanel.add(titleLabel, gbc);

		return headerPanel;
	}

	private JPanel createInputPanel() {
		JPanel inputPanel = new JPanel(new GridBagLayout());
		inputPanel.setOpaque(false);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);

		// IP
		JLabel ipLabel = uiComponentCreator.createLabel(UiResource.IP_ADDRESS_TEXT, UiConfig.COPYRIGHT_FONT);
		gbc.gridx = 0;
		gbc.gridy = 0;
		inputPanel.add(ipLabel, gbc);

		// textIP
		JTextField ipField = createIpField();
		gbc.gridx = 0;
		gbc.gridy = 1;
		inputPanel.add(ipField, gbc);

		// port
		JLabel portLabel = uiComponentCreator.createLabel(UiResource.PORT_NUMBER_TEXT, UiConfig.COPYRIGHT_FONT);
		gbc.gridx = 0;
		gbc.gridy = 2;
		inputPanel.add(portLabel, gbc);

		// textPort
		JTextField portField = createPortField();
		gbc.gridx = 0;
		gbc.gridy = 3;
		inputPanel.add(portField, gbc);

		JButton connectButton = uiComponentCreator.createButton(UiResource.BUTTON_CONNECT_TEXT, 300, 100);
		connectButton.addActionListener(navigationHandler.navigateToGameView());
		gbc.gridx = 0;
		gbc.gridy = 4;
		inputPanel.add(connectButton, gbc);

		return inputPanel;
	}

	private JTextField createPortField() {
		JTextField textField = new JTextField();

		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setPreferredSize(new Dimension(300, 100));
		textField.setFont(UiConfig.COPYRIGHT_FONT);
		textField.setBorder(null);

		((AbstractDocument) textField.getDocument()).setDocumentFilter(new NumberOnlyFilter());

		textField.setText("8000");

		return textField;
	}

	private JTextField createIpField() {
		JTextField textField = new JTextField();

		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setPreferredSize(new Dimension(300, 100));
		textField.setFont(UiConfig.COPYRIGHT_FONT);
		textField.setBorder(null);

		((AbstractDocument) textField.getDocument()).setDocumentFilter(new NumberAndDotOnlyFilter());

		textField.setText("127.0.0.1");

		return textField;
	}

	private static class NumberAndDotOnlyFilter extends DocumentFilter {
		private static final int MAX_LENGTH = 15;

		@Override
		public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
			StringBuilder builder = new StringBuilder(text.length());
			for (int i = 0; i < text.length(); i++) {
				char c = text.charAt(i);
				if (Character.isDigit(c) && fb.getDocument().getLength() < MAX_LENGTH) {
					builder.append(c);
				}
			}
			super.insertString(fb, offset, builder.toString(), attr);
		}

		@Override
		public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
			if (text == null) {
				super.replace(fb, offset, length, text, attrs);
				return;
			}
			StringBuilder builder = new StringBuilder(text.length());
			for (int i = 0; i < text.length(); i++) {
				char c = text.charAt(i);
				if ((Character.isDigit(c) || c == '.') && fb.getDocument().getLength() - length + builder.length() < MAX_LENGTH) {
					builder.append(c);
				}
			}
			super.replace(fb, offset, length, builder.toString(), attrs);
		}
	}

	private static class NumberOnlyFilter extends DocumentFilter {
		private static final int MAX_LENGTH = 5;

		@Override
		public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
			StringBuilder builder = new StringBuilder(text.length());
			for (int i = 0; i < text.length(); i++) {
				char c = text.charAt(i);
				if (Character.isDigit(c) && fb.getDocument().getLength() < MAX_LENGTH) {
					builder.append(c);
				}
			}
			super.insertString(fb, offset, builder.toString(), attr);
		}

		@Override
		public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
			if (text == null) {
				super.replace(fb, offset, length, text, attrs);
				return;
			}
			StringBuilder builder = new StringBuilder(text.length());
			for (int i = 0; i < text.length(); i++) {
				char c = text.charAt(i);
				if (Character.isDigit(c) && fb.getDocument().getLength() - length + builder.length() < MAX_LENGTH) {
					builder.append(c);
				}
			}
			super.replace(fb, offset, length, builder.toString(), attrs);
		}
	}
}
