package pl.kielce.tu.drylofudala.ui.view.factory;

import pl.kielce.tu.drylofudala.ui.view.*;

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

    public IView getUserViewFactory() {
        return new UserView();
    }

    public IView getGameViewFactory() {
        return new GameView();
    }
}
