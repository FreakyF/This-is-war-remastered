package pl.kielce.tu.drylofudala.system;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.*;

public class Server {

    private static final Logger logger = LogManager.getLogger(Server.class);

    boolean running = true;

    public void start() throws IOException {

        while (running) {
            // Create server socket listening on port 8000
            ServerSocket serverSocket = new ServerSocket(8000);
            try (serverSocket) {
                // Wait for a connection
                Socket socket = serverSocket.accept();

                // Create input and output streams to client
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                // Read message from client and send it back
                String inputLine = in.readLine();
                if (inputLine != null) {
                    logger.debug("Input from client: {}", inputLine);
                } else {
                    logger.debug("Input from client is null!");
                }
                out.println("Input: " + inputLine);
            } catch (IOException e) {
                stop();
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        running = false;
    }
}