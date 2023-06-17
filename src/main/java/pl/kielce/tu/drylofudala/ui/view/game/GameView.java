package pl.kielce.tu.drylofudala.ui.view.game;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import pl.kielce.tu.drylofudala.persistance.repository.card.ICardRepository;
import pl.kielce.tu.drylofudala.persistance.resource.IResourceRepository;
import pl.kielce.tu.drylofudala.ui.MainWindow;
import pl.kielce.tu.drylofudala.ui.service.navigation_handler.IViewNavigationHandler;
import pl.kielce.tu.drylofudala.ui.service.ui_component_creator.IUiComponentCreator;
import pl.kielce.tu.drylofudala.ui.service.ui_component_creator.UiComponentCreator;
import pl.kielce.tu.drylofudala.ui.view.factory.IView;

public class GameView implements IView {
	private static final String VIEW_NAME = "Game";
	private final ICardRepository cardRepository;
	private IViewNavigationHandler navigationHandler;
	private IResourceRepository resourceRepository;
	private JFrame parentWindow;
	private IUiComponentCreator uiComponentCreator;

	public GameView(final ICardRepository cardRepository) {
		this.cardRepository = cardRepository;
	}

	@Override
	public String getViewName() {
		return VIEW_NAME;
	}

	@Override
	public JPanel createView(final MainWindow parentWindow, final IViewNavigationHandler navigationHandler, final IResourceRepository resourceRepository) {
		this.parentWindow = parentWindow;
		this.navigationHandler = navigationHandler;
		this.resourceRepository = resourceRepository;
		uiComponentCreator = new UiComponentCreator(resourceRepository);
		return getGamePanel();
	}

	private JPanel getGamePanel() {
		final var gamePanel = new JPanel(new GridBagLayout());
		final var gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;

		final var firstColumn = new JPanel(new GridBagLayout());
		firstColumn.setBackground(Color.GREEN);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weighty = 1;
		gbc.weightx = 0.2;
		gamePanel.add(firstColumn, gbc);

		final var secondColumn = new JPanel(new GridBagLayout());
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weighty = 1;
		gbc.weightx = 0.6;
		secondColumn.setBackground(Color.RED);
		gamePanel.add(secondColumn, gbc);

		final var thirdColumn = new JPanel(new GridBagLayout());
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.weighty = 1;
		gbc.weightx = 0.2;
		thirdColumn.setBackground(Color.BLUE);
		gamePanel.add(thirdColumn, gbc);

		gamePanel.setVisible(true);
		return gamePanel;
	}

}
