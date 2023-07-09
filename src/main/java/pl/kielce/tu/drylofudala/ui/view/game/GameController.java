package pl.kielce.tu.drylofudala.ui.view.game;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import pl.kielce.tu.drylofudala.model.PositionType;
import pl.kielce.tu.drylofudala.ui.UiResource;
import pl.kielce.tu.drylofudala.ui.model.CardLabel;

public class GameController {
	private static final String STRING_FORMAT_PATTERN_STRING_NUMBER = "<html><body><b>%s</b> %d</body></html>";

	private boolean playerChosenCard;
	private CardLabel cardSelectedByPlayer;

	private final GameView gameView;

	private final List<CardLabel> playerCardLabels = new ArrayList<>();

	private final List<CardLabel> enemyCardLabels = new ArrayList<>();

	public GameController(final boolean playerChosenCard, final GameView gameView) {
		this.playerChosenCard = playerChosenCard;
		this.gameView = gameView;
	}

	public ActionListener createActionListenerForCardLabel(final CardLabel cardLabel) {
		playerChosenCard = true;
		cardSelectedByPlayer = cardLabel;
		final var positionType = cardSelectedByPlayer.getPositionType();
		return switch (positionType) {
			case MELEE -> e -> {
				gameView.playerDeckRow.removeCard(cardSelectedByPlayer);
				gameView.playerMeleeRow.addCard(cardSelectedByPlayer);
			};
			case RANGED -> e -> {
				gameView.playerDeckRow.removeCard(cardSelectedByPlayer);
				gameView.playerRangedRow.addCard(cardSelectedByPlayer);
			};
		};
	}

	public void updatePlayerPoints() {
		final var points = playerCardLabels.stream().mapToInt(CardLabel::getPoints).sum();
		gameView.playerPointsLabel.setText(String.format(STRING_FORMAT_PATTERN_STRING_NUMBER, UiResource.LABEL_POINTS_TEXT, points));
	}

	public void updateEnemyPoints() {
		final var enemyPoints = enemyCardLabels.stream().mapToInt(CardLabel::getPoints).sum();
		gameView.enemyPointsLabel.setText(String.format(STRING_FORMAT_PATTERN_STRING_NUMBER, UiResource.LABEL_POINTS_TEXT, enemyPoints));
	}

	public void updatePlayerLives(final int lives) {
		gameView.playerLivesLabel.setText(String.format(STRING_FORMAT_PATTERN_STRING_NUMBER, UiResource.LABEL_LIVES_TEXT, lives));
	}

	public void updateEnemyLives(final int lives) {
		gameView.enemyLivesLabel.setText(String.format(STRING_FORMAT_PATTERN_STRING_NUMBER, UiResource.LABEL_LIVES_TEXT, lives));
	}

	public void updatePlayerTurn(final boolean isPlayerTurn) {
		gameView.turnLabel.setText(isPlayerTurn ? gameView.playerTurnText : gameView.enemyTurnText);
		if (isPlayerTurn) {
			unblockPlayerCards();
			return;
		}
		blockPlayerCards();
	}

	public void addEnemyCard(final CardLabel cardLabel) {
		enemyCardLabels.add(cardLabel);
		cardLabel.blockCard();
		if (cardLabel.getPositionType().equals(PositionType.MELEE)) {
			gameView.enemyMeleeRow.addCard(cardLabel);
		}

		if (cardLabel.getPositionType().equals(PositionType.RANGED)) {
			gameView.enemyRangedRow.addCard(cardLabel);
		}

		updateEnemyPoints();
	}

	public void addPlayerCard(final CardLabel cardLabel) {
		playerCardLabels.add(cardLabel);
		cardLabel.blockCard();
		if (cardLabel.getPositionType().equals(PositionType.MELEE)) {
			gameView.enemyMeleeRow.addCard(cardLabel);
		}

		if (cardLabel.getPositionType().equals(PositionType.RANGED)) {
			gameView.enemyRangedRow.addCard(cardLabel);
		}

		updatePlayerPoints();
	}

	public void blockPlayerCards() {
		playerCardLabels.forEach(CardLabel::blockCard);
	}

	public void unblockPlayerCards() {
		playerCardLabels.forEach(CardLabel::unblockCard);
	}

	public boolean hasPlayerChosenCard() {
		return playerChosenCard;
	}

	public CardLabel getCardSelectedByPlayer() {
		return cardSelectedByPlayer;
	}

	public void resetCardSelectedByPlayer() {
		playerChosenCard = false;
		cardSelectedByPlayer = null;
	}
}
