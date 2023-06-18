package pl.kielce.tu.drylofudala.ui.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class RowPanel extends JPanel {
	private static final int WIDTH = 1300;
	private static final int HEIGHT = 160;
	private final transient Image backgroundImage;

	public RowPanel(final Image bgImage) {
		backgroundImage = bgImage;
		setOpaque(false);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		setSize(WIDTH, HEIGHT);
	}

	public void addCards(final List<CardLabel> cards) {
		for (final var card : cards) {
			add(card);
		}
	}

	public void addCard(final CardLabel card) {
		add(card);
		revalidate();
		repaint();
	}

	public void removeCard(final CardLabel card) {
		remove(card);
		revalidate();
		repaint();
	}

	@Override
	public int getWidth() {
		return WIDTH;
	}

	@Override
	public int getHeight() {
		return HEIGHT;
	}

	public void setPosition(final int x, final int y) {
		final var horizontalPos = x - WIDTH / 2;
		final var verticalPos = y - HEIGHT / 2;
		setLocation(horizontalPos, verticalPos);
	}

	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, backgroundImage.getWidth(this), backgroundImage.getHeight(this), this);
	}
}
