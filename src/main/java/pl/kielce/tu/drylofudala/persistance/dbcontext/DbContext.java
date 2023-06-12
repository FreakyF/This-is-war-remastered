package pl.kielce.tu.drylofudala.persistance.dbcontext;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;
import pl.kielce.tu.drylofudala.entity.BaseEntity;

public abstract class DbContext<T extends BaseEntity> implements IDbContext<T> {
	private static final String PERSISTENCE_UNIT_NAME = "ThisIsWarPU";
	private static final int BATCH_SIZE = 25;
	protected final EntityManagerFactory entityManagerFactory;
	protected final EntityManager entityManager;
	protected final Class<T> entityClass;

	protected DbContext(final Class<T> entityClass) {
		entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		entityManager = entityManagerFactory.createEntityManager();
		this.entityClass = entityClass;
	}

	@Override
	public T find(final long id) {
		return entityManager.find(entityClass, id);
	}

	@Override
	public @NotNull List<T> findAll() {
		final var criteriaBuilder = entityManager.getCriteriaBuilder();
		final var criteriaQuery = criteriaBuilder.createQuery(entityClass);
		final var root = criteriaQuery.from(entityClass);
		criteriaQuery.select(root);
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public void save(@NotNull final T entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
	}

	@Override
	public void saveRange(@NotNull final List<T> entities) {
		entityManager.getTransaction().begin();
		persistEntitiesInBatches(entities);
		entityManager.getTransaction().commit();
	}

	private void persistEntitiesInBatches(final List<T> entities) {
		processEntitiesInBatches(entities, entityManager::persist);
	}

	@Override
	public void update(@NotNull final T entity) {
		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		entityManager.getTransaction().commit();
	}

	@Override
	public void delete(@NotNull final T entity) {
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
	}

	@Override
	public void deleteRange(@NotNull final List<T> entities) {
		entityManager.getTransaction().begin();
		removeEntitiesInBatches(entities);
		entityManager.getTransaction().commit();
	}

	private void removeEntitiesInBatches(final List<T> entities) {
		processEntitiesInBatches(entities, entityManager::remove);
	}

	@Override
	public boolean isEmpty() {
		final var criteriaBuilder = entityManager.getCriteriaBuilder();
		final var criteriaQuery = criteriaBuilder.createQuery(entityClass);
		final var root = criteriaQuery.from(entityClass);
		criteriaQuery.select(root);
		return entityManager.createQuery(criteriaQuery).getResultList().isEmpty();
	}

	@Override
	public void close() {
		entityManager.close();
		entityManagerFactory.close();
	}

	private void processEntitiesInBatches(final List<T> entities, final Consumer<T> action) {
		int i = 0;
		for (final T entity : entities) {
			if (i > 0 && i % BATCH_SIZE == 0) {
				entityManager.flush();
				entityManager.clear();
			}
			action.accept(entity);
			i++;
		}
	}
}
