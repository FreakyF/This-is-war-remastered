package pl.kielce.tu.drylofudala.system;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.kielce.tu.drylofudala.entity.Player;
import pl.kielce.tu.drylofudala.ui.view.game.GameView;

public class Client extends Thread {
	private static final Logger logger = LogManager.getLogger(Client.class);
	private final int serverPort;
	private final GameView gameView;
	private final Player player;
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;

	public Client(final int serverPort, final GameView gameView) {
		this.serverPort = serverPort;
		this.gameView = gameView;
		player = gameView.getPlayer();
	}

	@Override
	public void run() {
		try {
			socket = new Socket("localhost", serverPort);
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			final var gson = new Gson();

			while (true) {

			}
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(final String msg) {
		out.println(msg);
	}

	public String receiveMessage() {
		try {
			return in.readLine();
		} catch (final IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void stopClient() {
		try {
			socket.close();
			in.close();
			out.close();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
}
