package pl.kielce.tu.drylofudala.ui.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import pl.kielce.tu.drylofudala.ui.UiConfig;
import pl.kielce.tu.drylofudala.ui.UiResource;
import pl.kielce.tu.drylofudala.ui.model.ImagePanel;
import pl.kielce.tu.drylofudala.ui.service.navigation_handler.IViewNavigationHandler;
import pl.kielce.tu.drylofudala.ui.service.ui_component_creator.IUiComponentCreator;
import pl.kielce.tu.drylofudala.ui.view.factory.IView;

public class GuestView implements IView {
	private static final String NAME = "Guest";
	private final IUiComponentCreator uiComponentCreator;
	private IViewNavigationHandler viewNavigationHandler;

	public GuestView(final IUiComponentCreator uiComponentCreator) {
		this.uiComponentCreator = uiComponentCreator;
	}

	@Override
	public String getViewName() {
		return NAME;
	}

	@Override
	public JPanel createView(final IViewNavigationHandler viewNavigationHandler) {
		this.viewNavigationHandler = viewNavigationHandler;
		viewNavigationHandler.getMainWindow().hideReturnButton();
		return initializeView();
	}

	JPanel initializeView() {
		final JPanel view = new JPanel(new BorderLayout());

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

	private JPanel createInputPanel() {
		final JPanel inputPanel = new JPanel(new GridBagLayout());
		inputPanel.setOpaque(false);

		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);

		final JButton loginButton = uiComponentCreator.createButton(UiResource.BUTTON_LOGIN_TEXT);
		loginButton.addActionListener(viewNavigationHandler.navigateToLoginView());
		gbc.gridx = 0;
		gbc.gridy = 0;
		inputPanel.add(loginButton, gbc);

		final JButton registerButton = uiComponentCreator.createButton(UiResource.BUTTON_REGISTER_TEXT);
		registerButton.addActionListener(viewNavigationHandler.navigateToRegisterView());
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
