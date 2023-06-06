package pl.kielce.tu.drylofudala.authentication;

import org.jetbrains.annotations.NotNull;

public interface IAuthenticationService {
	RegistrationResult register(@NotNull String nickname, @NotNull String password);

	AuthenticationResult login(@NotNull String nickname, @NotNull String password);

	ValidationResult isPasswordValid(@NotNull String password);

	ValidationResult isNicknameValid(@NotNull String nickname);
}
