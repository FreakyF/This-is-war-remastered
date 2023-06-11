package pl.kielce.tu.drylofudala.ui.model;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;

public class ImagePanel extends JPanel {
	protected final transient Image backgroundImage;

	public ImagePanel(Image backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, this);
	}
}
