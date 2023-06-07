package pl.kielce.tu.drylofudala.authentication.result;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.kielce.tu.drylofudala.authentication.AuthenticationConfig;

public record ValidationResult(@NotNull boolean valid, @Nullable List<String> messages) {
	public static final String PASSWORD_TOO_SHORT = "Password is too short. Minimum length is: " + AuthenticationConfig.MIN_PASSWORD_LENGTH;
	public static final String PASSWORD_TOO_LONG = "Password is too long. Maximum length is: " + AuthenticationConfig.MAX_PASSWORD_LENGTH;
	public static final String PASSWORD_WITHOUT_LOWERCASE = "Password does not contains at least one lowercase character";
	public static final String PASSWORD_WITHOUT_UPPERCASE = "Password does not contains at least one uppercase character";
	public static final String PASSWORD_WITHOUT_SPECIAL_CHARACTER = "Password does not contains at least one special character";
	public static final String PASSWORD_WITHOUT_NUMBER = "Password does not contains at least one number character";
	public static final String NICKNAME_TOO_SHORT = "Nickname is too short. Minimum length is: " + AuthenticationConfig.MIN_NICKNAME_LENGTH;
	public static final String NICKNAME_TOO_LONG = "Nickname is too long. Maximum length is: " + AuthenticationConfig.MAX_NICKNAME_LENGTH;
}
