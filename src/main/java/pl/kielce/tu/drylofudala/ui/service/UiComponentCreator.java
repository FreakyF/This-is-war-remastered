package pl.kielce.tu.drylofudala.ui.service;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
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

	public UiComponentCreator(final IResourceRepository resourceRepository) {
		this.resourceRepository = resourceRepository;
	}

	public ImagePanel createBackgroundPanel() {
		final var bgImage = resourceRepository.getImageFromPath(UiResource.VIEW_BACKGROUND_IMAGE_PATH);
		return new ImagePanel(bgImage);
	}

	public ImagePanel createBoardBackgroundPanel() {
		final var bgImage = resourceRepository.getImageFromPath(UiResource.BOARD_BACKGROUND_IMAGE_PATH);
		return new ImagePanel(bgImage);
	}

	public JButton createButton(final String text, final int width, final int height) {
		final JButton button = new JButton(text);

		button.setPreferredSize(new Dimension(width, height));
		button.setFont(UiConfig.BUTTON_FONT);

		final Image buttonImage = resourceRepository.getImageFromPath(UiResource.BUTTON_BACKGROUND_IMAGE_PATH);
		final ImageIcon buttonBackground = new ImageIcon(buttonImage);
		button.setIcon(buttonBackground);

		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.setVerticalTextPosition(SwingConstants.CENTER);

		return button;
	}

	public JLabel createLabel(final String text, final Font fontStyle) {
		final JLabel label = new JLabel(text);

		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.ORANGE);
		label.setFont(fontStyle);

		return label;
	}
}
