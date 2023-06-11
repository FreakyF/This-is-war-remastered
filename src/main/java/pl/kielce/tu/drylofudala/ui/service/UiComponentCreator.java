package pl.kielce.tu.drylofudala.ui.service;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import pl.kielce.tu.drylofudala.persistance.resource.IResourceRepository;
import pl.kielce.tu.drylofudala.ui.UiConfig;
import pl.kielce.tu.drylofudala.ui.UiResource;
import pl.kielce.tu.drylofudala.ui.model.ImagePanel;

public class UiComponentCreator {
	private final IResourceRepository resourceRepository;

	public UiComponentCreator(IResourceRepository resourceRepository) {
		this.resourceRepository = resourceRepository;
	}

	public ImagePanel createBackgroundPanel() {
		var bgImage = resourceRepository.getImageFromPath(UiResource.VIEW_BACKGROUND_IMAGE_PATH);
		return new ImagePanel(bgImage);
	}

	public ImagePanel createBoardBackgroundPanel() {
		var bgImage = resourceRepository.getImageFromPath(UiResource.BOARD_BACKGROUND_IMAGE_PATH);
		return new ImagePanel(bgImage);
	}

	public JButton createButton(String text, int width, int height) {
		final JButton button = new JButton(text);

		button.setPreferredSize(new Dimension(width, height));
		button.setFont(UiConfig.BUTTON_FONT);

		ImageIcon buttonBackground = new ImageIcon(UiResource.BUTTON_BACKGROUND_IMAGE_PATH);
		button.setIcon(buttonBackground);

		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.setVerticalTextPosition(SwingConstants.CENTER);

		return button;
	}

	public JLabel createLabel(String text, Font fontStyle) {
		final JLabel label = new JLabel(text);

		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.ORANGE);
		label.setFont(fontStyle);

		return label;
	}
}
