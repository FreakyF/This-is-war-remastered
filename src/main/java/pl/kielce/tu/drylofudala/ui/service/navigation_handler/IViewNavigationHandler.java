package pl.kielce.tu.drylofudala.ui.service.navigation_handler;

import java.awt.event.ActionListener;
import pl.kielce.tu.drylofudala.ui.MainWindow;

public interface IViewNavigationHandler {
	MainWindow getMainWindow();

	ActionListener navigateToLoginView();

	ActionListener navigateToRegisterView();

	ActionListener navigateToUserView();

	ActionListener navigateToGameView();

	ActionListener navigateToGuestView();

	ActionListener exitGame();
}
