package pl.kielce.tu.drylofudala.authentication;

import org.jetbrains.annotations.NotNull;
import pl.kielce.tu.drylofudala.entity.Player;

public interface IAuthenticationService {
	Player register(@NotNull String nickname, @NotNull String password);

	AuthenticationResult login(@NotNull String nickname, @NotNull String password);

	ValidationResult isPasswordValid(@NotNull String password);

	ValidationResult isNicknameValid(@NotNull String nickname);
}
