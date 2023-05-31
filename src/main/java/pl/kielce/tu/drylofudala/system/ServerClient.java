package pl.kielce.tu.drylofudala.system;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

public class ServerClient {
    private static final Logger logger = LogManager.getLogger(ServerClient.class);

    public static void main(String[] args) throws IOException {
        Result serverResult = startAndWaitForServer();

        // Connect to server on localhost, port 8000
        Socket firstClientSocket = new Socket("localhost", 8000);
        logger.debug("First client connecting to server on port: {}", firstClientSocket.getLocalPort());
        handleServerConnection(firstClientSocket, serverResult.clientsFinishedLatch());

        Socket secondClientSocket = new Socket("localhost", 8000);
        logger.debug("First client connecting to server on port: {}", secondClientSocket.getLocalPort());
        handleServerConnection(secondClientSocket, serverResult.clientsFinishedLatch());

        stopServerWithClientCompletion(serverResult.server(), serverResult.serverStoppedLatch(), serverResult.clientsFinishedLatch(), serverResult.serverThread());
    }

    @NotNull
    private static Result startAndWaitForServer() {
        Server server = new Server();
        CountDownLatch serverStartedLatch = new CountDownLatch(1);
        CountDownLatch serverStoppedLatch = new CountDownLatch(1);
        CountDownLatch clientsFinishedLatch = new CountDownLatch(2);

        Thread serverThread = new Thread(() -> {
            try {
                server.start();
                serverStartedLatch.countDown();
                serverStoppedLatch.await();
            } catch (IOException | InterruptedException e) {
                logger.error("An error occurred while starting the server: ", e);
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        });
        serverThread.start();
        return new Result(server, serverStoppedLatch, clientsFinishedLatch, serverThread);
    }

    private record Result(Server server, CountDownLatch serverStoppedLatch, CountDownLatch clientsFinishedLatch, Thread serverThread) {
    }

    private static void stopServerWithClientCompletion(Server server, CountDownLatch serverStoppedLatch, CountDownLatch clientsFinishedLatch, Thread serverThread) {
        serverStoppedLatch.countDown(); // Signal that the server has stopped
        try {
            clientsFinishedLatch.await(); // Wait for clients to finish before interrupting server thread
        } catch (InterruptedException e) {
            logger.error("An error occurred while starting the server: ", e);
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
        server.stop();
        serverThread.interrupt();
    }

    private static void handleServerConnection(Socket socket, CountDownLatch clientsFinishedLatch) {
        Thread clientThread = new Thread(() -> {
            try (socket) {
                // Create input and output streams to the client
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                sendMessage(out);
                readMessage(in);
            } catch (IOException e) {
                logger.error("An error occurred while handling the server connection: ", e);
                e.printStackTrace();
            } finally {
                clientsFinishedLatch.countDown();
            }
        });
        clientThread.start();
    }

    private static void readMessage(BufferedReader in) throws IOException {
        // Read the response from the server
        String response = in.readLine();
        if (response != null) {
            logger.debug("Response from server: {}", response);
        } else {
            logger.debug("Response from server is null!");
        }
    }

    private static void sendMessage(PrintWriter out) {
        // Send a message to the server
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("command", "Hello");
        out.println(jsonObject);
    }
}