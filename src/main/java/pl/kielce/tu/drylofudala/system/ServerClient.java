package pl.kielce.tu.drylofudala.system;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

public class ServerClient {
    private static final Logger logger = LogManager.getLogger(ServerClient.class);

    public static void main(String[] args) throws IOException {
        CountDownLatch serverStartedLatch;
        Server server = new Server();
        serverStartedLatch = new CountDownLatch(1);

        Thread serverThread = new Thread(() -> {
            try {
                server.start();
                serverStartedLatch.countDown();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        serverThread.start();

        // Connect to server on localhost, port 8000
        Socket socket = new Socket("localhost", 8000);
        try (socket) {
            // Create input and output streams to server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            // Send a message to the server
            out.println("Hello, Server!");

            // Read the response from the server
            String response = in.readLine();
            if (response != null) {
                logger.debug("Response from server: {}", response);
            } else {
                logger.debug("Response from server is null!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.stop();
        serverThread.interrupt();
    }
}