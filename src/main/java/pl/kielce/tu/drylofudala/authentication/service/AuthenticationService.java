package pl.kielce.tu.drylofudala.authentication.service;

import jakarta.persistence.NoResultException;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import org.jetbrains.annotations.NotNull;
import pl.kielce.tu.drylofudala.authentication.AuthenticationConfig;
import pl.kielce.tu.drylofudala.authentication.hasher.IHasher;
import pl.kielce.tu.drylofudala.authentication.result.AuthenticationResult;
import pl.kielce.tu.drylofudala.authentication.result.RegistrationResult;
import pl.kielce.tu.drylofudala.authentication.result.ValidationResult;
import pl.kielce.tu.drylofudala.entity.Player;
import pl.kielce.tu.drylofudala.persistance.repository.player.IPlayerRepository;

public class AuthenticationService implements IAuthenticationService {
	private static final Map<Predicate<String>, String> PASSWORD_RULES = Map.of(
			s -> s.length() >= AuthenticationConfig.MIN_PASSWORD_LENGTH, ValidationResult.PASSWORD_TOO_SHORT,
			s -> s.length() <= AuthenticationConfig.MAX_PASSWORD_LENGTH, ValidationResult.PASSWORD_TOO_LONG,
			s -> s.matches(".*[a-z].*"), ValidationResult.PASSWORD_WITHOUT_LOWERCASE,
			s -> s.matches(".*[A-Z].*"), ValidationResult.PASSWORD_WITHOUT_UPPERCASE,
			s -> s.matches(".*[^a-zA-Z0-9].*"), ValidationResult.PASSWORD_WITHOUT_SPECIAL_CHARACTER,
			s -> s.matches(".*\\d.*"), ValidationResult.PASSWORD_WITHOUT_NUMBER
	);
	private static final Map<Predicate<String>, String> NICKNAME_RULES = Map.of(
			s -> s.length() >= AuthenticationConfig.MIN_NICKNAME_LENGTH, ValidationResult.NICKNAME_TOO_SHORT,
			s -> s.length() <= AuthenticationConfig.MAX_NICKNAME_LENGTH, ValidationResult.NICKNAME_TOO_LONG
	);
	private final IPlayerRepository playerRepository;
	private final IHasher hasher;

	public AuthenticationService(final IPlayerRepository playerRepository,
	                             final IHasher hasher) {
		this.playerRepository = playerRepository;
		this.hasher = hasher;
	}

	@Override
	public RegistrationResult register(@NotNull final String nickname, @NotNull final String password) {
		if (playerRepository.isNicknameTaken(nickname)) {
			return RegistrationResult.NICKNAME_ALREADY_TAKEN;
		}

		final byte[] salt = hasher.generateSalt();
		final String hashedPassword = hasher.hashPassword(password, salt);

		final var newPlayer = new Player(nickname, hashedPassword, salt);
		playerRepository.save(newPlayer);
		return RegistrationResult.getSuccessResult(newPlayer);
	}

	@Override
	public AuthenticationResult login(@NotNull final String nickname, @NotNull final String password) {
		final Player existingPlayer;
		try {
			existingPlayer = playerRepository.getPlayerByNickname(nickname);
		} catch (final NoResultException e) {
			return AuthenticationResult.PLAYER_DOES_NOT_EXISTS;
		}

		if (!hasher.verifyPassword(password, existingPlayer.getHashedPassword(), existingPlayer.getPasswordSalt())) {
			return AuthenticationResult.INVALID_PASSWORD;
		}

		return AuthenticationResult.getSuccessResult(existingPlayer.getId());
	}

	@Override
	public ValidationResult isPasswordValid(@NotNull final String password) {
		final List<String> validationMessages = PASSWORD_RULES.entrySet().stream()
				.filter(entry -> !entry.getKey().test(password))
				.map(Map.Entry::getValue)
				.toList();

		return validationMessages.isEmpty()
				? new ValidationResult(true, null)
				: new ValidationResult(false, validationMessages);
	}

	@Override
	public ValidationResult isNicknameValid(@NotNull final String nickname) {
		final List<String> validationMessages = NICKNAME_RULES.entrySet().stream()
				.filter(entry -> !entry.getKey().test(nickname))
				.map(Map.Entry::getValue)
				.toList();

		return validationMessages.isEmpty()
				? new ValidationResult(true, null)
				: new ValidationResult(false, validationMessages);
	}
}
