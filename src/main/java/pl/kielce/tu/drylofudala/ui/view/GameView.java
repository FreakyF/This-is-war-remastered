package pl.kielce.tu.drylofudala.ui.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import pl.kielce.tu.drylofudala.persistance.resource.IResourceRepository;
import pl.kielce.tu.drylofudala.ui.MainWindow;
import pl.kielce.tu.drylofudala.ui.UiConfig;
import pl.kielce.tu.drylofudala.ui.UiResource;
import pl.kielce.tu.drylofudala.ui.model.ImagePanel;
import pl.kielce.tu.drylofudala.ui.service.navigation_handler.IViewNavigationHandler;
import pl.kielce.tu.drylofudala.ui.service.ui_component_creator.UiComponentCreator;
import pl.kielce.tu.drylofudala.ui.view.factory.IView;

public class GameView implements IView {
	private static final String NAME = "Game";
	private UiComponentCreator uiComponentCreator;
	private MainWindow parentWindow;
	private IViewNavigationHandler navigationHandler;
	private JPanel view;

	@Override
	public String getViewName() {
		return NAME;
	}

	@Override
	public JPanel createView(final MainWindow parentWindow, final IViewNavigationHandler navigationHandler, final IResourceRepository resourceRepository) {
		this.parentWindow = parentWindow;
		this.navigationHandler = navigationHandler;
		uiComponentCreator = new UiComponentCreator(resourceRepository);
		return initializeView();
	}

	private JPanel initializeView() {
		view = new JPanel(new BorderLayout());

		final ImagePanel backgroundPanel = uiComponentCreator.createBackgroundPanel();
		view.add(backgroundPanel);

		final JPanel contentPanel = createContentPanel();
		backgroundPanel.setLayout(new GridBagLayout());

		final GridBagConstraints gbc = new GridBagConstraints();
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
		final JPanel contentPanel = new JPanel(new GridBagLayout());

		contentPanel.setOpaque(false);
		contentPanel.setBackground(Color.cyan);

		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(10, 10, 10, 10);

		contentPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

		final JPanel menuPanel = createMenuPanel();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.01;
		gbc.weighty = 1.0;

		contentPanel.add(menuPanel, gbc);

		final JPanel boardPanel = createBoardPanel();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 0.6;
		gbc.weighty = 0;
		contentPanel.add(boardPanel, gbc);

		final JPanel statsPanel = createStatsPanel();
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.weightx = 0.05;
		gbc.weighty = 0;
		contentPanel.add(statsPanel, gbc);

		return contentPanel;
	}

	private JPanel createStatsPanel() {
		final JPanel statsPanel = new JPanel(new GridBagLayout());

		statsPanel.setOpaque(false);
		statsPanel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2));

		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(10, 10, 10, 10);

		final JPanel enemyStatsPanel = createEnemyStatsPanel();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 0.5;
		statsPanel.add(enemyStatsPanel, gbc);

		final JPanel whichTurnPanel = createWhichTurnPanel();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 0.01;
		statsPanel.add(whichTurnPanel, gbc);

		final JPanel playerStatsPanel = createPlayerStatsPanel();
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 1;
		gbc.weighty = 0.5;
		statsPanel.add(playerStatsPanel, gbc);

		return statsPanel;
	}

	private JPanel createWhichTurnPanel() {
		final JPanel whichTurnPanel = new JPanel(new GridBagLayout());

		whichTurnPanel.setOpaque(false);
		whichTurnPanel.setBorder(BorderFactory.createLineBorder(Color.PINK, 2));

		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(10, 10, 10, 10);

		final JLabel playerTurnLabel = uiComponentCreator.createLabel("Player Turn: ", UiConfig.COPYRIGHT_FONT);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		whichTurnPanel.add(playerTurnLabel, gbc);

		return whichTurnPanel;
	}

	private JPanel createEnemyStatsPanel() {
		final JPanel enemyStatsPanel = new JPanel(new GridBagLayout());

		enemyStatsPanel.setOpaque(false);
		enemyStatsPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.insets = new Insets(10, 10, 10, 10);

		final JLabel enemyNicknameLabel = uiComponentCreator.createLabel("enemy nickname", UiConfig.COPYRIGHT_FONT);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		enemyStatsPanel.add(enemyNicknameLabel, gbc);

		final JLabel enemyPointsLabel = uiComponentCreator.createLabel("enemy points", UiConfig.COPYRIGHT_FONT);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		enemyStatsPanel.add(enemyPointsLabel, gbc);

		final JLabel enemyLivesLeftLabel = uiComponentCreator.createLabel("enemy lives left", UiConfig.COPYRIGHT_FONT);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 1;
		gbc.weighty = 0;
		enemyStatsPanel.add(enemyLivesLeftLabel, gbc);

		return enemyStatsPanel;
	}

	private JPanel createPlayerStatsPanel() {
		final JPanel playerStatsPanel = new JPanel(new GridBagLayout());

		playerStatsPanel.setOpaque(false);
		playerStatsPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));

		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.SOUTHWEST;
		gbc.insets = new Insets(10, 10, 10, 10);

		final JLabel playerNicknameLabel = uiComponentCreator.createLabel("player nickname", UiConfig.COPYRIGHT_FONT);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 1;
		gbc.weighty = 1;
		playerStatsPanel.add(playerNicknameLabel, gbc);

		final JLabel playerPointsLabel = uiComponentCreator.createLabel("player points", UiConfig.COPYRIGHT_FONT);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		playerStatsPanel.add(playerPointsLabel, gbc);

		final JLabel playerLivesLeftLabel = uiComponentCreator.createLabel("enemy lives left", UiConfig.COPYRIGHT_FONT);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 0;
		playerStatsPanel.add(playerLivesLeftLabel, gbc);

		return playerStatsPanel;
	}

	private JPanel createMenuPanel() {
		final JPanel menuPanel = new JPanel(new GridBagLayout());

		menuPanel.setOpaque(false);
		menuPanel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 2));

		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.insets = new Insets(10, 0, 10, 0);

		final JButton passTurnButton = uiComponentCreator.createButton(UiResource.BUTTON_PASS_TURN_TEXT);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weighty = 0;
		menuPanel.add(passTurnButton, gbc);
		// implement pass the turn
		passTurnButton.addActionListener(onPassTurnButtonClicked());

		final JButton surrenderButton = uiComponentCreator.createButton(UiResource.BUTTON_SURRENDER_TEXT);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weighty = 0;
		menuPanel.add(surrenderButton, gbc);
		// implement surrender
		surrenderButton.addActionListener(onSurrenderButtonCLicked());

		final JButton exitButton = uiComponentCreator.createButton(UiResource.BUTTON_EXIT_TEXT);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weighty = 1;
		menuPanel.add(exitButton, gbc);
		// TODO: Implement auto-disconnect for the second player if one of them exits the game using the exitButton.
		exitButton.addActionListener(navigationHandler.navigateToUserView(parentWindow));

		return menuPanel;
	}

	private JPanel createBoardPanel() {
		final JPanel boardPanel = new JPanel(new GridBagLayout());

		boardPanel.setOpaque(false);
		boardPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));

		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(10, 10, 10, 10);

		final JPanel enemyBoardPanel = createEnemyBoardPanel();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 0.4;
		boardPanel.add(enemyBoardPanel, gbc);

		final JPanel playerBoardPanel = createPlayerBoardPanel();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 0.6;
		boardPanel.add(playerBoardPanel, gbc);

		return boardPanel;
	}

	private JPanel createEnemyBoardPanel() {
		final JPanel enemyBoardPanel = new JPanel(new GridBagLayout());

		enemyBoardPanel.setOpaque(false);
		enemyBoardPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(10, 10, 10, 10);

		final JPanel enemyRangedPanel = createEnemyRangedPanel();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 0.5;
		enemyBoardPanel.add(enemyRangedPanel, gbc);

		final JPanel enemyMeleePanel = createEnemyMeleePanel();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 0.5;
		enemyBoardPanel.add(enemyMeleePanel, gbc);

		return enemyBoardPanel;
	}

	private JPanel createEnemyMeleePanel() {
		final JPanel enemyMeleePanel = new JPanel(new GridBagLayout());

		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		// TODO: Change the cardPanels background for the correct one.
		final ImagePanel background = uiComponentCreator.createBoardBackgroundPanel();
		gbc.weightx = 1;
		gbc.weighty = 1;
		enemyMeleePanel.add(background, gbc);

		enemyMeleePanel.setOpaque(false);
		enemyMeleePanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

		return enemyMeleePanel;
	}

	private JPanel createEnemyRangedPanel() {
		final JPanel enemyRangedPanel = new JPanel(new GridBagLayout());

		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;

		final ImagePanel background = uiComponentCreator.createBoardBackgroundPanel();
		gbc.weightx = 1;
		gbc.weighty = 1;
		enemyRangedPanel.add(background, gbc);

		enemyRangedPanel.setOpaque(false);
		enemyRangedPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

		return enemyRangedPanel;
	}

	private JPanel createPlayerBoardPanel() {
		final JPanel playerBoardPanel = new JPanel(new GridBagLayout());

		playerBoardPanel.setOpaque(false);
		playerBoardPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));

		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(10, 10, 10, 10);

		final JPanel playerMeleePanel = createPlayerMeleePanel();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 0.3;
		playerBoardPanel.add(playerMeleePanel, gbc);

		final JPanel playerRangedPanel = createPlayerRangedPanel();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 0.3;
		playerBoardPanel.add(playerRangedPanel, gbc);

		final JPanel playerDeckPanel = createPlayerDeckPanel();
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 1;
		gbc.weighty = 0.3;
		playerBoardPanel.add(playerDeckPanel, gbc);

		return playerBoardPanel;
	}

	private JPanel createPlayerDeckPanel() {
		final JPanel playerDeckPanel = new JPanel(new GridBagLayout());

		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;

		final ImagePanel background = uiComponentCreator.createBoardBackgroundPanel();
		gbc.weightx = 1;
		gbc.weighty = 1;
		playerDeckPanel.add(background, gbc);

		playerDeckPanel.setOpaque(false);
		playerDeckPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

		return playerDeckPanel;
	}

	private JPanel createPlayerRangedPanel() {
		final JPanel playerRangedPanel = new JPanel(new GridBagLayout());

		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;

		final ImagePanel background = uiComponentCreator.createBoardBackgroundPanel();
		gbc.weightx = 1;
		gbc.weighty = 1;
		playerRangedPanel.add(background, gbc);

		playerRangedPanel.setOpaque(false);
		playerRangedPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

		return playerRangedPanel;
	}

	private JPanel createPlayerMeleePanel() {
		final JPanel playerMeleePanel = new JPanel(new GridBagLayout());

		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;

		final ImagePanel background = uiComponentCreator.createBoardBackgroundPanel();
		gbc.weightx = 1;
		gbc.weighty = 1;
		playerMeleePanel.add(background, gbc);

		playerMeleePanel.setOpaque(false);
		playerMeleePanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

		return playerMeleePanel;
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
