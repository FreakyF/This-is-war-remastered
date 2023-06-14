package pl.kielce.tu.drylofudala.ui.view.factory;

public interface IViewFactory {
	IAuthView getGuestViewFactory();

	IAuthView getLoginViewFactory();

	IAuthView getRegisterViewFactory();

	IAuthView getUserViewFactory();

	IView getGameViewFactory();
}
