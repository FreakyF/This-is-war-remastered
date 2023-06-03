package pl.kielce.tu.drylofudala.authentication;

import org.jetbrains.annotations.NotNull;
import pl.kielce.tu.drylofudala.entity.Player;

public interface IAuthenticationService {
    Player register(@NotNull final String nickname, @NotNull final String password);

    AuthenticationResult login(@NotNull final String nickname, @NotNull final String password);

    ValidationResult isPasswordValid(@NotNull final String password);

    ValidationResult isNicknameValid(@NotNull final String nickname);
}