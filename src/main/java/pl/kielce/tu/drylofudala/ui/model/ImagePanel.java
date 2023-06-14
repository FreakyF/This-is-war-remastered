package pl.kielce.tu.drylofudala.ui.model;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	private final transient Image backgroundImage;
	private final JFrame parentWindow;

	public ImagePanel(final Image backgroundImage, final JFrame parentWindow) {
		this.backgroundImage = backgroundImage;
		this.parentWindow = parentWindow;
	}

	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, parentWindow.getWidth(), parentWindow.getHeight(), this);
	}
}
