package pl.kielce.tu.drylofudala.system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.kielce.tu.drylofudala.system.service.prompt.Prompt;

public class Server extends Thread {
	private static final Logger logger = LogManager.getLogger(Server.class);
	private final int port;
	private ServerSocket socket;
	private Socket firstPlayerSocket;
	private Socket secondPlayerSocket;
	private PrintWriter firstPlayerWriter;
	private PrintWriter secondPlayerWriter;
	private BufferedReader firstPlayerReader;
	private BufferedReader secondPlayerReader;

	public Server(final int port) {
		this.port = port;
	}

	public int getPort() {
		return port;
	}

	@Override
	public void run() {
		try {
			socket = new ServerSocket(port);

			firstPlayerSocket = socket.accept();
			firstPlayerWriter = new PrintWriter(firstPlayerSocket.getOutputStream(), true);
			firstPlayerReader = new BufferedReader(new InputStreamReader(firstPlayerSocket.getInputStream()));

			secondPlayerSocket = socket.accept();
			secondPlayerWriter = new PrintWriter(secondPlayerSocket.getOutputStream(), true);
			secondPlayerReader = new BufferedReader(new InputStreamReader(secondPlayerSocket.getInputStream()));

			boolean firstPlayerTurn = true;

			while (true) {
				// TODO: Implement gameplay
				if (firstPlayerTurn) {
					sendMessage(firstPlayerWriter, Prompt.YOUR_TURN);
					logger.debug("{}", Prompt.YOUR_TURN);

					sendMessage(secondPlayerWriter, Prompt.OPPONENT_TURN);
					logger.debug("{}", Prompt.OPPONENT_TURN);
				} else {
					sendMessage(secondPlayerWriter, Prompt.YOUR_TURN);
					logger.debug("{}", Prompt.YOUR_TURN);

					sendMessage(firstPlayerWriter, Prompt.OPPONENT_TURN);
					logger.debug("{}", Prompt.OPPONENT_TURN);
				}

				final String message = firstPlayerTurn ? receiveMessage(firstPlayerReader) : receiveMessage(secondPlayerReader);
				logger.debug("message: {}", message);

				if (firstPlayerTurn) {
					sendMessage(firstPlayerWriter, Prompt.SEND_OPPONENT_MOVE);
					sendMessage(secondPlayerWriter, message);
				} else {
					sendMessage(firstPlayerWriter, message);
					sendMessage(secondPlayerWriter, Prompt.SEND_OPPONENT_MOVE);
				}

				firstPlayerTurn = !firstPlayerTurn;
			}

		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(final PrintWriter out, final String msg) {
		out.println(msg);
	}

	public String receiveMessage(final BufferedReader in) {
		try {
			return in.readLine();
		} catch (final IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void stopServer() {
		try {
			firstPlayerSocket.close();
			firstPlayerReader.close();
			firstPlayerWriter.close();

			secondPlayerSocket.close();
			secondPlayerReader.close();
			secondPlayerWriter.close();

			socket.close();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
}
