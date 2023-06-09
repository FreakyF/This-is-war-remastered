package pl.kielce.tu.drylofudala.ui.view.factory;

import pl.kielce.tu.drylofudala.ui.view.GuestView;
import pl.kielce.tu.drylofudala.ui.view.LoginView;
import pl.kielce.tu.drylofudala.ui.view.RegisterView;

public class ViewFactory {
	private static ViewFactory instance;

	public static ViewFactory getInstance() {
		if (instance == null) {
			instance = new ViewFactory();
		}
		return instance;
	}

	private ViewFactory() {
	}

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
		return new RegisterView();
	}

	public IView getGameViewFactory() {
		return new RegisterView();
	}
}
