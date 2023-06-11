package pl.kielce.tu.drylofudala.ui.service.navigation_handler;

import java.awt.event.ActionListener;

public interface IViewNavigationHandler {
	ActionListener navigateToLoginView();

	ActionListener navigateToRegisterView();

	ActionListener navigateToUserView();

	ActionListener navigateToGameView();
}
