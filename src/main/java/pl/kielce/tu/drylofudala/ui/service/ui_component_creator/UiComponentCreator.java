package pl.kielce.tu.drylofudala.ui.service.ui_component_creator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import pl.kielce.tu.drylofudala.entity.Card;
import pl.kielce.tu.drylofudala.persistance.repository.card.ICardRepository;
import pl.kielce.tu.drylofudala.persistance.resource.IResourceRepository;
import pl.kielce.tu.drylofudala.ui.MainWindow;
import pl.kielce.tu.drylofudala.ui.UiConfig;
import pl.kielce.tu.drylofudala.ui.UiResource;
import pl.kielce.tu.drylofudala.ui.model.ImagePanel;

public class UiComponentCreator implements IUiComponentCreator {

	private static final Logger logger = LogManager.getLogger(UiComponentCreator.class);
	private final IResourceRepository resourceRepository;
	private final ICardRepository cardRepository;
	private final MainWindow mainWindow;

	public UiComponentCreator(final IResourceRepository resourceRepository,
	                          final ICardRepository cardRepository,
	                          final MainWindow mainWindow) {
		this.resourceRepository = resourceRepository;
		this.cardRepository = cardRepository;
		this.mainWindow = mainWindow;
	}

	@NotNull
	private static JLabel createLabelForCard(final Card card, final Image cardImg) {
		final ImageIcon cardIcon = new ImageIcon(cardImg);
		final JLabel cardLabel = new JLabel(cardIcon);
		cardLabel.setToolTipText(card.getName() + " - " + card.getPoints() + " points");
		return cardLabel;
	}

	public List<JLabel> createCardLabels() {
		final var cardsList = cardRepository.findAll().stream().toList();
		final List<JLabel> cardLabels = new ArrayList<>();
		for (final Card card : cardsList) {
			final var pathToCardImg = card.getImageFileName();
			final Image cardImg = resourceRepository.getImageFromPath(pathToCardImg);
			if (cardImg == null) {
				logger.debug("Card {} not found", pathToCardImg);
				continue;
			}
			final var cardLabel = createLabelForCard(card, cardImg);
			cardLabels.add(cardLabel);
		}
		return cardLabels;
	}

	public ImagePanel createBackgroundPanel() {
		final var bgImage = resourceRepository.getImageFromPath(UiResource.VIEW_BACKGROUND_IMAGE_PATH);
		return new ImagePanel(bgImage, mainWindow);
	}

	public ImagePanel createBoardBackgroundPanel() {
		final var bgImage = resourceRepository.getImageFromPath(UiResource.BOARD_BACKGROUND_IMAGE_PATH);
		return new ImagePanel(bgImage, mainWindow);
	}

	public JButton createButton(final String text, final int width, final int height) {
		final JButton button = new JButton(text);

		button.setPreferredSize(new Dimension(width, height));
		button.setFont(UiConfig.BUTTON_FONT);

		final Image buttonImage = resourceRepository.getImageFromPath(UiResource.BUTTON_BACKGROUND_IMAGE_PATH);
		final ImageIcon buttonBackground = new ImageIcon(buttonImage);
		button.setIcon(buttonBackground);

		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.setVerticalTextPosition(SwingConstants.CENTER);

		return button;
	}

	public JButton createButton(final String text) {
		return createButton(text, UiConfig.BUTTON_DEFAULT_WIDTH, UiConfig.BUTTON_DEFAULT_HEIGHT);
	}

	public JButton createReturnButton() {
		final var button = createButton(UiResource.BUTTON_RETURN_SYMBOL, 50, 50);
		button.setFont(UiConfig.BUTTON_RETURN_FONT);
		button.setLocation(10, 10);

		return button;
	}

	public JLabel createLabel(final String text, final Font fontStyle) {
		final JLabel label = new JLabel(text);

		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.ORANGE);
		label.setFont(fontStyle);

		return label;
	}
}
