package pl.kielce.tu.drylofudala.ui.view.factory;

public interface IViewFactory {
	IView getGuestViewFactory();

	IAuthView getLoginViewFactory();

	IAuthView getRegisterViewFactory();

	IView getUserViewFactory();

	IView getGameViewFactory();
}
