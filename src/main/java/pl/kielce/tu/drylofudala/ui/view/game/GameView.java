package pl.kielce.tu.drylofudala.ui.view.game;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import pl.kielce.tu.drylofudala.ui.UiConfig;
import pl.kielce.tu.drylofudala.ui.UiResource;
import pl.kielce.tu.drylofudala.ui.model.CardLabel;
import pl.kielce.tu.drylofudala.ui.model.ImagePanel;
import pl.kielce.tu.drylofudala.ui.model.RowPanel;
import pl.kielce.tu.drylofudala.ui.service.navigation_handler.IViewNavigationHandler;
import pl.kielce.tu.drylofudala.ui.service.ui_component_creator.IUiComponentCreator;
import pl.kielce.tu.drylofudala.ui.view.factory.IView;

public class GameView implements IView {
	private static final String NAME = "Game";
	private static final String STRING_FORMAT_PATTERN_STRING_NUMBER = "%s %d";
	private static final String STRING_FORMAT_PATTERN_STRING_STRING = "%s %s";
	private final IUiComponentCreator uiComponentCreator;
	private IViewNavigationHandler navigationHandler;
	private JLabel turnLabel;
	private JLabel playerLivesLabel;
	private JLabel playerPointsLabel;
	private JLabel playerNicknameLabel;
	private String playerTurnText;
	private JLabel enemyLivesLabel;
	private JLabel enemyPointsLabel;
	private JLabel enemyNicknameLabel;
	private String enemyTurnText;
	private JPanel view;
	private ActionListener onPassButtonClicked;
	private ActionListener onSurrenderButtonClicked;
	private ActionListener onExitButtonClicked;

	public GameView(final IUiComponentCreator uiComponentCreator) {
		this.uiComponentCreator = uiComponentCreator;
	}

	@Override
	public String getViewName() {
		return NAME;
	}

	@Override
	public JPanel createView(final IViewNavigationHandler navigationHandler) {
		this.navigationHandler = navigationHandler;
		return initializeView();
	}

	public void initializeGame(final String playerNickname,
	                           final String enemyNickname,
	                           final boolean isPlayerTurn,
	                           final ActionListener onPassButtonClicked,
	                           final ActionListener onSurrenderButtonClicked,
	                           final ActionListener onExitButtonClicked) {
		playerNicknameLabel.setText(playerNickname);
		enemyNicknameLabel.setText(enemyNickname);
		playerTurnText = String.format(STRING_FORMAT_PATTERN_STRING_STRING, UiResource.LABEL_PLAYER_TURN_TEXT, playerNickname);
		enemyTurnText = String.format(STRING_FORMAT_PATTERN_STRING_STRING, UiResource.LABEL_PLAYER_TURN_TEXT, enemyNickname);
		turnLabel.setText(isPlayerTurn ? playerTurnText : enemyTurnText);
		this.onPassButtonClicked = onPassButtonClicked;
		this.onSurrenderButtonClicked = onSurrenderButtonClicked;
		this.onExitButtonClicked = onExitButtonClicked;
	}

	public void updatePlayerPoints(final int points) {
		playerPointsLabel.setText(String.format(STRING_FORMAT_PATTERN_STRING_NUMBER, UiResource.LABEL_POINTS_TEXT, points));
	}

	public void updateEnemyPoints(final int points) {
		enemyPointsLabel.setText(String.format(STRING_FORMAT_PATTERN_STRING_NUMBER, UiResource.LABEL_POINTS_TEXT, points));
	}

	public void updatePlayerLives(final int lives) {
		playerLivesLabel.setText(String.format(STRING_FORMAT_PATTERN_STRING_NUMBER, UiResource.LABEL_LIVES_TEXT, lives));
	}

	public void updateEnemyLives(final int lives) {
		enemyLivesLabel.setText(String.format(STRING_FORMAT_PATTERN_STRING_NUMBER, UiResource.LABEL_LIVES_TEXT, lives));
	}

	public void updatePlayerTurn(final boolean isPlayerTurn) {
		turnLabel.setText(isPlayerTurn ? playerTurnText : enemyTurnText);
	}

	private void addCardLabels(final List<CardLabel> cardLabels, final ImagePanel background) {
		final GridBagConstraints cardLabelConstraints = new GridBagConstraints();
		cardLabelConstraints.fill = GridBagConstraints.NONE;
		cardLabelConstraints.weightx = 1;
		cardLabelConstraints.weighty = 1;
		int cardIndex = 0;
		for (final JLabel cardLabel : cardLabels) {
			cardLabelConstraints.gridx = cardIndex;
			cardLabelConstraints.gridy = 0;
			background.add(cardLabel, cardLabelConstraints);
			cardIndex++;
		}
	}

	private JPanel initializeView() {
		view = new JPanel(new BorderLayout());

		final ImagePanel backgroundPanel = uiComponentCreator.createBackgroundPanel();
		backgroundPanel.setLayout(null);

		addMenuPanel(backgroundPanel);
		addRowsPanels(backgroundPanel);
		addStatsPanel(backgroundPanel);

		view.add(backgroundPanel);
		view.setVisible(true);
		return view;
	}

	private void addStatsPanel(final ImagePanel backgroundPanel) {
		final int horizontalPosition = UiConfig.WINDOW_MIN_WIDTH - 320;
		final int width = 300;
		final int height = 50;

		addEnemyStats(backgroundPanel, horizontalPosition, width, height);
		addPlayerTurnLabel(backgroundPanel, height);
		addPlayerStats(backgroundPanel, horizontalPosition, width, height);
	}

	private void addPlayerTurnLabel(final ImagePanel backgroundPanel, final int height) {
		final var playerTurnLabelInitialText = String.format(STRING_FORMAT_PATTERN_STRING_STRING, UiResource.LABEL_PLAYER_TURN_TEXT, "");
		turnLabel = uiComponentCreator.createGameViewLabel(playerTurnLabelInitialText, UiConfig.COPYRIGHT_FONT);
		turnLabel.setHorizontalAlignment(SwingConstants.CENTER);
		final int playerTurnLabelWidth = 450;
		turnLabel.setBounds(UiConfig.WINDOW_MIN_WIDTH / 2 - playerTurnLabelWidth / 2, 370, playerTurnLabelWidth, height);
		backgroundPanel.add(turnLabel);
	}

	private void addPlayerStats(final ImagePanel backgroundPanel, final int horizontalPosition, final int width, final int height) {
		int spaceFromBottom = UiConfig.WINDOW_MIN_HEIGHT;

		playerNicknameLabel = uiComponentCreator.createGameViewLabel("", UiConfig.COPYRIGHT_FONT);
		spaceFromBottom -= 130;
		playerNicknameLabel.setBounds(horizontalPosition, spaceFromBottom, width, height);
		backgroundPanel.add(playerNicknameLabel);

		final var playerPointsInitialText = String.format(STRING_FORMAT_PATTERN_STRING_NUMBER, UiResource.LABEL_POINTS_TEXT, 0);
		playerPointsLabel = uiComponentCreator.createGameViewLabel(playerPointsInitialText, UiConfig.COPYRIGHT_FONT);
		spaceFromBottom -= height;
		playerPointsLabel.setBounds(horizontalPosition, spaceFromBottom, width, height);
		backgroundPanel.add(playerPointsLabel);

		final var playerLivesInitialText = String.format(STRING_FORMAT_PATTERN_STRING_NUMBER, UiResource.LABEL_LIVES_TEXT, 0);
		playerLivesLabel = uiComponentCreator.createGameViewLabel(playerLivesInitialText, UiConfig.COPYRIGHT_FONT);
		spaceFromBottom -= height;
		playerLivesLabel.setBounds(horizontalPosition, spaceFromBottom, width, height);
		backgroundPanel.add(playerLivesLabel);
	}

	private void addEnemyStats(final ImagePanel backgroundPanel, final int horizontalPosition, final int width, final int height) {
		int spaceFromTop = 0;
		enemyNicknameLabel = uiComponentCreator.createGameViewLabel("", UiConfig.COPYRIGHT_FONT);
		spaceFromTop += 10;
		enemyNicknameLabel.setBounds(horizontalPosition, spaceFromTop, width, height);
		backgroundPanel.add(enemyNicknameLabel);

		final var enemyPointsInitialText = String.format(STRING_FORMAT_PATTERN_STRING_NUMBER, UiResource.LABEL_POINTS_TEXT, 0);
		enemyPointsLabel = uiComponentCreator.createGameViewLabel(enemyPointsInitialText, UiConfig.COPYRIGHT_FONT);
		spaceFromTop += height;
		enemyPointsLabel.setBounds(horizontalPosition, spaceFromTop, width, height);
		backgroundPanel.add(enemyPointsLabel);

		final var enemyLivesInitialText = String.format(STRING_FORMAT_PATTERN_STRING_NUMBER, UiResource.LABEL_LIVES_TEXT, 0);
		enemyLivesLabel = uiComponentCreator.createGameViewLabel(enemyLivesInitialText, UiConfig.COPYRIGHT_FONT);
		spaceFromTop += height;
		enemyLivesLabel.setBounds(horizontalPosition, spaceFromTop, width, height);
		backgroundPanel.add(enemyLivesLabel);
	}

	private void addRowsPanels(final ImagePanel backgroundPanel) {
		final var playerRowBgImage = uiComponentCreator.createPlayerRowBackgroundImage();

		final int spaceBetweenGroupOfRows = 10;
		final int spaceBelowPlayerDeckRow = 10;
		final int spaceBetweenEnemyAndPlayerRows = 85;
		final int spaceBetweenPlayerDeckAndRows = 65;

		int spaceFromBottom = 0;

		final int centerX = UiConfig.WINDOW_MIN_WIDTH / 2;
		final var playerDeckRow = new RowPanel(playerRowBgImage);
		spaceFromBottom += UiConfig.WINDOW_MIN_HEIGHT - playerDeckRow.getHeight() - spaceBelowPlayerDeckRow;
		playerDeckRow.setPosition(centerX, spaceFromBottom);
		backgroundPanel.add(playerDeckRow);

		final var playerRangedRow = new RowPanel(playerRowBgImage);
		spaceFromBottom -= playerRangedRow.getHeight() + spaceBetweenPlayerDeckAndRows;
		playerRangedRow.setPosition(centerX, spaceFromBottom);
		backgroundPanel.add(playerRangedRow);

		final var playerMeleeRow = new RowPanel(playerRowBgImage);
		spaceFromBottom -= playerMeleeRow.getHeight() + spaceBetweenGroupOfRows;
		playerMeleeRow.setPosition(centerX, spaceFromBottom);
		backgroundPanel.add(playerMeleeRow);

		final var enemyRowBgImage = uiComponentCreator.createEnemyRowBackgroundImage();

		final var enemyMeleeRow = new RowPanel(enemyRowBgImage);

		spaceFromBottom -= enemyMeleeRow.getHeight() + spaceBetweenEnemyAndPlayerRows;
		enemyMeleeRow.setPosition(centerX, spaceFromBottom);
		backgroundPanel.add(enemyMeleeRow);

		final var enemyRangedRow = new RowPanel(enemyRowBgImage);
		spaceFromBottom -= enemyRangedRow.getHeight() + spaceBetweenGroupOfRows;
		enemyRangedRow.setPosition(centerX, spaceFromBottom);
		backgroundPanel.add(enemyRangedRow);
	}

	private void addMenuPanel(final ImagePanel backgroundPanel) {
		final int BUTTON_PADDING = 10;
		int buttonPaddingCounter = 1;

		final var passButton = uiComponentCreator.createButton(UiResource.BUTTON_PASS_TURN_TEXT);
		passButton.setBounds(10, BUTTON_PADDING * buttonPaddingCounter++, UiConfig.BUTTON_DEFAULT_WIDTH, UiConfig.BUTTON_DEFAULT_HEIGHT);
		passButton.addActionListener(onPassTurnButtonClicked());
		backgroundPanel.add(passButton);

		final var surrenderButton = uiComponentCreator.createButton(UiResource.BUTTON_SURRENDER_TEXT);
		surrenderButton.addActionListener(onSurrenderButtonClicked());
		surrenderButton.setBounds(10, UiConfig.BUTTON_DEFAULT_HEIGHT + BUTTON_PADDING * buttonPaddingCounter++, UiConfig.BUTTON_DEFAULT_WIDTH, UiConfig.BUTTON_DEFAULT_HEIGHT);
		backgroundPanel.add(surrenderButton);

		final var exitButton = uiComponentCreator.createButton(UiResource.BUTTON_EXIT_TEXT);
		exitButton.setBounds(10, 2 * UiConfig.BUTTON_DEFAULT_HEIGHT + BUTTON_PADDING * buttonPaddingCounter, UiConfig.BUTTON_DEFAULT_WIDTH, UiConfig.BUTTON_DEFAULT_HEIGHT);
		exitButton.addActionListener(onExitButtonClicked());
		backgroundPanel.add(exitButton);
	}

	private ActionListener onPassTurnButtonClicked() {
		return e -> {
			if (onPassButtonClicked != null) {
				onPassButtonClicked.actionPerformed(e);
			}
			navigationHandler.navigateToUserView().actionPerformed(e);
		};
	}

	private ActionListener onSurrenderButtonClicked() {
		return e -> {
			JOptionPane.showMessageDialog(view, "PLAYER surrendered!");
			if (onSurrenderButtonClicked != null) {
				onSurrenderButtonClicked.actionPerformed(e);
			}
			navigationHandler.navigateToUserView().actionPerformed(e);
		};
	}

	private ActionListener onExitButtonClicked() {
		return e -> {
			if (onExitButtonClicked != null) {
				onExitButtonClicked.actionPerformed(e);
			}
			navigationHandler.navigateToUserView().actionPerformed(e);
		};
	}
}
