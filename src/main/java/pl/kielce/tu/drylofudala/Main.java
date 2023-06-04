package pl.kielce.tu.drylofudala;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.kielce.tu.drylofudala.system.GameClient;
import pl.kielce.tu.drylofudala.system.GameServer;

import java.io.IOException;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
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