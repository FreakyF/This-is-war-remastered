package pl.kielce.tu.drylofudala.system;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

			final var gson = new Gson();

			while (true) {
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
