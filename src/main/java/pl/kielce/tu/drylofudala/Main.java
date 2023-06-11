package pl.kielce.tu.drylofudala;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.kielce.tu.drylofudala.persistance.resource.IResourceRepository;
import pl.kielce.tu.drylofudala.persistance.resource.ResourceRepository;
import pl.kielce.tu.drylofudala.ui.MainWindow;
import pl.kielce.tu.drylofudala.ui.view.factory.IViewFactory;
import pl.kielce.tu.drylofudala.ui.view.factory.ViewFactory;

public class Main {
	private static final Logger logger = LogManager.getLogger(Main.class);
	private static final IResourceRepository resourceRepository = new ResourceRepository();
	private static final IViewFactory viewFactory = new ViewFactory();

	public static void main(String[] args) {
		logger.info("Application started");
		new MainWindow(viewFactory, resourceRepository);
	}
}
