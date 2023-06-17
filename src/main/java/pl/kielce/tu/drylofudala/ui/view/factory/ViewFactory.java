package pl.kielce.tu.drylofudala.ui.view.factory;

import pl.kielce.tu.drylofudala.authentication.hasher.Hasher;
import pl.kielce.tu.drylofudala.authentication.hasher.IHasher;
import pl.kielce.tu.drylofudala.authentication.service.AuthenticationService;
import pl.kielce.tu.drylofudala.authentication.service.IAuthenticationService;
import pl.kielce.tu.drylofudala.persistance.repository.card.CardRepository;
import pl.kielce.tu.drylofudala.persistance.repository.card.ICardRepository;
import pl.kielce.tu.drylofudala.persistance.repository.player.IPlayerRepository;
import pl.kielce.tu.drylofudala.persistance.repository.player.PlayerRepository;
import pl.kielce.tu.drylofudala.ui.service.ui_component_creator.IUiComponentCreator;
import pl.kielce.tu.drylofudala.ui.view.GuestView;
import pl.kielce.tu.drylofudala.ui.view.LoginView;
import pl.kielce.tu.drylofudala.ui.view.RegisterView;
import pl.kielce.tu.drylofudala.ui.view.UserView;
import pl.kielce.tu.drylofudala.ui.view.game.GameView;

public class ViewFactory implements IViewFactory {
	private final IUiComponentCreator uiComponentCreator;
	private final ICardRepository cardRepository;
	private final IAuthenticationService authenticationService;
	private final IPlayerRepository playerRepository;
	private final IHasher hasher;

	public ViewFactory(final IUiComponentCreator uiComponentCreator) {
		hasher = new Hasher();

		playerRepository = new PlayerRepository();
		cardRepository = new CardRepository();

		authenticationService = new AuthenticationService(playerRepository, hasher);
		this.uiComponentCreator = uiComponentCreator;
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
		return new GameView(uiComponentCreator);
	}
}
