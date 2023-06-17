package pl.kielce.tu.drylofudala.ui.service.navigation_handler;

import java.awt.event.ActionListener;
import javax.swing.JPanel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.kielce.tu.drylofudala.ui.MainWindow;
import pl.kielce.tu.drylofudala.ui.extension.JButtonExtension;
import pl.kielce.tu.drylofudala.ui.view.factory.IView;
import pl.kielce.tu.drylofudala.ui.view.factory.IViewFactory;

public class ViewNavigationHandler implements IViewNavigationHandler {
	private final IViewFactory viewFactory;
	private final MainWindow mainWindow;

	public ViewNavigationHandler(final MainWindow mainWindow, final IViewFactory viewFactory) {
		this.viewFactory = viewFactory;
		this.mainWindow = mainWindow;
	}

	@Override
	public MainWindow getMainWindow() {
		return mainWindow;
	}

	@Override
	public ActionListener navigateToLoginView() {
		return e -> {
			final IView loginViewFactory = viewFactory.getLoginViewFactory();
			// TODO: move this method call from this method.
			mainWindow.setLoggedInUserId(null);
			final var onReturnButtonClicked = navigateToGuestView();
			navigateToAuthView(mainWindow, loginViewFactory, onReturnButtonClicked);
		};
	}

	@Override
	public ActionListener navigateToRegisterView() {
		return e -> {
			final IView registerViewFactory = viewFactory.getRegisterViewFactory();
			final var onReturnButtonClicked = navigateToGuestView();
			navigateToAuthView(mainWindow, registerViewFactory, onReturnButtonClicked);
		};
	}

	@Override
	public ActionListener navigateToUserView() {
		return e -> {
			final IView userViewFactory = viewFactory.getUserViewFactory();
			// TODO: Modify so that the user will be logged out.
			final var onReturnButtonClicked = navigateToLoginView();
			navigateToAuthView(mainWindow, userViewFactory, onReturnButtonClicked);
		};
	}

	@Override
	public ActionListener navigateToGameView() {
		return e -> {
			final IView gameViewFactory = viewFactory.getGameViewFactory();
			navigateToView(mainWindow, gameViewFactory);
		};
	}

	@Override
	public ActionListener navigateToGuestView() {
		return e -> {
			final IView guestViewFactory = viewFactory.getGuestViewFactory();
			final var onReturnButtonClicked = exitGame();
			navigateToAuthView(mainWindow, guestViewFactory, onReturnButtonClicked);

		};
	}

	public ActionListener exitGame() {
		return e -> System.exit(0);
	}

	private void navigateToView(@NotNull final MainWindow parentWindow, final IView viewFactory) {
		final JPanel view = viewFactory.createView(this);
		parentWindow.setContentPane(view);
		navigateTo(parentWindow, viewFactory.getViewName(), null);
	}

	private void navigateToAuthView(@NotNull final MainWindow parentWindow, final IView viewFactory, @Nullable final ActionListener onReturnButtonClicked) {
		final JPanel view = viewFactory.createView(this);
		parentWindow.setContentPane(view);
		navigateTo(parentWindow, viewFactory.getViewName(), onReturnButtonClicked);
	}

	private void navigateTo(@NotNull final MainWindow parentWindow, final String viewName, @Nullable final ActionListener onReturnButtonClicked) {
		final var returnButton = parentWindow.getReturnButton();
		JButtonExtension.removeAllListeners(returnButton);
		final var showReturnButton = onReturnButtonClicked != null;
		if (showReturnButton) {
			parentWindow.showReturnButton();
			returnButton.addActionListener(onReturnButtonClicked);
		} else {
			parentWindow.hideReturnButton();
		}

		parentWindow.setTitle("This is war - " + viewName);
		parentWindow.revalidate();
		parentWindow.repaint();
	}
}
