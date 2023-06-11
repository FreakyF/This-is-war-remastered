package pl.kielce.tu.drylofudala.ui;

import pl.kielce.tu.drylofudala.persistance.resource.ResourceRepository;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class UiConfig {
	Font customFont;
	// TODO: Change font type that suppports foreign symbols.
	public static final String FONT_FAMILY = "Call Of Ops Duty";
	public static final Font COPYRIGHT_FONT = new Font(FONT_FAMILY, Font.PLAIN, 24);

	public static final Font TITLE_FONT = new Font(FONT_FAMILY, Font.PLAIN, 72);

	public static final Font BUTTON_FONT = new Font(FONT_FAMILY, Font.PLAIN, 56);
	public static final Insets GBC_INSETS = new Insets(10, 10, 10, 10);

	private UiConfig() {
	}

	static {
		Font customFont;
		try {
			URI x = ResourceRepository.getInstance().getResourceFromPath("CallOfOpsDuty.otf").toURI();
			customFont = Font.createFont(Font.TRUETYPE_FONT, new File(x));

		} catch (FontFormatException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(customFont);
	}
}
