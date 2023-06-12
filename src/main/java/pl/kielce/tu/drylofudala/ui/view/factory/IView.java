package pl.kielce.tu.drylofudala.ui.view.factory;

import javax.swing.JPanel;
import pl.kielce.tu.drylofudala.persistance.resource.IResourceRepository;
import pl.kielce.tu.drylofudala.ui.MainWindow;
import pl.kielce.tu.drylofudala.ui.service.navigation_handler.IViewNavigationHandler;

@FunctionalInterface
public interface IView {
	JPanel createView(MainWindow parentWindow, IViewNavigationHandler navigationHandler, IResourceRepository resourceRepository);
}
