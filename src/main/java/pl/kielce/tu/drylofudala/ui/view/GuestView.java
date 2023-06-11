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
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class GuestView implements IView {
	private final ViewNavigationHandler navigationHandler = ViewNavigationHandler.getInstance();

	@Override
	public JPanel createView() {
		return initializeView();
	}

	JPanel initializeView() {
		final JPanel view = new JPanel(new BorderLayout());

		ImagePanel backgroundPanel = UiComponentCreator.createBackgroundPanel();
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

	private JPanel createInputPanel() {
		JPanel inputPanel = new JPanel(new GridBagLayout());
		inputPanel.setOpaque(false);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);

		JButton loginButton = UiComponentCreator.createButton(UiResource.BUTTON_LOGIN_TEXT, 300, 100);
		loginButton.addActionListener(navigationHandler.navigateToLoginView());
		gbc.gridx = 0;
		gbc.gridy = 0;
		inputPanel.add(loginButton, gbc);

		JButton registerButton = UiComponentCreator.createButton(UiResource.BUTTON_REGISTER_TEXT, 300, 100);
		registerButton.addActionListener(navigationHandler.navigateToRegisterView());
		gbc.gridx = 0;
		gbc.gridy = 1;
		inputPanel.add(registerButton, gbc);

		return inputPanel;
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

		return headerPanel;
	}
}
