package pl.kielce.tu.drylofudala.authentication.result;

import java.io.*;
import java.util.*;
import org.jetbrains.annotations.*;
import pl.kielce.tu.drylofudala.authentication.*;

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
		Properties properties = new Properties();
		try (InputStream inputStream = ValidationResult.class.getResourceAsStream("/validationMessages.properties")) {
			properties.load(inputStream);
		} catch (IOException e) {
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
