package pl.kielce.tu.drylofudala.ui;

import java.awt.Font;
import java.awt.Insets;

public class UiConfig {
	public static final String FONT_FAMILY = "Comic Sans MS";
	public static final Font COPYRIGHT_FONT = new Font(FONT_FAMILY, Font.PLAIN, 24);

	public static final Font TITLE_FONT = new Font(FONT_FAMILY, Font.PLAIN, 72);

	public static final Font BUTTON_FONT = new Font(FONT_FAMILY, Font.PLAIN, 56);
	public static final Insets GBC_INSETS = new Insets(10, 10, 10, 10);

	private UiConfig() {
	}
}
