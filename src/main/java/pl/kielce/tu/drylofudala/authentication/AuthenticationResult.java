package pl.kielce.tu.drylofudala.authentication;

import org.jetbrains.annotations.NotNull;

public record AuthenticationResult(@NotNull AuthenticationResultType type, @NotNull String message) {

}
