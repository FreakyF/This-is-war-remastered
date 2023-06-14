package pl.kielce.tu.drylofudala;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.kielce.tu.drylofudala.system.GameClient;
import pl.kielce.tu.drylofudala.system.GameServer;

import java.io.IOException;
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

public final class Main {
	private static final Logger logger = LogManager.getLogger(Main.class);
	private static final IResourceRepository resourceRepository = new ResourceRepository();
	private static final IViewFactory viewFactory = new ViewFactory();
	private static final IPlayerRepository playerRepository = new PlayerRepository();
	private static final IHasher hasher = new Hasher();
	private static final IAuthenticationService authenticationService = new AuthenticationService(playerRepository, hasher);

	public static void main(final String[] args) {
		logger.info("Application started");
		new MainWindow(authenticationService, viewFactory, resourceRepository);
        try {
            // Tworzenie i uruchamianie serwera
            GameServer gameServer = new GameServer(5000);
            new Thread(() -> {
                try {
                    gameServer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            Thread.sleep(2000);

            // Symulacja dwóch graczy
            GameClient player1 = new GameClient("localhost", 5000);
            player1.connect();

            Thread.sleep(2000);

            GameClient player2 = new GameClient("localhost", 5000);
            player2.connect();

            // Zamykanie połączeń
            player1.close();
            player2.close();
            gameServer.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
