package pl.kielce.tu.drylofudala.ui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import pl.kielce.tu.drylofudala.authentication.service.IAuthenticationService;
import pl.kielce.tu.drylofudala.persistance.resource.IResourceRepository;
import pl.kielce.tu.drylofudala.ui.MainWindow;
import pl.kielce.tu.drylofudala.ui.UiConfig;
import pl.kielce.tu.drylofudala.ui.UiResource;
import pl.kielce.tu.drylofudala.ui.model.ImagePanel;
import pl.kielce.tu.drylofudala.ui.service.navigation_handler.IViewNavigationHandler;
import pl.kielce.tu.drylofudala.ui.service.ui_component_creator.UiComponentCreator;
import pl.kielce.tu.drylofudala.ui.view.factory.IAuthView;

public class RegisterView implements IAuthView {
	private static final String NAME = "Register";
	private IViewNavigationHandler navigationHandler;
	private UiComponentCreator uiComponentCreator;
	private IAuthenticationService authenticationService;

	private JPanel view;
	private JTextField nicknameTextField;
	private JPasswordField passwordTextField;
	private JPasswordField repeatedPasswordTextField;
	private MainWindow parentWindow;

	@Override
	public String getViewName() {
		return NAME;
	}

	@Override
	public JPanel createView(final MainWindow parentWindow, final IAuthenticationService authenticationService, final IViewNavigationHandler navigationHandler, final IResourceRepository resourceRepository) {
		this.parentWindow = parentWindow;
		this.authenticationService = authenticationService;
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

		view.setVisible(true);
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

	private JPanel createInputPanel() {
		final JPanel inputPanel = new JPanel(new GridBagLayout());
		inputPanel.setOpaque(false);

		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);

		final JPanel nicknamePanel = createNicknamePanel();
		gbc.gridx = 0;
		gbc.gridy = 0;
		inputPanel.add(nicknamePanel, gbc);

		final JPanel passwordPanel = createPasswordPanel(false);
		gbc.gridx = 0;
		gbc.gridy = 1;
		inputPanel.add(passwordPanel, gbc);

		final JPanel repeatedPasswordPanel = createPasswordPanel(true);
		gbc.gridx = 0;
		gbc.gridy = 2;
		inputPanel.add(repeatedPasswordPanel, gbc);

		final JPanel buttonPanel = createButtonPanel();
		gbc.gridx = 0;
		gbc.gridy = 3;
		inputPanel.add(buttonPanel, gbc);

		return inputPanel;
	}

	private JPanel createButtonPanel() {
		final JPanel buttonPanel = new JPanel(new GridBagLayout());
		buttonPanel.setOpaque(false);

		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);

		final JButton registerButton = uiComponentCreator.createButton(UiResource.BUTTON_REGISTER_TEXT);
		registerButton.addActionListener(onRegisterButtonClicked());
		gbc.gridx = 0;
		gbc.gridy = 0;
		buttonPanel.add(registerButton, gbc);

		return buttonPanel;
	}

	private JPanel createPasswordPanel(final boolean isRepeatingField) {
		final JPanel passwordPanel = new JPanel(new GridBagLayout());
		passwordPanel.setOpaque(false);

		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);

		final JLabel passwordLabel = uiComponentCreator.createLabel(UiResource.INPUT_LABEL_PASSWORD, UiConfig.COPYRIGHT_FONT);
		gbc.gridx = 0;
		gbc.gridy = 0;
		passwordPanel.add(passwordLabel, gbc);

		final JPasswordField passwordField = createPasswordField();
		gbc.gridx = 0;
		gbc.gridy = 1;
		passwordPanel.add(passwordField, gbc);

		if (isRepeatingField) {
			repeatedPasswordTextField = passwordField;
		} else {
			passwordTextField = passwordField;
		}

		return passwordPanel;
	}

	private JPasswordField createPasswordField() {
		final JPasswordField passwordField = new JPasswordField();

		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setPreferredSize(new Dimension(300, 100));
		passwordField.setFont(UiConfig.COPYRIGHT_FONT);
		passwordField.setBorder(null);

		return passwordField;
	}

	private JPanel createNicknamePanel() {
		final JPanel nicknamePanel = new JPanel(new GridBagLayout());
		nicknamePanel.setOpaque(false);

		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);

		final JLabel nicknameLabel = uiComponentCreator.createLabel(UiResource.INPUT_LABEL_NICKNAME, UiConfig.COPYRIGHT_FONT);
		gbc.gridx = 0;
		gbc.gridy = 0;
		nicknamePanel.add(nicknameLabel, gbc);

		final JTextField nicknameField = createNicknameField();
		gbc.gridx = 0;
		gbc.gridy = 1;
		nicknamePanel.add(nicknameField, gbc);

		return nicknamePanel;
	}

	private JTextField createNicknameField() {
		final JTextField textField = new JTextField();

		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setPreferredSize(new Dimension(300, 100));
		textField.setFont(UiConfig.COPYRIGHT_FONT);
		textField.setBorder(null);
		nicknameTextField = textField;

		return textField;
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

		final JLabel subtitleLabel = uiComponentCreator.createLabel(UiResource.SUBTITLE_REGISTER, UiConfig.BUTTON_FONT);
		gbc.gridx = 0;
		gbc.gridy = 1;
		headerPanel.add(subtitleLabel, gbc);

		return headerPanel;
	}

	private ActionListener onRegisterButtonClicked() {
		return e -> {
			if (!Arrays.equals(passwordTextField.getPassword(), repeatedPasswordTextField.getPassword())) {
				JOptionPane.showMessageDialog(view, "Passwords do not match");
				return;
			}

			final var password = Arrays.toString(passwordTextField.getPassword());
			final var nicknameValidationResult = authenticationService.isNicknameValid(nicknameTextField.getText());
			final var passwordValidationResult = authenticationService.isPasswordValid(password);

			if (nicknameValidationResult.valid() && passwordValidationResult.valid()) {
				authenticationService.register(nicknameTextField.getText(), password);
				navigationHandler.navigateToLoginView(parentWindow).actionPerformed(e);
				return;
			}

			final StringBuilder validationMessages = new StringBuilder();

			if (!nicknameValidationResult.valid()) {
				validationMessages.append(nicknameValidationResult.getMessagesAsString());
			}

			if (!passwordValidationResult.valid()) {
				if (!validationMessages.isEmpty()) {
					validationMessages.append("\n");
				}
				validationMessages.append(passwordValidationResult.getMessagesAsString());
			}

			JOptionPane.showMessageDialog(view, validationMessages.toString());
		};
	}
}
