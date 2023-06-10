package pl.kielce.tu.drylofudala.ui.view;

import pl.kielce.tu.drylofudala.ui.model.ImagePanel;
import pl.kielce.tu.drylofudala.ui.service.UiComponentCreator;
import pl.kielce.tu.drylofudala.ui.view.factory.IView;

import javax.swing.BorderFactory;
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
		gbc.weightx = 0.2;
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
		gbc.weightx = 0.2;
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

		// playerStatsPanel
		JPanel playerStatsPanel = createPlayerStatsPanel();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 0.5;
		statsPanel.add(playerStatsPanel, gbc);

		return statsPanel;
	}

	private JPanel createEnemyStatsPanel() {
		JPanel enemyStatsPanel = new JPanel(new GridBagLayout());

		enemyStatsPanel.setOpaque(false);
		enemyStatsPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

		return enemyStatsPanel;
	}

	private JPanel createPlayerStatsPanel() {
		JPanel playerStatsPanel = new JPanel(new GridBagLayout());

		playerStatsPanel.setOpaque(false);
		playerStatsPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));

		return playerStatsPanel;
	}

	private JPanel createMenuPanel() {
		JPanel menuPanel = new JPanel(new GridBagLayout());

		menuPanel.setOpaque(false);
		menuPanel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 2));

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
		gbc.weighty = 0.5;
		boardPanel.add(enemyBoardPanel, gbc);

		// playerBoardPanel
		JPanel playerBoardPanel = createPlayerBoardPanel();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 0.5;
		boardPanel.add(playerBoardPanel, gbc);

		return boardPanel;
	}

	private JPanel createEnemyBoardPanel() {
		JPanel enemyBoardPanel = new JPanel(new GridBagLayout());

//		enemyBoardPanel.setPreferredSize(new Dimension(170, 75));
		enemyBoardPanel.setOpaque(false);
		enemyBoardPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

		return enemyBoardPanel;
	}

	private JPanel createPlayerBoardPanel() {
		JPanel playerBoardPanel = new JPanel(new GridBagLayout());

//		playerBoardPanel.setPreferredSize(new Dimension(170, 75));
		playerBoardPanel.setOpaque(false);
		playerBoardPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));

		return playerBoardPanel;
	}
}
