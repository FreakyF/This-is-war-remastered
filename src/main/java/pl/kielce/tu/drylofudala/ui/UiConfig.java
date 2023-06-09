package pl.kielce.tu.drylofudala.ui;

import pl.kielce.tu.drylofudala.persistance.resource.ResourceRepository;

import java.awt.Font;
import java.net.URL;

public class UiConfig {
	public static final String FONT_FAMILY = "JetBrains Mono";
	public static final Font COPYRIGHT_FONT = new Font(FONT_FAMILY, Font.PLAIN, 24);

	public static final Font TITLE_FONT = new Font(FONT_FAMILY, Font.PLAIN, 144);

	public static final Font BUTTON_FONT = new Font(FONT_FAMILY, Font.PLAIN, 56);

	private UiConfig() {
	}
}
