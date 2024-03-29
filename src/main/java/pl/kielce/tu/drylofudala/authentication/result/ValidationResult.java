package pl.kielce.tu.drylofudala.authentication.result;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import org.jetbrains.annotations.Nullable;
import pl.kielce.tu.drylofudala.authentication.AuthenticationConfig;

public record ValidationResult(boolean valid, @Nullable List<String> messages) {
	public static final String PASSWORD_TOO_SHORT;
	public static final String PASSWORD_TOO_LONG;
	public static final String PASSWORD_WITHOUT_LOWERCASE;
	public static final String PASSWORD_WITHOUT_UPPERCASE;
	public static final String PASSWORD_WITHOUT_SPECIAL_CHARACTER;
	public static final String PASSWORD_WITHOUT_NUMBER;
	public static final String NICKNAME_TOO_SHORT;
	public static final String NICKNAME_TOO_LONG;

	static {
		final Properties properties = new Properties();
		try (final InputStream inputStream = ValidationResult.class.getResourceAsStream("/validationMessages.properties")) {
			properties.load(inputStream);
		} catch (final IOException e) {
			e.printStackTrace();
		}

		PASSWORD_TOO_SHORT = String.format(properties.getProperty("PASSWORD_TOO_SHORT"), AuthenticationConfig.MIN_PASSWORD_LENGTH);
		PASSWORD_TOO_LONG = String.format(properties.getProperty("PASSWORD_TOO_LONG"), AuthenticationConfig.MAX_PASSWORD_LENGTH);
		PASSWORD_WITHOUT_LOWERCASE = properties.getProperty("PASSWORD_WITHOUT_LOWERCASE");
		PASSWORD_WITHOUT_UPPERCASE = properties.getProperty("PASSWORD_WITHOUT_UPPERCASE");
		PASSWORD_WITHOUT_SPECIAL_CHARACTER = properties.getProperty("PASSWORD_WITHOUT_SPECIAL_CHARACTER");
		PASSWORD_WITHOUT_NUMBER = properties.getProperty("PASSWORD_WITHOUT_NUMBER");
		NICKNAME_TOO_SHORT = String.format(properties.getProperty("NICKNAME_TOO_SHORT"), AuthenticationConfig.MIN_NICKNAME_LENGTH);
		NICKNAME_TOO_LONG = String.format(properties.getProperty("NICKNAME_TOO_LONG"), AuthenticationConfig.MAX_NICKNAME_LENGTH);
	}

	public String getMessagesAsString() {
		if (messages == null) {
			return "";
		}

		return String.join("\n", messages);
	}
}
