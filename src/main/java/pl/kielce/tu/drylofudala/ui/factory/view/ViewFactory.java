package pl.kielce.tu.drylofudala.ui.factory.view;

import pl.kielce.tu.drylofudala.ui.GuestView;
import pl.kielce.tu.drylofudala.ui.LoginView;
import pl.kielce.tu.drylofudala.ui.RegisterView;

public class ViewFactory {
	private static ViewFactory instance;
	public static ViewFactory getInstance(){
		if(instance == null)
		{
			instance = new ViewFactory();
		}
		return instance;
	}
	private ViewFactory(){
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
}
