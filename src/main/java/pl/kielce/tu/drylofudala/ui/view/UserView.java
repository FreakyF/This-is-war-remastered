package pl.kielce.tu.drylofudala.ui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import pl.kielce.tu.drylofudala.authentication.service.IAuthenticationService;
import pl.kielce.tu.drylofudala.persistance.resource.IResourceRepository;
import pl.kielce.tu.drylofudala.ui.MainWindow;
import pl.kielce.tu.drylofudala.ui.UiConfig;
import pl.kielce.tu.drylofudala.ui.UiResource;
import pl.kielce.tu.drylofudala.ui.model.ImagePanel;
import pl.kielce.tu.drylofudala.ui.service.navigation_handler.IViewNavigationHandler;
import pl.kielce.tu.drylofudala.ui.service.ui_component_creator.UiComponentCreator;
import pl.kielce.tu.drylofudala.ui.view.factory.IAuthView;

public class UserView implements IAuthView {
	private static final String NAME = "Menu";
	private IViewNavigationHandler navigationHandler;
	private UiComponentCreator uiComponentCreator;
	private MainWindow parentWindow;
	private JPanel view;
	private JTextField ipTextField;
	private JTextField portTextField;

	@Override
	public String getViewName() {
		return NAME;
	}

	@Override
	public JPanel createView(final MainWindow parentWindow, final IAuthenticationService authenticationService, final IViewNavigationHandler navigationHandler, final IResourceRepository resourceRepository) {
		this.parentWindow = parentWindow;
		this.navigationHandler = navigationHandler;
		uiComponentCreator = new UiComponentCreator(resourceRepository);
		return initializeView();
	}

	private JPanel initializeView() {
		view = new JPanel(new BorderLayout());

		final ImagePanel backgroundPanel = uiComponentCreator.createBackgroundPanel();
		view.add(backgroundPanel);

		final JPanel contentPanel = createContentPanel();
		backgroundPanel.add(contentPanel);

		return view;
	}

	private JPanel createContentPanel() {
		final JPanel contentPanel = new JPanel(new GridBagLayout());
		contentPanel.setOpaque(false);

		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);

		final JPanel headerPanel = createHeaderPanel();
		gbc.gridx = 0;
		gbc.gridy = 0;
		contentPanel.add(headerPanel, gbc);

		final JPanel inputPanel = createInputPanel();
		gbc.gridx = 0;
		gbc.gridy = 1;
		contentPanel.add(inputPanel, gbc);

		return contentPanel;
	}

	private JPanel createHeaderPanel() {
		final JPanel headerPanel = new JPanel(new GridBagLayout());
		headerPanel.setOpaque(false);

		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);

		final JLabel titleLabel = uiComponentCreator.createLabel(UiResource.GAME_TITLE, UiConfig.TITLE_FONT);
		gbc.gridx = 0;
		gbc.gridy = 0;
		headerPanel.add(titleLabel, gbc);

		return headerPanel;
	}

	private JPanel createInputPanel() {
		final JPanel inputPanel = new JPanel(new GridBagLayout());
		inputPanel.setOpaque(false);

		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);

		final JLabel ipLabel = uiComponentCreator.createLabel(UiResource.IP_ADDRESS_TEXT, UiConfig.COPYRIGHT_FONT);
		gbc.gridx = 0;
		gbc.gridy = 0;
		inputPanel.add(ipLabel, gbc);

		final JTextField ipField = createIpField();
		gbc.gridx = 0;
		gbc.gridy = 1;
		inputPanel.add(ipField, gbc);

		// port
		final JLabel portLabel = uiComponentCreator.createLabel(UiResource.PORT_NUMBER_TEXT, UiConfig.COPYRIGHT_FONT);
		gbc.gridx = 0;
		gbc.gridy = 2;
		inputPanel.add(portLabel, gbc);

		final JTextField portField = createPortField();
		gbc.gridx = 0;
		gbc.gridy = 3;
		inputPanel.add(portField, gbc);

		final JButton connectButton = uiComponentCreator.createButton(UiResource.BUTTON_CONNECT_TEXT);
		// TODO: Implement check if the server on the IP and PORT provided by user exists.
		connectButton.addActionListener(onLoginButtonClicked());
		gbc.gridx = 0;
		gbc.gridy = 4;
		inputPanel.add(connectButton, gbc);

		return inputPanel;
	}

	private JTextField createPortField() {
		final JTextField textField = new JTextField();

		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setPreferredSize(new Dimension(300, 100));
		textField.setFont(UiConfig.COPYRIGHT_FONT);
		textField.setBorder(null);
		portTextField = textField;

		((AbstractDocument) textField.getDocument()).setDocumentFilter(new NumberOnlyFilter());

		textField.setText("8000");

		return textField;
	}

	private JTextField createIpField() {
		final JTextField textField = new JTextField();

		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setPreferredSize(new Dimension(300, 100));
		textField.setFont(UiConfig.COPYRIGHT_FONT);
		textField.setBorder(null);
		ipTextField = textField;

		((AbstractDocument) textField.getDocument()).setDocumentFilter(new NumberAndDotOnlyFilter());

		textField.setText("127.0.0.1");

		return textField;
	}

	private ActionListener onLoginButtonClicked() {
		return e -> {
			final String enteredIP = ipTextField.getText();
			final String enteredPort = portTextField.getText();
			// TODO: Create a ServerConfig class with SERVER_IP and SERVER_PORT fields;
			final String serverIP = "127.0.0.1";
			final int serverPort = 8000;

			final boolean ipMatch = serverIP.equals(enteredIP);
			final boolean portMatch = Integer.toString(serverPort).equals(enteredPort);

			if (ipMatch && portMatch) {
				navigationHandler.navigateToGameView(parentWindow).actionPerformed(e);
				return;
			}

			final String errorMessage;
			if (!ipMatch && !portMatch) {
				errorMessage = "Incorrect IP address and port!";
			} else if (!ipMatch) {
				errorMessage = "Incorrect IP address";
			} else {
				errorMessage = "Incorrect port!";
			}

			JOptionPane.showMessageDialog(view, errorMessage);
		};
	}

	private static class NumberAndDotOnlyFilter extends DocumentFilter {
		private static final int MAX_LENGTH = 15;

		@Override
		public void insertString(final FilterBypass fb, final int offset, final String string, final AttributeSet attr) throws BadLocationException {
			final StringBuilder builder = new StringBuilder(string.length());
			for (final char c : string.toCharArray()) {
				if (Character.isDigit(c) && fb.getDocument().getLength() < MAX_LENGTH) {
					builder.append(c);
				}
			}
			super.insertString(fb, offset, builder.toString(), attr);
		}

		@Override
		public void replace(final FilterBypass fb, final int offset, final int length, final String text, final AttributeSet attrs) throws BadLocationException {
			if (text == null) {
				super.replace(fb, offset, length, text, attrs);
				return;
			}
			final StringBuilder builder = new StringBuilder(text.length());
			for (int i = 0; i < text.length(); i++) {
				final char c = text.charAt(i);
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
		public void insertString(final FilterBypass fb, final int offset, final String string, final AttributeSet attr) throws BadLocationException {
			final StringBuilder builder = new StringBuilder(string.length());
			for (final char c : string.toCharArray()) {
				if (Character.isDigit(c) && fb.getDocument().getLength() < MAX_LENGTH) {
					builder.append(c);
				}
			}
			super.insertString(fb, offset, builder.toString(), attr);
		}

		@Override
		public void replace(final FilterBypass fb, final int offset, final int length, final String text, final AttributeSet attrs) throws BadLocationException {
			if (text == null) {
				super.replace(fb, offset, length, text, attrs);
				return;
			}
			final StringBuilder builder = new StringBuilder(text.length());
			for (final char c : text.toCharArray()) {
				if (Character.isDigit(c) && fb.getDocument().getLength() - length + builder.length() < MAX_LENGTH) {
					builder.append(c);
				}
			}
			super.replace(fb, offset, length, builder.toString(), attrs);
		}
	}
}
