package pl.kielce.tu.drylofudala.ui;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import pl.kielce.tu.drylofudala.persistance.resource.IResourceRepository;
import pl.kielce.tu.drylofudala.ui.model.DialogBox;
import pl.kielce.tu.drylofudala.ui.service.navigation_handler.IViewNavigationHandler;
import pl.kielce.tu.drylofudala.ui.view.GuestView;

public class MainWindow extends JFrame {
	private final transient IResourceRepository resourceRepository;
	private final transient IViewNavigationHandler viewNavigationHandler;

	public MainWindow(IViewNavigationHandler viewNavigationHandler, IResourceRepository resourceRepository) {
		this.resourceRepository = resourceRepository;
		this.viewNavigationHandler = viewNavigationHandler;
		initializeWindow();
	}

	private void initializeWindow() {
		setTitle("This is war - Guest"); // First view is always guest.
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(UiConfig.WINDOW_MIN_WIDTH, UiConfig.WINDOW_MIN_HEIGHT));

		setLocationRelativeTo(null); // set to null because window has no parent. The window is itself a parent.
		setDefaultLookAndFeelDecorated(true);

		GuestView guestView = new GuestView();
		JPanel viewPanel = guestView.createView(viewNavigationHandler, resourceRepository);
		add(viewPanel);

		setVisible(true);

		new DialogBox(this, "", resourceRepository);
	}
}
