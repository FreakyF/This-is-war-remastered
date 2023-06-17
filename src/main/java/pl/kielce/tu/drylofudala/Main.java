package pl.kielce.tu.drylofudala;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.kielce.tu.drylofudala.persistance.DatabaseSeeder;
import pl.kielce.tu.drylofudala.persistance.repository.card.CardRepository;
import pl.kielce.tu.drylofudala.persistance.repository.card.ICardRepository;
import pl.kielce.tu.drylofudala.ui.MainWindow;

public final class Main {
	private static final Logger logger = LogManager.getLogger(Main.class);
	private static final ICardRepository cardRepository = new CardRepository();

	public static void main(final String[] args) {
		new DatabaseSeeder(cardRepository).seedCards();
		logger.info("Application started");
		new MainWindow();
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
