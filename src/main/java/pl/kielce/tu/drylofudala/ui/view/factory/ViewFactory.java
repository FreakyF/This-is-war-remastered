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
		return new GameView();
	}
}
