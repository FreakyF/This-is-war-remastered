package pl.kielce.tu.drylofudala.ui.model;

import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import pl.kielce.tu.drylofudala.entity.Card;
import pl.kielce.tu.drylofudala.model.PositionType;

public class CardLabel extends JLabel implements MouseListener {
	private final transient Card card;
	private boolean blocked;
	private transient ActionListener onCardClicked;

	public CardLabel(final Card card, final Image cardImg) {
		super(new ImageIcon(cardImg));
		this.card = card;
		setToolTipText(card.getName() + " - " + card.getPoints() + " points");
		addMouseListener(this);
	}

	public void addOnClickAction(final ActionListener actionListener) {
		onCardClicked = actionListener;
	}

	public PositionType getPositionType() {
		return card.getPositionType();
	}

	@Override
	public void mouseClicked(final MouseEvent e) {
		// TODO: Implement it. :)
	}

	@Override
	public void mousePressed(final MouseEvent e) {
		// Not used
	}

	@Override
	public void mouseReleased(final MouseEvent e) {
		// Not used
	}

	@Override
	public void mouseEntered(final MouseEvent e) {
		// Not used
	}

	@Override
	public void mouseExited(final MouseEvent e) {
		// Not used
	}
}
