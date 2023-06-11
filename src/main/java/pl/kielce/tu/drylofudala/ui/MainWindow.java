package pl.kielce.tu.drylofudala.ui;

import java.awt.Dimension;
import javax.swing.JFrame;
import pl.kielce.tu.drylofudala.persistance.resource.IResourceRepository;
import pl.kielce.tu.drylofudala.ui.model.DialogBox;
import pl.kielce.tu.drylofudala.ui.service.navigation_handler.IViewNavigationHandler;
import pl.kielce.tu.drylofudala.ui.service.navigation_handler.ViewNavigationHandler;
import pl.kielce.tu.drylofudala.ui.view.factory.IViewFactory;

public class MainWindow extends JFrame {
	private final transient IResourceRepository resourceRepository;
	private final transient IViewFactory viewFactory;
	private final transient IViewNavigationHandler viewNavigationHandler;

	public MainWindow(IViewFactory viewFactory, IResourceRepository resourceRepository) {
		this.resourceRepository = resourceRepository;
		this.viewFactory = viewFactory;
		this.viewNavigationHandler = new ViewNavigationHandler(viewFactory, resourceRepository);
		initializeWindow();
	}

	private void initializeWindow() {
		setTitle("This is war - Guest"); // First view is always guest.
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(UiConfig.WINDOW_MIN_WIDTH, UiConfig.WINDOW_MIN_HEIGHT));

		setLocationRelativeTo(null); // set to null because window has no parent. The window is itself a parent.
		setDefaultLookAndFeelDecorated(true);

		var guestView = viewFactory.getGuestViewFactory().createView(viewNavigationHandler, resourceRepository);
		add(guestView);

		setVisible(true);

		new DialogBox(this, "", resourceRepository);
	}
}
