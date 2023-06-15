package pl.kielce.tu.drylofudala.system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread {
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
				// TODO: Implement receiving and sending messages - GAMEPLAY
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
