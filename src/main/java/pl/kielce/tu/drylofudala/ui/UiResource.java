package pl.kielce.tu.drylofudala.ui;

import pl.kielce.tu.drylofudala.persistance.resource.ResourceRepository;

import java.awt.Image;
import java.net.URL;

public class UiResource {
	public static final URL BUTTON_BACKGROUND_IMAGE_RESOURCE = ResourceRepository.getInstance().getResourceFromPath("graphics\\UI\\HandPanel.png");
	public static final Image VIEW_BACKGROUND_IMAGE_RESOURCE = ResourceRepository.getInstance().getImageFromPath("graphics\\UI\\background.png");

	private UiResource() {
	}
}
