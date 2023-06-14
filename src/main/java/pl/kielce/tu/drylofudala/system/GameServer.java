package pl.kielce.tu.drylofudala.system;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameServer {
	public static final int DEFAULT_PORT = 8000;
	private static final Logger logger = LogManager.getLogger(GameServer.class);
	private final int port;
	private ServerSocket serverSocket;
	private GameSession gameSession;

	private volatile boolean running;

	public GameServer() {
		port = DEFAULT_PORT;
	}

	public GameServer(final int port) {
		this.port = port;
	}

	public void start() throws IOException {
		running = true;
		serverSocket = new ServerSocket(port);
		while (running) {
			logger.debug("Waiting for first client...");
			final Socket firstPlayerSocket = serverSocket.accept();
			final GameClient firstPlayer = new GameClient(firstPlayerSocket);
			new Thread(() -> {
				try {
					firstPlayer.connect();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}).start();
			logger.debug("First player connected");

			logger.debug("Waiting for second client...");
			final Socket secondPlayerSocket = serverSocket.accept();
			final GameClient secondPlayer = new GameClient(secondPlayerSocket);
			new Thread(() -> {
				try {
					secondPlayer.connect();
				} catch (final IOException e) {
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
		if (serverSocket != null) {
			serverSocket.close();
		}
	}
}
