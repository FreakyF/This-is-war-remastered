package pl.kielce.tu.drylofudala.system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.kielce.tu.drylofudala.system.service.prompt.Prompt;

public class Client extends Thread {
	private static final Logger logger = LogManager.getLogger(Client.class);
	private final int serverPort;
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;

	public Client(final int serverPort) {
		this.serverPort = serverPort;
	}

	@Override
	public void run() {
		try {
			socket = new Socket("localhost", serverPort);
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			while (true) {
				final String message = receiveMessage();

				if (message.equals(Prompt.YOUR_TURN)) {
					sendMessage("Moj ruch! " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort());
					logger.debug("Moj ruch! {}:{}", socket.getInetAddress().getHostAddress(), socket.getPort());
				}

				if (message.equals(Prompt.OPPONENT_TURN)) {
					sendMessage("To nie moj ruch! " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort());
					logger.debug("To nie moj ruch! {}:{}", socket.getInetAddress().getHostAddress(), socket.getPort());
				}

				if (message.equals(Prompt.SEND_OPPONENT_MOVE)) {
					sendMessage("Moja wiadomość została wysłana do przeciwnika! " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort());
					logger.debug("Moja wiadomość została wysłana do przeciwnika! {}:{}", socket.getInetAddress().getHostAddress(), socket.getPort());
				}
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
