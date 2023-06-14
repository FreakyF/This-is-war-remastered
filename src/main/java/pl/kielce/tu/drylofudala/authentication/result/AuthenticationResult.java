package pl.kielce.tu.drylofudala.authentication.result;

import org.jetbrains.annotations.Nullable;

public record AuthenticationResult(boolean authorized, @Nullable String message, @Nullable Long playerId) {
	private static final String PLAYER_DOES_NOT_EXISTS_MESSAGE = "Player does not exists";
	public static final AuthenticationResult PLAYER_DOES_NOT_EXISTS = new AuthenticationResult(false, PLAYER_DOES_NOT_EXISTS_MESSAGE, null);
	private static final String INVALID_PASSWORD_MESSAGE = "Invalid password";
	public static final AuthenticationResult INVALID_PASSWORD = new AuthenticationResult(false, INVALID_PASSWORD_MESSAGE, null);

	public static AuthenticationResult getSuccessResult(final Long playerId) {
		return new AuthenticationResult(true, null, playerId);
	}
}
