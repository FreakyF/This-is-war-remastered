package pl.kielce.tu.drylofudala.ui.service;

import pl.kielce.tu.drylofudala.persistance.resource.ResourceRepository;
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
import java.net.URL;

public class UiComponentCreator {
	private UiComponentCreator() {

	}

	public static ImagePanel createBackgroundPanel() {
		final Image backgroundImage = UiResource.VIEW_BACKGROUND_IMAGE_RESOURCE;
		return new ImagePanel(backgroundImage);
	}
	public static JButton createButton(String text) {
		final JButton button = new JButton(text);

		button.setPreferredSize(new Dimension(300, 100));
		button.setFont(UiConfig.BUTTON_FONT);

		ImageIcon buttonBackground = new ImageIcon(UiResource.BUTTON_BACKGROUND_IMAGE_RESOURCE);
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
