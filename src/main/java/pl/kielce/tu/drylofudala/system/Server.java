package pl.kielce.tu.drylofudala.system;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final Logger logger = LogManager.getLogger(Server.class);

    boolean running = true;

    public void start() throws IOException {
        // Create server socket listening on port 8000
        ServerSocket serverSocket = new ServerSocket(8000);
        logger.debug("Server is listening on port: {}", serverSocket.getLocalPort());
        try (serverSocket) {
            while (running) {
                handleClientConnection(serverSocket);
            }
        }
    }

    private void handleClientConnection(ServerSocket serverSocket) {
        // Wait for a connection
        try (Socket socket = serverSocket.accept()) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            readMessage(in, out);
        } catch (IOException e) {
            stop();
            logger.error("An error occured during client connection: {}", e.getMessage(), e);
            e.printStackTrace();
        }
    }

    private static void readMessage(BufferedReader in, PrintWriter out) throws IOException {
        // Read message from client and send it back
        String inputLine = in.readLine();
        if (inputLine != null) {
            logger.debug("Input from client: {}", inputLine);
            JSONObject jsonObject = new JSONObject(inputLine);
            logger.info(jsonObject.getString("command"));
        } else {
            logger.debug("Input from client is null!");
        }
        out.println("Input: " + inputLine);
    }

    public void stop() {
        running = false;
    }
}