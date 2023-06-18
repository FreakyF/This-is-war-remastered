package pl.kielce.tu.drylofudala.ui.view.factory;

import pl.kielce.tu.drylofudala.authentication.hasher.Hasher;
import pl.kielce.tu.drylofudala.authentication.hasher.IHasher;
import pl.kielce.tu.drylofudala.authentication.service.AuthenticationService;
import pl.kielce.tu.drylofudala.authentication.service.IAuthenticationService;
import pl.kielce.tu.drylofudala.persistance.repository.player.IPlayerRepository;
import pl.kielce.tu.drylofudala.persistance.repository.player.PlayerRepository;
import pl.kielce.tu.drylofudala.ui.MainWindow;
import pl.kielce.tu.drylofudala.ui.service.ui_component_creator.IUiComponentCreator;
import pl.kielce.tu.drylofudala.ui.view.GuestView;
import pl.kielce.tu.drylofudala.ui.view.LoginView;
import pl.kielce.tu.drylofudala.ui.view.RegisterView;
import pl.kielce.tu.drylofudala.ui.view.UserView;
import pl.kielce.tu.drylofudala.ui.view.game.GameView;

public class ViewFactory implements IViewFactory {
	private final IUiComponentCreator uiComponentCreator;
	private final IAuthenticationService authenticationService;
	private final IPlayerRepository playerRepository;
	private final IHasher hasher;
	private final MainWindow mainWindow;

	public ViewFactory(final IUiComponentCreator uiComponentCreator, final MainWindow mainWindow) {
		hasher = new Hasher();
		playerRepository = new PlayerRepository();

		authenticationService = new AuthenticationService(playerRepository, hasher);
		this.uiComponentCreator = uiComponentCreator;

		this.mainWindow = mainWindow;
	}

	public IView getGuestViewFactory() {
		return new GuestView(uiComponentCreator);
	}

	public IView getLoginViewFactory() {
		return new LoginView(uiComponentCreator, authenticationService);
	}

	public IView getRegisterViewFactory() {
		return new RegisterView(uiComponentCreator, authenticationService);
	}

	public IView getUserViewFactory() {
		return new UserView(uiComponentCreator);
	}

	public IView getGameViewFactory() {
		return new GameView(uiComponentCreator, mainWindow.getLoggedInUserId(), playerRepository);
	}
}
