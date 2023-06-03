package pl.kielce.tu.drylofudala.authentication;

import org.jetbrains.annotations.NotNull;
import pl.kielce.tu.drylofudala.entity.Player;

public class AuthenticationService implements IAuthenticationService {
    @Override
    public Player register(@NotNull String nickname, @NotNull String password) {
        return null;
    }

    @Override
    public AuthenticationResult login(@NotNull String nickname, @NotNull String password) {
        return null;
    }
}
