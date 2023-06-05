package pl.kielce.tu.drylofudala.authentication;

import org.jetbrains.annotations.NotNull;
import pl.kielce.tu.drylofudala.entity.Player;
import pl.kielce.tu.drylofudala.persistance.IRepository;

public class AuthenticationService implements IAuthenticationService {
	private final IRepository<Player> playerRepository;
	private final IHasher hasher;

	public AuthenticationService(IRepository<Player> playerRepository, IHasher hasher) {
		this.playerRepository = playerRepository;
		this.hasher = hasher;
	}

	@Override
	public Player register(@NotNull String nickname, @NotNull String password) {
		byte[] salt = hasher.generateSalt();
		String hashedPassword = hasher.hashPassword(password, salt);
		Player player = new Player(nickname, hashedPassword, salt);
		playerRepository.save(player);
		return player;
	}

	@Override
	public AuthenticationResult login(@NotNull String nickname, @NotNull String password) {
		return null;
	}

	@Override
	public ValidationResult isPasswordValid(@NotNull String password) {
		StringBuilder validationMessage = new StringBuilder();
		if (isTooShort(password, AuthenticationConfig.MIN_PASSWORD_LENGTH)) {
			validationMessage.append(ValidationMessage.PASSWORD_TOO_SHORT);
			validationMessage.append('\n');
		}
		if (isTooLong(password, AuthenticationConfig.MAX_PASSWORD_LENGTH)) {
			validationMessage.append(ValidationMessage.PASSWORD_TOO_LONG);
			validationMessage.append('\n');
		}
		if (containsLowercase(password)) {
			validationMessage.append(ValidationMessage.PASSWORD_WITHOUT_LOWERCASE);
			validationMessage.append('\n');
		}
		if (containsUppercase(password)) {
			validationMessage.append(ValidationMessage.PASSWORD_WITHOUT_UPPERCASE);
			validationMessage.append('\n');
		}
		if (containsSpecialCharacters(password)) {
			validationMessage.append(ValidationMessage.PASSWORD_WITHOUT_SPECIAL_CHARACTER);
			validationMessage.append('\n');
		}
		if (containsNumber(password)) {
			validationMessage.append(ValidationMessage.PASSWORD_WITHOUT_NUMBER);
			validationMessage.append('\n');
		}

		return !validationMessage.isEmpty() ? new ValidationResult(false, validationMessage.toString()) : new ValidationResult(true);
	}

	@Override
	public ValidationResult isNicknameValid(@NotNull String nickname) {
		StringBuilder validationMessage = new StringBuilder();
		if (isTooShort(nickname, AuthenticationConfig.MIN_NICKNAME_LENGTH)) {
			validationMessage.append(ValidationMessage.NICKNAME_TOO_SHORT);
		} else if (isTooLong(nickname, AuthenticationConfig.MAX_NICKNAME_LENGTH)) {
			validationMessage.append(ValidationMessage.NICKNAME_TOO_LONG);
		}
		return !validationMessage.isEmpty() ? new ValidationResult(false, validationMessage.toString()) : new ValidationResult(true);
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
