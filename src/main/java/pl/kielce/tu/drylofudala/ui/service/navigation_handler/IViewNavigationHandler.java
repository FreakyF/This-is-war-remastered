package pl.kielce.tu.drylofudala.ui.service.navigation_handler;

import pl.kielce.tu.drylofudala.ui.MainWindow;

import java.awt.event.ActionListener;

public interface IViewNavigationHandler {
    ActionListener navigateToLoginView(MainWindow parentWindow);

    ActionListener navigateToRegisterView(MainWindow parentWindow);

    ActionListener navigateToUserView(MainWindow parentWindow);

    ActionListener navigateToGameView(MainWindow parentWindow);
}
