package pl.kielce.tu.drylofudala.authentication;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.kielce.tu.drylofudala.entity.Player;

public record RegistrationResult(@NotNull RegistrationResultType type,
                                 @Nullable String message,
                                 @Nullable Player newPlayer) {
}
