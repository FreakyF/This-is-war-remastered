package pl.kielce.tu.drylofudala.ui.view;

import pl.kielce.tu.drylofudala.ui.UiConfig;
import pl.kielce.tu.drylofudala.ui.UiResource;
import pl.kielce.tu.drylofudala.ui.model.ImagePanel;
import pl.kielce.tu.drylofudala.ui.service.UiComponentCreator;
import pl.kielce.tu.drylofudala.ui.view.factory.IView;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;


public class GameView implements IView {
	@Override
	public JPanel createView() {
		return initializeView();
	}

	private JPanel initializeView() {
		final JPanel view = new JPanel(new BorderLayout());

		ImagePanel backgroundPanel = UiComponentCreator.createBackgroundPanel();
		view.add(backgroundPanel);

		JPanel contentPanel = createContentPanel();
		backgroundPanel.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;

		backgroundPanel.add(contentPanel, gbc);

		view.setVisible(true);
		return view;
	}

	private JPanel createContentPanel() {
		JPanel contentPanel = new JPanel(new GridBagLayout());

		contentPanel.setOpaque(false);
		contentPanel.setBackground(Color.cyan);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(10, 10, 10, 10);

		contentPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

		// menuPanel
		JPanel menuPanel = createMenuPanel();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.01;
		gbc.weighty = 1.0;

		contentPanel.add(menuPanel, gbc);

		// boardPanel
		JPanel boardPanel = createBoardPanel();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 0.6;
		gbc.weighty = 0;
		contentPanel.add(boardPanel, gbc);

		// statsPanel
		JPanel statsPanel = createStatsPanel();
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.weightx = 0.05;
		gbc.weighty = 0;
		contentPanel.add(statsPanel, gbc);

		return contentPanel;
	}

	private JPanel createStatsPanel() {
		JPanel statsPanel = new JPanel(new GridBagLayout());

		statsPanel.setOpaque(false);
		statsPanel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(10, 10, 10, 10);

		// enemyStatsPanel
		JPanel enemyStatsPanel = createEnemyStatsPanel();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 0.5;
		statsPanel.add(enemyStatsPanel, gbc);

		// whichTurnPanel
		JPanel whichTurnPanel = createWhichTurnPanel();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 0.01;
		statsPanel.add(whichTurnPanel, gbc);

		// playerStatsPanel
		JPanel playerStatsPanel = createPlayerStatsPanel();
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 1;
		gbc.weighty = 0.5;
		statsPanel.add(playerStatsPanel, gbc);

		return statsPanel;
	}

	private JPanel createWhichTurnPanel() {
		JPanel whichTurnPanel = new JPanel(new GridBagLayout());

		whichTurnPanel.setOpaque(false);
		whichTurnPanel.setBorder(BorderFactory.createLineBorder(Color.PINK, 2));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(10, 10, 10, 10);

		// playerTurnLabel
		JLabel playerTurnLabel = UiComponentCreator.createLabel("Player Turn: ", UiConfig.COPYRIGHT_FONT);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1; // Set weightx to 1 to prevent horizontal centering
		whichTurnPanel.add(playerTurnLabel, gbc);

		return whichTurnPanel;
	}

	private JPanel createEnemyStatsPanel() { // TODO: Fix the panels scaling so that it wont depend on its content.
		JPanel enemyStatsPanel = new JPanel(new GridBagLayout());

		enemyStatsPanel.setOpaque(false);
		enemyStatsPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.insets = new Insets(10, 10, 10, 10);

		// enemyNickLabel
		JLabel enemyNicknameLabel = UiComponentCreator.createLabel("enemy nickname", UiConfig.COPYRIGHT_FONT);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1; // Set weightx to 1 to prevent horizontal centering
		gbc.weighty = 1; // Set weighty to 1 to prevent vertical centering
		enemyStatsPanel.add(enemyNicknameLabel, gbc);

		// enemyPointsLabel
		JLabel enemyPointsLabel = UiComponentCreator.createLabel("enemy points", UiConfig.COPYRIGHT_FONT);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1; // Set weightx to 1 to prevent horizontal centering
		gbc.weighty = 1; // Set weighty to 1 to prevent vertical centering
		enemyStatsPanel.add(enemyPointsLabel, gbc);

		// enemyLivesLeftLabel
		JLabel enemyLivesLeftLabel = UiComponentCreator.createLabel("enemy lives left", UiConfig.COPYRIGHT_FONT);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 1; // Set weightx to 1 to prevent horizontal centering
		gbc.weighty = 0; // Set weighty to 1 to prevent vertical centering
		enemyStatsPanel.add(enemyLivesLeftLabel, gbc);

		return enemyStatsPanel;
	}

	private JPanel createPlayerStatsPanel() {
		JPanel playerStatsPanel = new JPanel(new GridBagLayout());

		playerStatsPanel.setOpaque(false);
		playerStatsPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.SOUTHWEST;
		gbc.insets = new Insets(10, 10, 10, 10);

		// playerNickLabel
		JLabel playerNicknameLabel = UiComponentCreator.createLabel("player nickname", UiConfig.COPYRIGHT_FONT);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 1; // Set weightx to 1 to prevent horizontal centering
		gbc.weighty = 1; // Set weighty to 1 to prevent vertical centering
		playerStatsPanel.add(playerNicknameLabel, gbc);

		// playerPointsLabel
		JLabel playerPointsLabel = UiComponentCreator.createLabel("player points", UiConfig.COPYRIGHT_FONT);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1; // Set weightx to 1 to prevent horizontal centering
		gbc.weighty = 1; // Set weighty to 1 to prevent vertical centering
		playerStatsPanel.add(playerPointsLabel, gbc);

		// playerLivesLeftLabel
		JLabel playerLivesLeftLabel = UiComponentCreator.createLabel("enemy lives left", UiConfig.COPYRIGHT_FONT);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1; // Set weightx to 1 to prevent horizontal centering
		gbc.weighty = 0; // Set weighty to 1 to prevent vertical centering
		playerStatsPanel.add(playerLivesLeftLabel, gbc);

		return playerStatsPanel;
	}

	private JPanel createMenuPanel() {
		JPanel menuPanel = new JPanel(new GridBagLayout());

		menuPanel.setOpaque(false);
		menuPanel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 2));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.insets = new Insets(10, 0, 10, 0);

		JButton passTurnButton = UiComponentCreator.createButton(UiResource.BUTTON_PASS_TURN_TEXT);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weighty = 0; // Set weighty to 0 to prevent vertical centering
		menuPanel.add(passTurnButton, gbc);

		JButton surrenderButton = UiComponentCreator.createButton(UiResource.BUTTON_SURRENDER_TEXT);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weighty = 0; // Set weighty to 0 to prevent vertical centering
		menuPanel.add(surrenderButton, gbc);

		JButton exitButton = UiComponentCreator.createButton(UiResource.BUTTON_EXIT_TEXT);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weighty = 1; // Set weighty to 1 to push the buttons to the top
		menuPanel.add(exitButton, gbc);

		return menuPanel;
	}

	private JPanel createBoardPanel() {
		JPanel boardPanel = new JPanel(new GridBagLayout());

		boardPanel.setOpaque(false);
		boardPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(10, 10, 10, 10);

		// enemyBoardPanel
		JPanel enemyBoardPanel = createEnemyBoardPanel();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 0.4;
		boardPanel.add(enemyBoardPanel, gbc);

		// playerBoardPanel
		JPanel playerBoardPanel = createPlayerBoardPanel();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 0.6;
		boardPanel.add(playerBoardPanel, gbc);

		return boardPanel;
	}

	private JPanel createEnemyBoardPanel() {
		JPanel enemyBoardPanel = new JPanel(new GridBagLayout());

		enemyBoardPanel.setOpaque(false);
		enemyBoardPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(10, 10, 10, 10);

		// enemyRangedPanel
		JPanel enemyRangedPanel = createEnemyRangedPanel();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 0.5;
		enemyBoardPanel.add(enemyRangedPanel, gbc);

		// enemyMeleePanel
		JPanel enemyMeleePanel = createEnemyMeleePanel();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 0.5;
		enemyBoardPanel.add(enemyMeleePanel, gbc);

		return enemyBoardPanel;
	}

	private JPanel createEnemyMeleePanel() {
		JPanel enemyMeleePanel = new JPanel(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		// TODO: Change the cardPanels background for the correct one.
		ImagePanel background = UiComponentCreator.createBackgroundPanel();
		gbc.weightx = 1;
		gbc.weighty = 1;
		enemyMeleePanel.add(background, gbc);

		enemyMeleePanel.setOpaque(false);
		enemyMeleePanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

		return enemyMeleePanel;
	}

	private JPanel createEnemyRangedPanel() {
		JPanel enemyRangedPanel = new JPanel(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;

		ImagePanel background = UiComponentCreator.createBackgroundPanel();
		gbc.weightx = 1;
		gbc.weighty = 1;
		enemyRangedPanel.add(background, gbc);

		enemyRangedPanel.setOpaque(false);
		enemyRangedPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

		return enemyRangedPanel;
	}

	private JPanel createPlayerBoardPanel() {
		JPanel playerBoardPanel = new JPanel(new GridBagLayout());

		playerBoardPanel.setOpaque(false);
		playerBoardPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(10, 10, 10, 10);

		// playerMeleePanel
		JPanel playerMeleePanel = createPlayerMeleePanel();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 0.3;
		playerBoardPanel.add(playerMeleePanel, gbc);

		// playerRangedPanel
		JPanel playerRangedPanel = createPlayerRangedPanel();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 0.3;
		playerBoardPanel.add(playerRangedPanel, gbc);

		// playerDeckPanel
		JPanel playerDeckPanel = createPlayerDeckPanel();
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 1;
		gbc.weighty = 0.3;
		playerBoardPanel.add(playerDeckPanel, gbc);

		return playerBoardPanel;
	}

	private JPanel createPlayerDeckPanel() {
		JPanel playerDeckPanel = new JPanel(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;

		ImagePanel background = UiComponentCreator.createBackgroundPanel();
		gbc.weightx = 1;
		gbc.weighty = 1;
		playerDeckPanel.add(background, gbc);

		playerDeckPanel.setOpaque(false);
		playerDeckPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

		return playerDeckPanel;
	}

	private JPanel createPlayerRangedPanel() {
		JPanel playerRangedPanel = new JPanel(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;

		ImagePanel background = UiComponentCreator.createBackgroundPanel();
		gbc.weightx = 1;
		gbc.weighty = 1;
		playerRangedPanel.add(background, gbc);

		playerRangedPanel.setOpaque(false);
		playerRangedPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

		return playerRangedPanel;
	}

	private JPanel createPlayerMeleePanel() {
		JPanel playerMeleePanel = new JPanel(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;

		ImagePanel background = UiComponentCreator.createBackgroundPanel();
		gbc.weightx = 1;
		gbc.weighty = 1;
		playerMeleePanel.add(background, gbc);

		playerMeleePanel.setOpaque(false);
		playerMeleePanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

		return playerMeleePanel;
	}
}
