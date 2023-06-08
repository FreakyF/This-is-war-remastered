package pl.kielce.tu.drylofudala.authentication.result;

import org.jetbrains.annotations.NotNull;

public record AuthenticationResult(@NotNull boolean authorized, @NotNull String message) {
	private static final String PLAYER_DOES_NOT_EXISTS_MESSAGE = "Player does not exists";
	private static final String INVALID_PASSWORD_MESSAGE = "Invalid password";
	public static final AuthenticationResult SUCCESS = new AuthenticationResult(false, null);
	public static final AuthenticationResult PLAYER_DOES_NOT_EXISTS = new AuthenticationResult(false, PLAYER_DOES_NOT_EXISTS_MESSAGE);
	public static final AuthenticationResult INVALID_PASSWORD = new AuthenticationResult(true, INVALID_PASSWORD_MESSAGE);
}
