package pl.kielce.tu.drylofudala.ui.view.factory;

import pl.kielce.tu.drylofudala.persistance.repository.card.ICardRepository;
import pl.kielce.tu.drylofudala.ui.view.GuestView;
import pl.kielce.tu.drylofudala.ui.view.LoginView;
import pl.kielce.tu.drylofudala.ui.view.RegisterView;
import pl.kielce.tu.drylofudala.ui.view.UserView;
import pl.kielce.tu.drylofudala.ui.view.game.GameView;

public class ViewFactory implements IViewFactory {
	private final ICardRepository cardRepository;

	public ViewFactory(final ICardRepository cardRepository) {
		this.cardRepository = cardRepository;
	}

	public IAuthView getGuestViewFactory() {
		return new GuestView();
	}

	public IAuthView getLoginViewFactory() {
		return new LoginView();
	}

	public IAuthView getRegisterViewFactory() {
		return new RegisterView();
	}

	public IAuthView getUserViewFactory() {
		return new UserView();
	}

	public IView getGameViewFactory() {
		return new GameView(cardRepository);
	}
}
