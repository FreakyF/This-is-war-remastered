package pl.kielce.tu.drylofudala.system;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    private static final Logger logger = LogManager.getLogger(GameServer.class);
    private ServerSocket serverSocket;
    private int port;
    private GameSession gameSession;

    private volatile boolean running = false;

    public GameServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        running = true;
        serverSocket = new ServerSocket(port);
        while(running){
            logger.debug("Waiting for first client...");
            Socket firstPlayerSocket = serverSocket.accept();
            GameClient firstPlayer = new GameClient(firstPlayerSocket);
            new Thread(() -> {
                try {
                    firstPlayer.connect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            logger.debug("First player connected");

            logger.debug("Waiting for second client...");
            Socket secondPlayerSocket = serverSocket.accept();
            GameClient secondPlayer = new GameClient(secondPlayerSocket);
            new Thread(() -> {
                try {
                    secondPlayer.connect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            logger.debug("Second player connected");

            gameSession = new GameSession(firstPlayer, secondPlayer);

            logger.debug("Game session created");
            new Thread(gameSession).start();
        }
    }

    public void stop() throws IOException {
        running = false;
        if(serverSocket != null)
        {
            serverSocket.close();
        }
    }
}
