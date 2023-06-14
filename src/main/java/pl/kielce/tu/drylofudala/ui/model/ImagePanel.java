package pl.kielce.tu.drylofudala.ui.model;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	protected final transient Image backgroundImage;

	public ImagePanel(final Image backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, this);
	}
}
