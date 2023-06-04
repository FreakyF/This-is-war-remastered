package pl.kielce.tu.drylofudala.system;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class GameSession implements Runnable {
    private static final Logger logger = LogManager.getLogger(GameSession.class);
    private GameClient firstPlayer;
    private GameClient secondPlayer;

    public GameSession(GameClient firstPlayer, GameClient secondPlayer) {
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // And here is how you might receive a message from player 2:
        try {
            String message = secondPlayer.receive();
            logger.debug("Wiadomosc od pierwszego gracza: {}", message);
            secondPlayer.send("WIADOMOŚĆ OD DRUGIEGO GRACZA Z GAME SESSION");
        } catch (IOException e) {
            logger.debug("Error reading from player 2: {}", e.getMessage(), e);
        }
    }
}
