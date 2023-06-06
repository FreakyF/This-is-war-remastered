package pl.kielce.tu.drylofudala.persistance.repository.player;

import org.jetbrains.annotations.NotNull;
import pl.kielce.tu.drylofudala.entity.Player;
import pl.kielce.tu.drylofudala.persistance.dbcontext.DbContext;

public class PlayerRepository extends DbContext<Player> implements IPlayerRepository {
    public PlayerRepository() {
        super(Player.class);
    }

    @Override
    public boolean isNicknameTaken(@NotNull String nickname) {
        String jpql = "SELECT COUNT(p) FROM Player p WHERE p.name = :nickname";
        Long count = entityManager.createQuery(jpql, Long.class)
                .setParameter("nickname", nickname)
                .getSingleResult();
        return count > 0;
    }
}