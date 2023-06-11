package pl.kielce.tu.drylofudala.authentication.service;

import org.jetbrains.annotations.NotNull;
import pl.kielce.tu.drylofudala.authentication.result.AuthenticationResult;
import pl.kielce.tu.drylofudala.authentication.result.RegistrationResult;
import pl.kielce.tu.drylofudala.authentication.result.ValidationResult;

public interface IAuthenticationService {
	RegistrationResult register(@NotNull String nickname, @NotNull String password);

	AuthenticationResult login(@NotNull String nickname, @NotNull String password);

	ValidationResult isPasswordValid(@NotNull String password);

	ValidationResult isNicknameValid(@NotNull String nickname);
}
