package pl.kielce.tu.drylofudala.persistance.repository.player;

import org.jetbrains.annotations.NotNull;
import pl.kielce.tu.drylofudala.entity.Player;
import pl.kielce.tu.drylofudala.persistance.dbcontext.IDbContext;

public interface IPlayerRepository extends IDbContext<Player> {
    boolean isNicknameTaken(@NotNull String nickname);
}
