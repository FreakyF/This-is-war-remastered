package pl.kielce.tu.drylofudala.ui.view;

import pl.kielce.tu.drylofudala.ui.UiConfig;
import pl.kielce.tu.drylofudala.ui.UiResource;
import pl.kielce.tu.drylofudala.ui.model.ImagePanel;
import pl.kielce.tu.drylofudala.ui.service.UiComponentCreator;
import pl.kielce.tu.drylofudala.ui.service.ViewNavigationHandler;
import pl.kielce.tu.drylofudala.ui.view.factory.IView;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class RegisterView implements IView {
	private final ViewNavigationHandler navigationHandler = ViewNavigationHandler.getInstance();

	@Override
	public JPanel createView() {
		return initializeView();
	}

	private JPanel initializeView() {
		final JPanel view = new JPanel(new BorderLayout());

		ImagePanel backgroundPanel = UiComponentCreator.createBackgroundPanel();
		view.add(backgroundPanel);

		JPanel contentPanel = createContentPanel();
		backgroundPanel.add(contentPanel);

		view.setVisible(true);
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

	private JPanel createInputPanel() {
		JPanel inputPanel = new JPanel(new GridBagLayout());
		inputPanel.setOpaque(false);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);

		JPanel nicknamePanel = createNicknamePanel();
		gbc.gridx = 0;
		gbc.gridy = 0;
		inputPanel.add(nicknamePanel, gbc);

		JPanel passwordPanel = createPasswordPanel();
		gbc.gridx = 0;
		gbc.gridy = 1;
		inputPanel.add(passwordPanel, gbc);

		JPanel repeatedPasswordPanel = createPasswordPanel();
		gbc.gridx = 0;
		gbc.gridy = 2;
		inputPanel.add(repeatedPasswordPanel, gbc);

		JPanel buttonPanel = createButtonPanel();
		gbc.gridx = 0;
		gbc.gridy = 3;
		inputPanel.add(buttonPanel, gbc);

		return inputPanel;
	}

	private JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel(new GridBagLayout());
		buttonPanel.setOpaque(false);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);

		JButton registerButton = UiComponentCreator.createButton(UiResource.BUTTON_REGISTER_TEXT, 300, 100);
		registerButton.addActionListener(navigationHandler.navigateToLoginView());
		gbc.gridx = 0;
		gbc.gridy = 0;
		buttonPanel.add(registerButton, gbc);

		return buttonPanel;
	}
	private JPanel createPasswordPanel() {
		JPanel passwordPanel = new JPanel(new GridBagLayout());
		passwordPanel.setOpaque(false);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);

		JLabel passwordLabel = UiComponentCreator.createLabel(UiResource.INPUT_LABEL_PASSWORD, UiConfig.COPYRIGHT_FONT);
		gbc.gridx = 0;
		gbc.gridy = 0;
		passwordPanel.add(passwordLabel, gbc);

		JPasswordField passwordField = createPasswordField();
		gbc.gridx = 0;
		gbc.gridy = 1;
		passwordPanel.add(passwordField, gbc);

		return passwordPanel;
	}

	private JPasswordField createPasswordField() {
		JPasswordField passwordField = new JPasswordField();

		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setPreferredSize(new Dimension(300, 100));
		passwordField.setFont(UiConfig.COPYRIGHT_FONT);
		passwordField.setBorder(null);

		return passwordField;
	}

	private JPanel createNicknamePanel() {
		JPanel nicknamePanel = new JPanel(new GridBagLayout());
		nicknamePanel.setOpaque(false);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);

		JLabel nicknameLabel = UiComponentCreator.createLabel(UiResource.INPUT_LABEL_NICKNAME, UiConfig.COPYRIGHT_FONT);
		gbc.gridx = 0;
		gbc.gridy = 0;
		nicknamePanel.add(nicknameLabel, gbc);

		JTextField nicknameField = createNicknameField();
		gbc.gridx = 0;
		gbc.gridy = 1;
		nicknamePanel.add(nicknameField, gbc);

		return nicknamePanel;
	}

	private JTextField createNicknameField() {
		JTextField textField = new JTextField();

		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setPreferredSize(new Dimension(300, 100));
		textField.setFont(UiConfig.COPYRIGHT_FONT);
		textField.setBorder(null);

		return textField;
	}

	private JPanel createHeaderPanel() {
		JPanel headerPanel = new JPanel(new GridBagLayout());
		headerPanel.setOpaque(false);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);

		JLabel titleLabel = UiComponentCreator.createLabel(UiResource.GAME_TITLE, UiConfig.TITLE_FONT);
		gbc.gridx = 0;
		gbc.gridy = 0;
		headerPanel.add(titleLabel, gbc);

		JLabel subtitleLabel = UiComponentCreator.createLabel(UiResource.SUBTITLE_REGISTER, UiConfig.BUTTON_FONT);
		gbc.gridx = 0;
		gbc.gridy = 1;
		headerPanel.add(subtitleLabel, gbc);

		return headerPanel;
	}
}
