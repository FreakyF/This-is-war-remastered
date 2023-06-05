package pl.kielce.tu.drylofudala.authentication;

import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;
import org.jetbrains.annotations.NotNull;

import java.security.SecureRandom;
import java.util.Base64;

public class Hasher implements IHasher {

	@Override
	public byte[] generateSalt() {
		byte[] salt = new byte[AuthenticationConfig.SALT_LENGTH];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(salt);

		return salt;
	}

	@Override
	public String hashPassword(@NotNull String password, byte @NotNull [] salt) {
		Argon2Parameters parameters = new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
				.withSalt(salt)
				.withIterations(AuthenticationConfig.ITERATIONS)
				.withMemoryAsKB(AuthenticationConfig.MEMORY)
				.build();

		Argon2BytesGenerator generator = new Argon2BytesGenerator();
		generator.init(parameters);

		byte[] hashedPassword = new byte[AuthenticationConfig.HASHED_PASSWORD_LENGTH];
		generator.generateBytes(password.getBytes(), hashedPassword);

		return Base64.getEncoder().encodeToString(hashedPassword);
	}

	@Override
	public boolean verifyPassword(@NotNull String password, @NotNull String hashedPassword, byte @NotNull [] salt) {
		String userTypedHashedPassword = hashPassword(password, salt);
		return userTypedHashedPassword.equals(hashedPassword);
	}
}
