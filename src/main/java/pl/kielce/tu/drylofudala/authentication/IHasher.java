package pl.kielce.tu.drylofudala.authentication;

import org.jetbrains.annotations.NotNull;

public interface IHasher {
	byte[] generateSalt();

	String hashPassword(@NotNull String password, byte @NotNull [] salt);

	boolean verifyPassword(@NotNull String password, @NotNull String hashedPassword, byte @NotNull [] salt);
}
