package pl.kielce.tu.drylofudala;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.kielce.tu.drylofudala.persistance.DatabaseSeeder;
import pl.kielce.tu.drylofudala.persistance.repository.card.CardRepository;
import pl.kielce.tu.drylofudala.persistance.repository.card.ICardRepository;
import pl.kielce.tu.drylofudala.system.Server;
import pl.kielce.tu.drylofudala.ui.MainWindow;

public final class Main {
	public static final int SERVER_PORT = 8000;
	private static final Logger logger = LogManager.getLogger(Main.class);
	private static final ICardRepository cardRepository = new CardRepository();

	public static void main(final String[] args) {
		new DatabaseSeeder(cardRepository).seedCards();
		logger.info("Application started");

		final Server server = new Server(SERVER_PORT);
		server.start();

		new Thread(MainWindow::new).start();
		new Thread(MainWindow::new).start();
	}
}
