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

		StringBuilder validationMessages = new StringBuilder();
		validationMessages.append("PASSWORD_TOO_SHORT=Password is too short. Minimum length is: %d\n");
		validationMessages.append("PASSWORD_TOO_LONG=Password is too long. Maximum length is: %d\n");
		validationMessages.append("PASSWORD_WITHOUT_LOWERCASE=Password does not contain at least one lowercase character\n");
		validationMessages.append("PASSWORD_WITHOUT_UPPERCASE=Password does not contain at least one uppercase character\n");
		validationMessages.append("PASSWORD_WITHOUT_SPECIAL_CHARACTER=Password does not contain at least one special character\n");
		validationMessages.append("PASSWORD_WITHOUT_NUMBER=Password does not contain at least one numeric character\n");
		validationMessages.append("NICKNAME_TOO_SHORT=Nickname is too short. Minimum length is: %d\n");
		validationMessages.append("NICKNAME_TOO_LONG=Nickname is too long. Maximum length is: %d\n");

		String validationMessagesString = validationMessages.toString();
		new DialogBox(this, validationMessagesString, resourceRepository);
	}
}
