package pl.kielce.tu.drylofudala.ui;

import pl.kielce.tu.drylofudala.persistance.resource.ResourceRepository;

import java.awt.Image;

public class UiResource {
	public static final Image VIEW_BACKGROUND_IMAGE = ResourceRepository.getInstance().getImageFromPath("graphics\\UI\\background.png");
	public static final Image BOARD_BACKGROUND_IMAGE = ResourceRepository.getInstance().getImageFromPath("graphics\\UI\\HandPanel.png");
	public static final Image BUTTON_BACKGROUND_IMAGE = ResourceRepository.getInstance().getImageFromPath("graphics\\UI\\HandPanel.png");
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
	public static final String INPUT_LABEL_NICKNAME = "Nickname";
	public static final String INPUT_LABEL_PASSWORD = "Password";
	public static final String BUTTON_OK_TEXT = "Ok";

	private UiResource() {
	}
}
