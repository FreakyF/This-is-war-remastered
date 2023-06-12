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
import pl.kielce.tu.drylofudala.ui.service.UiComponentCreator;
import pl.kielce.tu.drylofudala.ui.service.navigation_handler.IViewNavigationHandler;
import pl.kielce.tu.drylofudala.ui.view.factory.IAuthView;

public class LoginView implements IAuthView {
	private IViewNavigationHandler navigationHandler;
	private UiComponentCreator uiComponentCreator;
	private IAuthenticationService authenticationService;

	private JPanel view;
	private MainWindow parentWindow;
	private JTextField nicknameTextField;
	private JPasswordField passwordTextField;

	@Override
	public JPanel createView(final MainWindow parentWindow, final IAuthenticationService authenticationService, final IViewNavigationHandler viewNavigationHandler, final IResourceRepository resourceRepository) {
		this.parentWindow = parentWindow;
		this.authenticationService = authenticationService;
		navigationHandler = viewNavigationHandler;
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

		final JPanel passwordPanel = createPasswordPanel();
		gbc.gridx = 0;
		gbc.gridy = 1;
		inputPanel.add(passwordPanel, gbc);

		final JPanel buttonPanel = createButtonPanel();
		gbc.gridx = 0;
		gbc.gridy = 2;
		inputPanel.add(buttonPanel, gbc);

		return inputPanel;
	}

	private JPanel createButtonPanel() {
		final JPanel buttonPanel = new JPanel(new GridBagLayout());
		buttonPanel.setOpaque(false);

		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);

		final JButton loginButton = uiComponentCreator.createButton(UiResource.BUTTON_LOGIN_TEXT, 300, 100);
		loginButton.addActionListener(onLoginButtonClicked());
		gbc.gridx = 0;
		gbc.gridy = 0;
		buttonPanel.add(loginButton, gbc);

		return buttonPanel;
	}

	private JPanel createPasswordPanel() {
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

		return passwordPanel;
	}

	private JPasswordField createPasswordField() {
		final JPasswordField passwordField = new JPasswordField();

		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setPreferredSize(new Dimension(300, 100));
		passwordField.setFont(UiConfig.COPYRIGHT_FONT);
		passwordField.setBorder(null);
		passwordTextField = passwordField;

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

		final JLabel subtitleLabel = uiComponentCreator.createLabel(UiResource.SUBTITLE_LOGIN, UiConfig.BUTTON_FONT);
		gbc.gridx = 0;
		gbc.gridy = 1;
		headerPanel.add(subtitleLabel, gbc);

		return headerPanel;
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

	private ActionListener onLoginButtonClicked() {
		return e -> {
			final var nickname = nicknameTextField.getText();
			final var password = Arrays.toString(passwordTextField.getPassword());
			final var authResult = authenticationService.login(nickname, password);
			if (authResult.authorized()) {
				parentWindow.setLoggedInUserId(authResult.playerId());
				navigationHandler.navigateToUserView(parentWindow).actionPerformed(e);
				return;
			}

			JOptionPane.showMessageDialog(view, authResult.message());
		};
	}
}
