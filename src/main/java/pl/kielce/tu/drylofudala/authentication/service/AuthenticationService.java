package pl.kielce.tu.drylofudala.authentication.service;

import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import pl.kielce.tu.drylofudala.authentication.AuthenticationConfig;
import pl.kielce.tu.drylofudala.authentication.hasher.IHasher;
import pl.kielce.tu.drylofudala.authentication.result.AuthenticationResult;
import pl.kielce.tu.drylofudala.authentication.result.RegistrationResult;
import pl.kielce.tu.drylofudala.authentication.result.ValidationResult;
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
			return RegistrationResult.NICKNAME_ALREADY_TAKEN;
		}

		byte[] salt = hasher.generateSalt();
		String hashedPassword = hasher.hashPassword(password, salt);

		var newPlayer = new Player(nickname, hashedPassword, salt);
		playerRepository.save(newPlayer);
		return RegistrationResult.getSuccessResult(newPlayer);
	}

	@Override
	public AuthenticationResult login(@NotNull String nickname, @NotNull String password) {
		Player existingPlayer = playerRepository.getPlayerByNickname(nickname);
		if (existingPlayer == null) {
			return AuthenticationResult.PLAYER_DOES_NOT_EXISTS;
		}

		if (!hasher.verifyPassword(password, existingPlayer.getHashedPassword(), existingPlayer.getPasswordSalt())) {
			return AuthenticationResult.INVALID_PASSWORD;
		}

		return AuthenticationResult.SUCCESS;
	}

	@Override
	public ValidationResult isPasswordValid(@NotNull String password) {
		List<String> validationMessages = new ArrayList<>();

		if (isTooShort(password, AuthenticationConfig.MIN_PASSWORD_LENGTH)) {
			validationMessages.add(ValidationResult.PASSWORD_TOO_SHORT);
		} else if (isTooLong(password, AuthenticationConfig.MAX_PASSWORD_LENGTH)) {
			validationMessages.add(ValidationResult.PASSWORD_TOO_LONG);
		}

		if (!containsLowercase(password)) {
			validationMessages.add(ValidationResult.PASSWORD_WITHOUT_LOWERCASE);
		}

		if (!containsUppercase(password)) {
			validationMessages.add(ValidationResult.PASSWORD_WITHOUT_UPPERCASE);
		}
		if (!containsSpecialCharacters(password)) {
			validationMessages.add(ValidationResult.PASSWORD_WITHOUT_SPECIAL_CHARACTER);
		}
		if (!containsNumber(password)) {
			validationMessages.add(ValidationResult.PASSWORD_WITHOUT_NUMBER);
		}

		return !validationMessages.isEmpty()
				? new ValidationResult(false, validationMessages)
				: new ValidationResult(true, null);
	}

	@Override
	public ValidationResult isNicknameValid(@NotNull String nickname) {
		List<String> validationMessages = new ArrayList<>();

		if (isTooShort(nickname, AuthenticationConfig.MIN_NICKNAME_LENGTH)) {
			validationMessages.add(ValidationResult.NICKNAME_TOO_SHORT);
		} else if (isTooLong(nickname, AuthenticationConfig.MAX_NICKNAME_LENGTH)) {
			validationMessages.add(ValidationResult.NICKNAME_TOO_LONG);
		}

		return !validationMessages.isEmpty()
				? new ValidationResult(false, validationMessages)
				: new ValidationResult(true, null);
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
