package pl.kielce.tu.drylofudala.system;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameSession implements Runnable {
	private static final Logger logger = LogManager.getLogger(GameSession.class);
	private final GameClient firstPlayer;
	private final GameClient secondPlayer;

	public GameSession(final GameClient firstPlayer, final GameClient secondPlayer) {
		this.firstPlayer = firstPlayer;
		this.secondPlayer = secondPlayer;
	}

	@Override
	public void run() {
		logger.debug("Game session started");

		// Here is where you would implement the game logic and handle communication
		// between the two players.
		firstPlayer.send("WIADOMOŚĆ OD PIERWSZEGO GRACZA Z GAME SESSION");
		try {
			firstPlayer.receive();
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}

		// And here is how you might receive a message from player 2:
		try {
			final String message = secondPlayer.receive();
			logger.debug("Wiadomosc od pierwszego gracza: {}", message);
			secondPlayer.send("WIADOMOŚĆ OD DRUGIEGO GRACZA Z GAME SESSION");
		} catch (final IOException e) {
			logger.debug("Error reading from player 2: {}", e.getMessage(), e);
		}
	}
}
