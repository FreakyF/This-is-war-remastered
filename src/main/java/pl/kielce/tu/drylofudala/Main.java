package pl.kielce.tu.drylofudala;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.kielce.tu.drylofudala.authentication.hasher.Hasher;
import pl.kielce.tu.drylofudala.authentication.hasher.IHasher;
import pl.kielce.tu.drylofudala.authentication.service.AuthenticationService;
import pl.kielce.tu.drylofudala.authentication.service.IAuthenticationService;
import pl.kielce.tu.drylofudala.persistance.repository.player.IPlayerRepository;
import pl.kielce.tu.drylofudala.persistance.repository.player.PlayerRepository;
import pl.kielce.tu.drylofudala.persistance.resource.IResourceRepository;
import pl.kielce.tu.drylofudala.persistance.resource.ResourceRepository;
import pl.kielce.tu.drylofudala.ui.MainWindow;
import pl.kielce.tu.drylofudala.ui.view.factory.IViewFactory;
import pl.kielce.tu.drylofudala.ui.view.factory.ViewFactory;

public class Main {
	private static final Logger logger = LogManager.getLogger(Main.class);
	private static final IResourceRepository resourceRepository = new ResourceRepository();
	private static final IViewFactory viewFactory = new ViewFactory();
	private static final IPlayerRepository playerRepository = new PlayerRepository();
	private static final IHasher hasher = new Hasher();
	private static final IAuthenticationService authenticationService = new AuthenticationService(playerRepository, hasher);

	public static void main(String[] args) {
		logger.info("Application started");
		new MainWindow(authenticationService, viewFactory, resourceRepository);
	}
}
