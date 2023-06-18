package pl.kielce.tu.drylofudala.ui.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class RowPanel extends JPanel {
	private final transient Image backgroundImage;
	private final int width = 1250;
	private final int height = 160;

	public RowPanel(final Image bgImage) {
		backgroundImage = bgImage;
		setOpaque(false);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		setSize(width, height);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setPosition(final int x, final int y) {
		final var horizontalPos = x - width / 2;
		final var verticalPos = y - height / 2;
		setLocation(horizontalPos, verticalPos);
	}

	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, backgroundImage.getWidth(this), backgroundImage.getHeight(this), this);
	}
}
