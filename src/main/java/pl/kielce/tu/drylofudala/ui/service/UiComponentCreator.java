package pl.kielce.tu.drylofudala.ui.service;

import pl.kielce.tu.drylofudala.ui.UiConfig;
import pl.kielce.tu.drylofudala.ui.UiResource;
import pl.kielce.tu.drylofudala.ui.model.ImagePanel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

public class UiComponentCreator {
	private UiComponentCreator() {

	}

	public static ImagePanel createBackgroundPanel() {
		final Image backgroundImage = UiResource.VIEW_BACKGROUND_IMAGE;
		return new ImagePanel(backgroundImage);
	}

	public static ImagePanel createBoardBackgroundPanel() {
		final Image backgroundImage = UiResource.BOARD_BACKGROUND_IMAGE;
		return new ImagePanel(backgroundImage);
	}
	public static JButton createButton(String text, int width, int height) {
		final JButton button = new JButton(text);

		button.setPreferredSize(new Dimension(width, height));
		button.setFont(UiConfig.BUTTON_FONT);

		ImageIcon buttonBackground = new ImageIcon(UiResource.BUTTON_BACKGROUND_IMAGE);
		button.setIcon(buttonBackground);

		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.setVerticalTextPosition(SwingConstants.CENTER);

		return button;
	}

	public static JLabel createLabel(String text, Font fontStyle) {
		final JLabel label = new JLabel(text);

		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.ORANGE);
		label.setFont(fontStyle);

		return label;
	}
}
