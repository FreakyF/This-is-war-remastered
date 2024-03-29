package pl.kielce.tu.drylofudala.ui.service.navigation_handler;

import java.awt.event.ActionListener;
import javax.swing.JPanel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.kielce.tu.drylofudala.authentication.service.IAuthenticationService;
import pl.kielce.tu.drylofudala.persistance.resource.IResourceRepository;
import pl.kielce.tu.drylofudala.ui.MainWindow;
import pl.kielce.tu.drylofudala.ui.extension.JButtonExtension;
import pl.kielce.tu.drylofudala.ui.view.factory.IAuthView;
import pl.kielce.tu.drylofudala.ui.view.factory.IView;
import pl.kielce.tu.drylofudala.ui.view.factory.IViewFactory;

public class ViewNavigationHandler implements IViewNavigationHandler {
	private final IResourceRepository resourceRepository;
	private final IViewFactory viewFactory;
	private final IAuthenticationService authenticationService;

	private final MainWindow mainWindow;

	public ViewNavigationHandler(final MainWindow mainWindow, final IAuthenticationService authenticationService, final IViewFactory viewFactory, final IResourceRepository resourceRepository) {
		this.authenticationService = authenticationService;
		this.resourceRepository = resourceRepository;
		this.viewFactory = viewFactory;
		this.mainWindow = mainWindow;
	}

	@Override
	public ActionListener navigateToLoginView(@NotNull final MainWindow parentWindow) {
		return e -> {
			final IAuthView loginViewFactory = viewFactory.getLoginViewFactory();
			// TODO: move this method call from this method.
			mainWindow.setLoggedInUserId(null);
			final var onReturnButtonClicked = navigateToGuestView(parentWindow);
			navigateToAuthView(parentWindow, loginViewFactory, onReturnButtonClicked);
		};
	}

	@Override
	public ActionListener navigateToRegisterView(@NotNull final MainWindow parentWindow) {
		return e -> {
			final IAuthView registerViewFactory = viewFactory.getRegisterViewFactory();
			final var onReturnButtonClicked = navigateToGuestView(parentWindow);
			navigateToAuthView(parentWindow, registerViewFactory, onReturnButtonClicked);
		};
	}

	@Override
	public ActionListener navigateToUserView(@NotNull final MainWindow parentWindow) {
		return e -> {
			final IAuthView userViewFactory = viewFactory.getUserViewFactory();
			// TODO: Modify so that the user will be logged out.
			final var onReturnButtonClicked = navigateToLoginView(parentWindow);
			navigateToAuthView(parentWindow, userViewFactory, onReturnButtonClicked);
		};
	}

	@Override
	public ActionListener navigateToGameView(@NotNull final MainWindow parentWindow) {
		return e -> {
			final IView gameViewFactory = viewFactory.getGameViewFactory();
			navigateToView(parentWindow, gameViewFactory);
		};
	}

	@Override
	public ActionListener navigateToGuestView(@NotNull final MainWindow parentWindow) {
		return e -> {
			final IAuthView guestViewFactory = viewFactory.getGuestViewFactory();
			final var onReturnButtonClicked = exitGame(parentWindow);
			navigateToAuthView(parentWindow, guestViewFactory, onReturnButtonClicked);

		};
	}

	public ActionListener exitGame(final @NotNull MainWindow parentWindow) {
		return e -> System.exit(0);
	}

	private void navigateToView(@NotNull final MainWindow parentWindow, final IView viewFactory) {
		final JPanel view = viewFactory.createView(parentWindow, this, resourceRepository);
		parentWindow.setContentPane(view);
		navigateTo(parentWindow, viewFactory.getViewName(), null);
	}

	private void navigateToAuthView(@NotNull final MainWindow parentWindow, final IAuthView viewFactory, @Nullable final ActionListener onReturnButtonClicked) {
		final JPanel view = viewFactory.createView(parentWindow, authenticationService, this, resourceRepository);
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
