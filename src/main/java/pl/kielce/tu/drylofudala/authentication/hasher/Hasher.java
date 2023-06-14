package pl.kielce.tu.drylofudala.authentication.hasher;

import java.security.SecureRandom;
import java.util.Base64;
import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;
import org.jetbrains.annotations.NotNull;
import pl.kielce.tu.drylofudala.authentication.AuthenticationConfig;

public class Hasher implements IHasher {

	@Override
	public byte[] generateSalt() {
		final byte[] salt = new byte[AuthenticationConfig.SALT_LENGTH];
		final SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(salt);

		return salt;
	}

	@Override
	public String hashPassword(@NotNull final String password, final byte @NotNull [] salt) {
		final Argon2Parameters parameters = new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
				.withSalt(salt)
				.withIterations(AuthenticationConfig.ITERATIONS)
				.withMemoryAsKB(AuthenticationConfig.MEMORY)
				.build();

		final Argon2BytesGenerator generator = new Argon2BytesGenerator();
		generator.init(parameters);

		final byte[] hashedPassword = new byte[AuthenticationConfig.HASHED_PASSWORD_LENGTH];
		generator.generateBytes(password.getBytes(), hashedPassword);

		return Base64.getEncoder().encodeToString(hashedPassword);
	}

	@Override
	public boolean verifyPassword(@NotNull final String password, @NotNull final String hashedPassword, final byte @NotNull [] salt) {
		final String userTypedHashedPassword = hashPassword(password, salt);
		return userTypedHashedPassword.equals(hashedPassword);
	}
}
