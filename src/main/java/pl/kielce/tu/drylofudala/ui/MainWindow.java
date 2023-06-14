package pl.kielce.tu.drylofudala.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Optional;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import pl.kielce.tu.drylofudala.authentication.service.IAuthenticationService;
import pl.kielce.tu.drylofudala.persistance.resource.IResourceRepository;
import pl.kielce.tu.drylofudala.ui.service.navigation_handler.IViewNavigationHandler;
import pl.kielce.tu.drylofudala.ui.service.navigation_handler.ViewNavigationHandler;
import pl.kielce.tu.drylofudala.ui.service.ui_component_creator.IUiComponentCreator;
import pl.kielce.tu.drylofudala.ui.service.ui_component_creator.UiComponentCreator;
import pl.kielce.tu.drylofudala.ui.view.factory.IViewFactory;

public class MainWindow extends JFrame {
	private final transient IAuthenticationService authenticationService;
	private final transient IResourceRepository resourceRepository;
	private final transient IViewFactory viewFactory;
	private final transient IViewNavigationHandler viewNavigationHandler;
	private final transient IUiComponentCreator componentCreator;
	private long loggedInUserId;
	private JButton returnButton;

	public MainWindow(final IAuthenticationService authenticationService, final IViewFactory viewFactory, final IResourceRepository resourceRepository) {
		this.resourceRepository = resourceRepository;
		this.viewFactory = viewFactory;
		viewNavigationHandler = new ViewNavigationHandler(this, authenticationService, viewFactory, resourceRepository);
		componentCreator = new UiComponentCreator(resourceRepository);
		initializeWindow();
		this.authenticationService = authenticationService;
	}

	private void initializeWindow() {
		setTitle("This is war - Guest"); // First view is always guest.
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(UiConfig.WINDOW_MIN_WIDTH, UiConfig.WINDOW_MIN_HEIGHT));

		setLocationRelativeTo(null); // set to null because window has no parent. The window is itself a parent.
		setDefaultLookAndFeelDecorated(true);

		final var guestView = viewFactory.getGuestViewFactory().createView(this, authenticationService, viewNavigationHandler, resourceRepository);
		initializeReturnButton();

		add(guestView);
		setVisible(true);
	}

	private void initializeReturnButton() {
		returnButton = componentCreator.createReturnButton();
		returnButton.addActionListener(viewNavigationHandler.exitGame(this));

		final var glass = (JPanel) getGlassPane();
		glass.setVisible(true);
		glass.setLayout(new FlowLayout(FlowLayout.LEFT));
		glass.add(returnButton);
	}

	public JButton getReturnButton() {
		return returnButton;
	}

	public void hideReturnButton() {
		setReturnButtonState(false);
	}

	public void showReturnButton() {
		setReturnButtonState(true);
	}

	private void setReturnButtonState(final boolean value) {
		getGlassPane().setVisible(value);
	}

	public long getLoggedInUserId() {
		return loggedInUserId;
	}

	public void setLoggedInUserId(final Long playerId) {
		loggedInUserId = Optional.ofNullable(playerId).orElse(0L);
	}
}
