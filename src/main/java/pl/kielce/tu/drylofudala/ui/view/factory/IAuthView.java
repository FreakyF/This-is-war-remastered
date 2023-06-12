package pl.kielce.tu.drylofudala.ui.view.factory;

import javax.swing.JPanel;
import pl.kielce.tu.drylofudala.authentication.service.IAuthenticationService;
import pl.kielce.tu.drylofudala.persistance.resource.IResourceRepository;
import pl.kielce.tu.drylofudala.ui.MainWindow;
import pl.kielce.tu.drylofudala.ui.service.navigation_handler.IViewNavigationHandler;

@FunctionalInterface
public interface IAuthView {
	JPanel createView(MainWindow parentWindow, IAuthenticationService authenticationService, IViewNavigationHandler navigationHandler, IResourceRepository resourceRepository);
}
