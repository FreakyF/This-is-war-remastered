package pl.kielce.tu.drylofudala.ui.service.navigation_handler;

import java.awt.event.ActionListener;
import pl.kielce.tu.drylofudala.ui.MainWindow;

public interface IViewNavigationHandler {
	ActionListener navigateToLoginView(MainWindow parentWindow);

	ActionListener navigateToRegisterView(MainWindow parentWindow);

	ActionListener navigateToUserView(MainWindow parentWindow);

	ActionListener navigateToGameView(MainWindow parentWindow);
}
