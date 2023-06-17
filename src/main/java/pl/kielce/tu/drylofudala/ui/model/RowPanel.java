package pl.kielce.tu.drylofudala.ui.model;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.kielce.tu.drylofudala.entity.Card;
import pl.kielce.tu.drylofudala.persistance.repository.card.ICardRepository;
import pl.kielce.tu.drylofudala.persistance.resource.IResourceRepository;

public class RowPanel extends JPanel {
	private static final Logger logger = LogManager.getLogger(RowPanel.class);

	public RowPanel(final ICardRepository cardRepository, final IResourceRepository resourceRepository, final ImagePanel background) {
		setLayout(new GridBagLayout());
		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;

		setOpaque(false);
		setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

		int cardIndex = 0;
		final var cards = cardRepository.findAll().stream().toList();
		for (final Card card : cards) {
			final var pathToCardImg = card.getImageFileName();
			final Image cardImg = resourceRepository.getImageFromPath(pathToCardImg);
			if (cardImg == null) {
				logger.debug("Card {} not found", pathToCardImg);
				continue;
			}
			final ImageIcon cardIcon = new ImageIcon(cardImg);
			final JLabel cardLabel = new JLabel(cardIcon);
			cardLabel.setToolTipText(card.getName() + " - " + card.getPoints() + " points");
			gbc.gridx = cardIndex % 5;
			gbc.gridy = 0;

			background.add(cardLabel, gbc);
			cardIndex++;
		}
		add(background, gbc);
		validate();
	}
}
