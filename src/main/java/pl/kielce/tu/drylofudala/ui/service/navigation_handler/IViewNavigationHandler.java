package pl.kielce.tu.drylofudala.ui.service.navigation_handler;

import java.awt.event.ActionListener;
import org.jetbrains.annotations.NotNull;
import pl.kielce.tu.drylofudala.ui.MainWindow;

public interface IViewNavigationHandler {
	ActionListener navigateToLoginView(@NotNull MainWindow parentWindow);

	ActionListener navigateToRegisterView(@NotNull MainWindow parentWindow);

	ActionListener navigateToUserView(@NotNull MainWindow parentWindow);

	ActionListener navigateToGameView(@NotNull MainWindow parentWindow);

	ActionListener navigateToGuestView(@NotNull MainWindow parentWindow);

	ActionListener exitGame(@NotNull final MainWindow parentWindow);
}
