package pl.kielce.tu.drylofudala.ui.view.factory;

public interface IViewFactory {
	IView getGuestViewFactory();

	IView getLoginViewFactory();

	IView getRegisterViewFactory();

	IView getUserViewFactory();

	IView getGameViewFactory();
}
