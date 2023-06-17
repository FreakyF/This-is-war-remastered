package pl.kielce.tu.drylofudala.ui.view.factory;

import javax.swing.JPanel;
import pl.kielce.tu.drylofudala.ui.service.navigation_handler.IViewNavigationHandler;

public interface IView {
	String getViewName();

	JPanel createView(IViewNavigationHandler navigationHandler);
}
