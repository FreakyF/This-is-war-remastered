package pl.kielce.tu.drylofudala.ui.model;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import pl.kielce.tu.drylofudala.entity.Card;
import pl.kielce.tu.drylofudala.ui.MainWindow;

public class CardLabel extends JLabel implements MouseListener {
	private final Card card;
	private final MainWindow mainWindow;

	public CardLabel(final Card card, final Image cardImg, final MainWindow mainWindow) {
		super(new ImageIcon(cardImg));
		this.mainWindow = mainWindow;
		this.card = card;
		setToolTipText(card.getName() + " - " + card.getPoints() + " points");
		addMouseListener(this);
	}

	@Override
	public void mouseClicked(final MouseEvent e) {
		// TODO: Implement moving card
		JOptionPane.showMessageDialog(mainWindow, card.getName());
	}

	@Override
	public void mousePressed(final MouseEvent e) {

	}

	@Override
	public void mouseReleased(final MouseEvent e) {

	}

	@Override
	public void mouseEntered(final MouseEvent e) {

	}

	@Override
	public void mouseExited(final MouseEvent e) {
	}
}
