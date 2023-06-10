package pl.kielce.tu.drylofudala.ui;

import pl.kielce.tu.drylofudala.persistance.resource.ResourceRepository;

import java.awt.Image;
import java.net.URL;

public class UiResource {
	public static final Image VIEW_BACKGROUND_IMAGE_RESOURCE = ResourceRepository.getInstance().getImageFromPath("graphics\\UI\\background.png");
	public static final URL BUTTON_BACKGROUND_IMAGE_RESOURCE = ResourceRepository.getInstance().getResourceFromPath("graphics\\UI\\HandPanel.png");
	public static final String GAME_TITLE = "This Is War";
	public static final String SUBTITLE_LOGIN = "Login";
	public static final String SUBTITLE_REGISTER = "Register";
	public static final String BUTTON_LOGIN_TEXT = "Login";
	public static final String BUTTON_REGISTER_TEXT = "Register";
	public static final String BUTTON_PASS_TURN_TEXT = "Pass turn";
	public static final String BUTTON_SURRENDER_TEXT = "Surrender";
	public static final String BUTTON_EXIT_TEXT = "Exit";
	public static final String INPUT_LABEL_NICKNAME = "Nickname";
	public static final String INPUT_LABEL_PASSWORD = "Password";

	private UiResource() {
	}
}
