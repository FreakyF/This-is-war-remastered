package pl.kielce.tu.drylofudala.authentication.result;

import org.jetbrains.annotations.Nullable;
import pl.kielce.tu.drylofudala.entity.Player;

public record RegistrationResult(boolean success,
                                 @Nullable String message,
                                 @Nullable Player newPlayer) {
	private static final String NICKNAME_ALREADY_TAKEN_MESSAGE = "Given nickname is already taken.";
	public static final RegistrationResult NICKNAME_ALREADY_TAKEN = new RegistrationResult(
			false,
			NICKNAME_ALREADY_TAKEN_MESSAGE,
			null
	);

	public static RegistrationResult getSuccessResult(final Player newPlayer) {
		return new RegistrationResult(
				true,
				null,
				newPlayer
		);
	}
}
