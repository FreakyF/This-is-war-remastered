package pl.kielce.tu.drylofudala.ui.view.factory;

import pl.kielce.tu.drylofudala.ui.view.GameView;
import pl.kielce.tu.drylofudala.ui.view.GuestView;
import pl.kielce.tu.drylofudala.ui.view.LoginView;
import pl.kielce.tu.drylofudala.ui.view.RegisterView;
import pl.kielce.tu.drylofudala.ui.view.UserView;

public class ViewFactory implements IViewFactory {
	public IView getGuestViewFactory() {
		return new GuestView();
	}

	public IView getLoginViewFactory() {
		return new LoginView();
	}

	public IView getRegisterViewFactory() {
		return new RegisterView();
	}

	public IView getUserViewFactory() {
		return new UserView();
	}

	public IView getGameViewFactory() {
		return new GameView();
	}
}
