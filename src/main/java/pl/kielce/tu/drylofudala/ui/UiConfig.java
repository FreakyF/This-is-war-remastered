package pl.kielce.tu.drylofudala.ui;

import java.awt.Font;

public final class UiConfig {
	public static final String FONT_FAMILY = "Comic Sans MS";
	public static final Font COPYRIGHT_FONT = new Font(FONT_FAMILY, Font.PLAIN, 24);
	public static final Font TITLE_FONT = new Font(FONT_FAMILY, Font.PLAIN, 72);
	public static final Font BUTTON_FONT = new Font(FONT_FAMILY, Font.PLAIN, 56);
	public static final Font BUTTON_RETURN_FONT = new Font(FONT_FAMILY, Font.PLAIN, 20);
	public static final int WINDOW_MIN_WIDTH = 1920;
	public static final int WINDOW_MIN_HEIGHT = 1080;
	public static final int BUTTON_DEFAULT_WIDTH = 300;
	public static final int BUTTON_DEFAULT_HEIGHT = 100;

	private UiConfig() {
	}
}
