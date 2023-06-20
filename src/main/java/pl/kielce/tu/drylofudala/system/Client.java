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
import pl.kielce.tu.drylofudala.system.dto.PlayerMoveDTO;
import pl.kielce.tu.drylofudala.system.dto.StartGameDTO;
import pl.kielce.tu.drylofudala.system.service.prompt.Prompt;
import pl.kielce.tu.drylofudala.ui.model.CardLabel;
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

			sendMessage(player.getName());
			final var startGameDTOJson = in.readLine();
			final StartGameDTO startGameDTO = gson.fromJson(startGameDTOJson, StartGameDTO.class);
			gameView.initializeGame(player.getName(),
					startGameDTO.enemyNickname(),
					startGameDTO.playerTurn(),
					null,
					null,
					null);

			while (true) {
				final String message = receiveMessage();

				if (message.equals(Prompt.YOUR_TURN)) {
					gameView.updatePlayerTurn(true);
					logger.debug("My turn! {}", player.getName());
					logger.debug("Waiting for player to choose the card");
					while (true) {
						if (gameView.hasPlayerChosenCard()) {
							break;
						}
					}
					final var cardSelectedByPlayer = gameView.getCardSelectedByPlayer();
					final var playerMoveDTO = gson.toJson(new PlayerMoveDTO(cardSelectedByPlayer), PlayerMoveDTO.class);
					gameView.addPlayerCard(cardSelectedByPlayer);
					sendMessage(playerMoveDTO);
					gameView.resetCardSelectedByPlayer();
				}

				if (message.equals(Prompt.OPPONENT_TURN)) {
					logger.debug("It is not my turn! {}", player.getName());
					gameView.updatePlayerTurn(false);
					final var enemyCard = gson.fromJson(receiveMessage(), CardLabel.class);
					gameView.addEnemyCard(enemyCard);
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
