package pl.kielce.tu.drylofudala.ui.view;

import pl.kielce.tu.drylofudala.authentication.service.IAuthenticationService;
import pl.kielce.tu.drylofudala.persistance.resource.IResourceRepository;
import pl.kielce.tu.drylofudala.ui.MainWindow;
import pl.kielce.tu.drylofudala.ui.UiConfig;
import pl.kielce.tu.drylofudala.ui.UiResource;
import pl.kielce.tu.drylofudala.ui.model.ImagePanel;
import pl.kielce.tu.drylofudala.ui.service.UiComponentCreator;
import pl.kielce.tu.drylofudala.ui.service.navigation_handler.IViewNavigationHandler;
import pl.kielce.tu.drylofudala.ui.view.factory.IAuthView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class RegisterView implements IAuthView {
    private IViewNavigationHandler navigationHandler;
    private UiComponentCreator uiComponentCreator;
    private IAuthenticationService authenticationService;

    private JPanel view;
    private JTextField nicknameTextField;
    private JPasswordField passwordTextField;
    private JPasswordField repeatedPasswordTextField;
    MainWindow parentWindow;


    @Override
    public JPanel createView(MainWindow parentWindow, IAuthenticationService authenticationService, IViewNavigationHandler viewNavigationHandler, IResourceRepository resourceRepository) {
        this.parentWindow = parentWindow;
        this.authenticationService = authenticationService;
        this.navigationHandler = viewNavigationHandler;
        this.uiComponentCreator = new UiComponentCreator(resourceRepository);
        return initializeView();
    }

    private JPanel initializeView() {
        view = new JPanel(new BorderLayout());

        ImagePanel backgroundPanel = uiComponentCreator.createBackgroundPanel();
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

        JPanel passwordPanel = createPasswordPanel(false);
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(passwordPanel, gbc);

        JPanel repeatedPasswordPanel = createPasswordPanel(true);
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

        JButton registerButton = uiComponentCreator.createButton(UiResource.BUTTON_REGISTER_TEXT, 300, 100);
        registerButton.addActionListener(onRegisterButtonClicked());
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(registerButton, gbc);

        return buttonPanel;
    }

    private JPanel createPasswordPanel(boolean isRepeatingField) {
        JPanel passwordPanel = new JPanel(new GridBagLayout());
        passwordPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel passwordLabel = uiComponentCreator.createLabel(UiResource.INPUT_LABEL_PASSWORD, UiConfig.COPYRIGHT_FONT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        passwordPanel.add(passwordLabel, gbc);

        JPasswordField passwordField = createPasswordField();
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

        JLabel nicknameLabel = uiComponentCreator.createLabel(UiResource.INPUT_LABEL_NICKNAME, UiConfig.COPYRIGHT_FONT);
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
        nicknameTextField = textField;

        return textField;
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

        JLabel subtitleLabel = uiComponentCreator.createLabel(UiResource.SUBTITLE_REGISTER, UiConfig.BUTTON_FONT);
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

            var password = Arrays.toString(passwordTextField.getPassword());
            var nicknameValidationResult = authenticationService.isNicknameValid(nicknameTextField.getText());
            var passwordValidationResult = authenticationService.isPasswordValid(password);

            if (nicknameValidationResult.valid() && passwordValidationResult.valid()) {
                authenticationService.register(nicknameTextField.getText(), password);
                navigationHandler.navigateToLoginView(parentWindow).actionPerformed(e);
                return;
            }

            StringBuilder validationMessages = new StringBuilder();

            if (!nicknameValidationResult.valid()) {
                validationMessages.append(nicknameValidationResult.getMessagesAsString());
            }

            if (!passwordValidationResult.valid()) {
                if (validationMessages.length() > 0) {
                    validationMessages.append("\n");
                }
                validationMessages.append(passwordValidationResult.getMessagesAsString());
            }

            JOptionPane.showMessageDialog(view, validationMessages.toString());
        };
    }
}
