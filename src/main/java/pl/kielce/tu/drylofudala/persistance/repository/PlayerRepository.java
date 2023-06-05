package pl.kielce.tu.drylofudala.persistance.repository;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import pl.kielce.tu.drylofudala.entity.BaseEntity;
import pl.kielce.tu.drylofudala.persistance.DbContext;

public class PlayerRepository<T extends BaseEntity> extends DbContext<T> implements IPlayerRepository {
	public PlayerRepository(Class<T> entityClass) {
		super(entityClass);
	}

	@Override
	public boolean isNicknameAlreadyTaken(String nickname) throws NoResultException {
		TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(p) FROM Player p WHERE p.name = :nickname", Long.class);
		query.setParameter("nickname", nickname);

		Long count = query.getSingleResult();
		return count > 0;
	}
}
