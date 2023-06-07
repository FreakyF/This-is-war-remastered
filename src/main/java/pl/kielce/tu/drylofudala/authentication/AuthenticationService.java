package pl.kielce.tu.drylofudala.authentication;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import pl.kielce.tu.drylofudala.entity.Player;
import pl.kielce.tu.drylofudala.persistance.repository.player.IPlayerRepository;

public class AuthenticationService implements IAuthenticationService {
	private final IPlayerRepository playerRepository;
	private final IHasher hasher;

	public AuthenticationService(IPlayerRepository playerRepository, IHasher hasher) {
		this.playerRepository = playerRepository;
		this.hasher = hasher;
	}

	@Override
	public RegistrationResult register(@NotNull String nickname, @NotNull String password) {
		if (playerRepository.isNicknameTaken(nickname)) {
			return new RegistrationResult(
					RegistrationResultType.NICKNAME_ALREADY_TAKEN,
					RegistrationResultMessage.NICKNAME_ALREADY_TAKEN,
					null
			);
		}

		byte[] salt = generateSalt();
		String hashedPassword = hasher.hashPassword(password, salt);

		var newPlayer = new Player(nickname, hashedPassword, salt);
		playerRepository.save(newPlayer);
		return new RegistrationResult(
				RegistrationResultType.SUCCESS,
				null,
				newPlayer
		);
	}

	private byte[] generateSalt() {
		byte[] salt = new byte[AuthenticationConfig.SALT_LENGTH];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(salt);

		return salt;
	}

	@Override
	public AuthenticationResult login(@NotNull String nickname, @NotNull String password) {
		// TODO: Implement
		return null;
	}

	@Override
	public ValidationResult isPasswordValid(@NotNull String password) {
		List<String> validationMessages = new ArrayList<>();

		if (isTooShort(password, AuthenticationConfig.MIN_PASSWORD_LENGTH)) {
			validationMessages.add(ValidationMessage.PASSWORD_TOO_SHORT);
		} else if (isTooLong(password, AuthenticationConfig.MAX_PASSWORD_LENGTH)) {
			validationMessages.add(ValidationMessage.PASSWORD_TOO_LONG);
		}

		if (!containsLowercase(password)) {
			validationMessages.add(ValidationMessage.PASSWORD_WITHOUT_LOWERCASE);
		}

		if (!containsUppercase(password)) {
			validationMessages.add(ValidationMessage.PASSWORD_WITHOUT_UPPERCASE);
		}
		if (!containsSpecialCharacters(password)) {
			validationMessages.add(ValidationMessage.PASSWORD_WITHOUT_SPECIAL_CHARACTER);
		}
		if (!containsNumber(password)) {
			validationMessages.add(ValidationMessage.PASSWORD_WITHOUT_NUMBER);
		}

		return !validationMessages.isEmpty() ? new ValidationResult(false, validationMessages) : new ValidationResult(true);
	}

	@Override
	public ValidationResult isNicknameValid(@NotNull String nickname) {
		List<String> validationMessages = new ArrayList<>();

		if (isTooShort(nickname, AuthenticationConfig.MIN_NICKNAME_LENGTH)) {
			validationMessages.add(ValidationMessage.NICKNAME_TOO_SHORT);
		} else if (isTooLong(nickname, AuthenticationConfig.MAX_NICKNAME_LENGTH)) {
			validationMessages.add(ValidationMessage.NICKNAME_TOO_LONG);
		}

		return !validationMessages.isEmpty() ? new ValidationResult(false, validationMessages) : new ValidationResult(true);
	}

	private boolean isTooShort(final String text, final int minLength) {
		return text.length() < minLength;
	}

	private boolean isTooLong(final String text, final int maxLength) {
		return text.length() > maxLength;
	}

	private boolean containsLowercase(final String password) {
		return password.matches(".*[a-z].*");
	}

	private boolean containsUppercase(final String password) {
		return password.matches(".*[A-Z].*");
	}

	private boolean containsSpecialCharacters(final String password) {
		return password.matches(".*[^a-zA-Z0-9].*");
	}

	private boolean containsNumber(final String password) {
		return password.matches(".*\\d.*");
	}
}
