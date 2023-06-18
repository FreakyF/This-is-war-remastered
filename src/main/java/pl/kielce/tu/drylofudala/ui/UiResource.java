package pl.kielce.tu.drylofudala.ui;

import java.nio.file.Path;

public final class UiResource {
	public static final String GAME_TITLE = "This Is War";
	public static final String IP_ADDRESS_TEXT = "IP address";
	public static final String PORT_NUMBER_TEXT = "Port number";
	public static final String SUBTITLE_LOGIN = "Login";
	public static final String SUBTITLE_REGISTER = "Register";
	public static final String BUTTON_LOGIN_TEXT = "Login";
	public static final String BUTTON_CONNECT_TEXT = "Connect";
	public static final String BUTTON_REGISTER_TEXT = "Register";
	public static final String BUTTON_PASS_TURN_TEXT = "Pass turn";
	public static final String BUTTON_SURRENDER_TEXT = "Surrender";
	public static final String BUTTON_EXIT_TEXT = "Exit";
	public static final String BUTTON_RETURN_SYMBOL = "<-";
	public static final String INPUT_LABEL_NICKNAME = "Nickname";
	public static final String INPUT_LABEL_PASSWORD = "Password";
	public static final String GRAPHICS_FOLDER = "graphics";
	public static final String CARDS_FOLDER = "cards";
	public static final String LABEL_POINTS_TEXT = "POINTS:";
	public static final String LABEL_LIVES_TEXT = "LIVES:";
	public static final String LABEL_PLAYER_TURN_TEXT = "PLAYER TURN:";
	private static final String UI_FOLDER = "UI";
	public static final String VIEW_BACKGROUND_IMAGE_PATH = Path.of(GRAPHICS_FOLDER, UI_FOLDER, "background.png").toString();
	public static final String PLAYER_ROW_BACKGROUND_IMAGE_PATH = Path.of(GRAPHICS_FOLDER, UI_FOLDER, "playerRowPanelBackground.png").toString();
	public static final String ENEMY_ROW_BACKGROUND_IMAGE_PATH = Path.of(GRAPHICS_FOLDER, UI_FOLDER, "enemyRowPanelBackground.png").toString();
	public static final String BUTTON_BACKGROUND_IMAGE_PATH = Path.of(GRAPHICS_FOLDER, UI_FOLDER, "buttonBackground.png").toString();

	private UiResource() {
	}
}
