package pl.kielce.tu.drylofudala.ui.model;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RowPanel extends JPanel {
	private final List<JLabel> cardLabels;

	public RowPanel(final List<JLabel> cardLabels, final ImagePanel background) {
		this.cardLabels = cardLabels;
		setLayout(new GridBagLayout());
		setOpaque(false);

		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 1;
		gbc.weighty = 1;

		int cardIndex = 0;
		for (final JLabel cardLabel : cardLabels) {
			gbc.gridx = cardIndex;
			gbc.gridy = 0;
			background.add(cardLabel, gbc);
			cardIndex++;
		}

		add(background, gbc);
		validate();
	}

}
