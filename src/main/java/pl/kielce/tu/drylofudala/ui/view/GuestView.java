package pl.kielce.tu.drylofudala.ui.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import pl.kielce.tu.drylofudala.authentication.service.IAuthenticationService;
import pl.kielce.tu.drylofudala.persistance.resource.IResourceRepository;
import pl.kielce.tu.drylofudala.ui.MainWindow;
import pl.kielce.tu.drylofudala.ui.UiConfig;
import pl.kielce.tu.drylofudala.ui.UiResource;
import pl.kielce.tu.drylofudala.ui.model.ImagePanel;
import pl.kielce.tu.drylofudala.ui.service.navigation_handler.IViewNavigationHandler;
import pl.kielce.tu.drylofudala.ui.service.ui_component_creator.UiComponentCreator;
import pl.kielce.tu.drylofudala.ui.view.factory.IAuthView;

public class GuestView implements IAuthView {
	private static final String NAME = "Guest";
	private IViewNavigationHandler navigationHandler;
	private UiComponentCreator uiComponentCreator;
	private MainWindow parentWindow;

	@Override
	public String getViewName() {
		return NAME;
	}

	@Override
	public JPanel createView(final MainWindow parentWindow, final IAuthenticationService authenticationService, final IViewNavigationHandler navigationHandler, final IResourceRepository resourceRepository) {
		this.parentWindow = parentWindow;
		this.navigationHandler = navigationHandler;
		uiComponentCreator = new UiComponentCreator(resourceRepository);
		parentWindow.getGlassPane().setVisible(false);
		return initializeView();
	}

	JPanel initializeView() {
		final JPanel view = new JPanel(new BorderLayout());

		final ImagePanel backgroundPanel = uiComponentCreator.createBackgroundPanel(parentWindow);
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

	private JPanel createInputPanel() {
		final JPanel inputPanel = new JPanel(new GridBagLayout());
		inputPanel.setOpaque(false);

		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);

		final JButton loginButton = uiComponentCreator.createButton(UiResource.BUTTON_LOGIN_TEXT);
		loginButton.addActionListener(navigationHandler.navigateToLoginView(parentWindow));
		gbc.gridx = 0;
		gbc.gridy = 0;
		inputPanel.add(loginButton, gbc);

		final JButton registerButton = uiComponentCreator.createButton(UiResource.BUTTON_REGISTER_TEXT);
		registerButton.addActionListener(navigationHandler.navigateToRegisterView(parentWindow));
		gbc.gridx = 0;
		gbc.gridy = 1;
		inputPanel.add(registerButton, gbc);

		return inputPanel;
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
}
