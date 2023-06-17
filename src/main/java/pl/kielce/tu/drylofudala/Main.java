package pl.kielce.tu.drylofudala;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.kielce.tu.drylofudala.authentication.hasher.Hasher;
import pl.kielce.tu.drylofudala.authentication.hasher.IHasher;
import pl.kielce.tu.drylofudala.authentication.service.AuthenticationService;
import pl.kielce.tu.drylofudala.authentication.service.IAuthenticationService;
import pl.kielce.tu.drylofudala.persistance.DatabaseSeeder;
import pl.kielce.tu.drylofudala.persistance.repository.card.CardRepository;
import pl.kielce.tu.drylofudala.persistance.repository.card.ICardRepository;
import pl.kielce.tu.drylofudala.persistance.repository.player.IPlayerRepository;
import pl.kielce.tu.drylofudala.persistance.repository.player.PlayerRepository;
import pl.kielce.tu.drylofudala.persistance.resource.IResourceRepository;
import pl.kielce.tu.drylofudala.persistance.resource.ResourceRepository;
import pl.kielce.tu.drylofudala.ui.MainWindow;
import pl.kielce.tu.drylofudala.ui.view.factory.IViewFactory;
import pl.kielce.tu.drylofudala.ui.view.factory.ViewFactory;

public final class Main {
	private static final Logger logger = LogManager.getLogger(Main.class);
	private static final IResourceRepository resourceRepository = new ResourceRepository();
	private static final IPlayerRepository playerRepository = new PlayerRepository();
	private static final IHasher hasher = new Hasher();
	private static final IAuthenticationService authenticationService = new AuthenticationService(playerRepository, hasher);
	private static final ICardRepository cardRepository = new CardRepository();
	private static final IViewFactory viewFactory = new ViewFactory(cardRepository);

	public static void main(final String[] args) {
		new DatabaseSeeder(cardRepository).seedCards();
		logger.info("Application started");
		new MainWindow(authenticationService, viewFactory, resourceRepository);
//		final Server server = new Server(8000);
//		server.start();
//
//		final Client client1 = new Client(server.getPort());
//		client1.start();
//
//		final Client client2 = new Client(server.getPort());
//		client2.start();
	}
}
