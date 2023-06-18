package pl.kielce.tu.drylofudala.ui.view.game;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
	private final IUiComponentCreator uiComponentCreator;
	private IViewNavigationHandler navigationHandler;
	private JPanel view;

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

		int spaceFromTop = 0;
		final var enemyNicknameLabel = uiComponentCreator.createGameViewLabel("01234567890123456", UiConfig.COPYRIGHT_FONT);
		spaceFromTop += 10;
		enemyNicknameLabel.setBounds(horizontalPosition, spaceFromTop, width, height);
		backgroundPanel.add(enemyNicknameLabel);

		final var enemyPointsLabel = uiComponentCreator.createGameViewLabel("Points: 180", UiConfig.COPYRIGHT_FONT);
		spaceFromTop += height;
		enemyPointsLabel.setBounds(horizontalPosition, spaceFromTop, width, height);
		backgroundPanel.add(enemyPointsLabel);

		final var enemyLivesLabel = uiComponentCreator.createGameViewLabel("Lives: 3", UiConfig.COPYRIGHT_FONT);
		spaceFromTop += height;
		enemyLivesLabel.setBounds(horizontalPosition, spaceFromTop, width, height);
		backgroundPanel.add(enemyLivesLabel);

		final var playterTurnLabel = uiComponentCreator.createGameViewLabel("Player turn: ", UiConfig.COPYRIGHT_FONT);
		spaceFromTop += 260;
		playterTurnLabel.setBounds(horizontalPosition, spaceFromTop, width, height);
		backgroundPanel.add(playterTurnLabel);

		int spaceFromBottom = UiConfig.WINDOW_MIN_HEIGHT;
		final var playerNicknameLabel = uiComponentCreator.createGameViewLabel("01234567890123456", UiConfig.COPYRIGHT_FONT);
		spaceFromBottom -= 130;
		playerNicknameLabel.setBounds(horizontalPosition, spaceFromBottom, width, height);
		backgroundPanel.add(playerNicknameLabel);

		final var playerPointsLabel = uiComponentCreator.createGameViewLabel("Points: 180", UiConfig.COPYRIGHT_FONT);
		spaceFromBottom -= height;
		playerPointsLabel.setBounds(horizontalPosition, spaceFromBottom, width, height);
		backgroundPanel.add(playerPointsLabel);

		final var playerLivesLabel = uiComponentCreator.createGameViewLabel("Lives: 3", UiConfig.COPYRIGHT_FONT);
		spaceFromBottom -= height;
		playerLivesLabel.setBounds(horizontalPosition, spaceFromBottom, width, height);
		backgroundPanel.add(playerLivesLabel);
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

		final var surrenderButton = uiComponentCreator.createButton(UiResource.BUTTON_SURRENDER_TEXT);
		surrenderButton.setBounds(10, UiConfig.BUTTON_DEFAULT_HEIGHT + BUTTON_PADDING * buttonPaddingCounter++, UiConfig.BUTTON_DEFAULT_WIDTH, UiConfig.BUTTON_DEFAULT_HEIGHT);

		final var exitButton = uiComponentCreator.createButton(UiResource.BUTTON_EXIT_TEXT);
		exitButton.setBounds(10, 2 * UiConfig.BUTTON_DEFAULT_HEIGHT + BUTTON_PADDING * buttonPaddingCounter, UiConfig.BUTTON_DEFAULT_WIDTH, UiConfig.BUTTON_DEFAULT_HEIGHT);

		backgroundPanel.add(passButton);
		backgroundPanel.add(surrenderButton);
		backgroundPanel.add(exitButton);
	}

	private ActionListener onPassTurnButtonClicked() {
		return e -> {
			// TODO: Implement passing the turn in gameplay.
		};
	}

	private ActionListener onSurrenderButtonCLicked() {
		return e -> {
			// TODO: Implement surrendering in gameplay.
			// TODO: Print which player surrendered.
			JOptionPane.showMessageDialog(view, "PLAYER surrendered!");
		};
	}
}
