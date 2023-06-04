package pl.kielce.tu.drylofudala.system;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient {
    private static final Logger logger = LogManager.getLogger(GameClient.class);
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private String host;
    private int port;
    private String identity;

    public GameClient(final String host, final int port) {
        this.host = host;
        this.port = port;
    }

    public GameClient(final Socket socket) throws IOException {
        this.socket = socket;
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.output = new PrintWriter(socket.getOutputStream(), true);
        setIdentity(socket.getInetAddress().getHostAddress(), socket.getPort());
    }

    public void connect() throws IOException {
        socket = new Socket(host, port);
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);
        setIdentity(socket.getInetAddress().getHostAddress(), socket.getPort());
    }

    public void send(String message) {
        logger.debug("{} Sending message: {}", identity, message);
        output.println(message);
    }

    public String receive() throws IOException {
        logger.debug("{} is waiting for message", identity);
        final String receivedMessage = input.readLine();
        logger.debug("{} Received message: {}", identity, receivedMessage);
        return receivedMessage;
    }

    public void close() throws IOException {
        socket.close();
        input.close();
        output.close();
    }

    private void setIdentity(String host, int port) {
        this.identity = host + ":" + port + " - ";
    }
}
