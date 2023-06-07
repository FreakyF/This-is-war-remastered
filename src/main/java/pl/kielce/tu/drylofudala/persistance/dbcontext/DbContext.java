package pl.kielce.tu.drylofudala.persistance.dbcontext;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.jetbrains.annotations.NotNull;
import pl.kielce.tu.drylofudala.entity.BaseEntity;

import java.util.List;
import java.util.function.Consumer;

public abstract class DbContext<T extends BaseEntity> implements IDbContext<T> {
	protected final EntityManagerFactory entityManagerFactory;
	protected final EntityManager entityManager;
	protected final Class<T> entityClass;

	private static final String PERSISTENCE_UNIT_NAME = "ThisIsWarPU";
	private static final int BATCH_SIZE = 25;

	protected DbContext(Class<T> entityClass) {
		entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		entityManager = entityManagerFactory.createEntityManager();
		this.entityClass = entityClass;
	}

	@Override
	public T find(long id) {
		return entityManager.find(entityClass, id);
	}

	@Override
	public @NotNull List<T> findAll() {
		var criteriaBuilder = entityManager.getCriteriaBuilder();
		var criteriaQuery = criteriaBuilder.createQuery(entityClass);
		var root = criteriaQuery.from(entityClass);
		criteriaQuery.select(root);
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public void save(@NotNull T entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
	}

	@Override
	public void saveRange(@NotNull List<T> entities) {
		entityManager.getTransaction().begin();
		persistEntitiesInBatches(entities);
		entityManager.getTransaction().commit();
	}

	private void persistEntitiesInBatches(List<T> entities) {
		processEntitiesInBatches(entities, entityManager::persist);
	}

	@Override
	public void update(@NotNull T entity) {
		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		entityManager.getTransaction().commit();
	}

	@Override
	public void delete(@NotNull T entity) {
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
	}

	@Override
	public void deleteRange(@NotNull List<T> entities) {
		entityManager.getTransaction().begin();
		removeEntitiesInBatches(entities);
		entityManager.getTransaction().commit();
	}

	private void removeEntitiesInBatches(List<T> entities) {
		processEntitiesInBatches(entities, entityManager::remove);
	}

	@Override
	public boolean isEmpty() {
		var criteriaBuilder = entityManager.getCriteriaBuilder();
		var criteriaQuery = criteriaBuilder.createQuery(entityClass);
		var root = criteriaQuery.from(entityClass);
		criteriaQuery.select(root);
		return entityManager.createQuery(criteriaQuery).getResultList().isEmpty();
	}

	@Override
	public void close() {
		entityManager.close();
		entityManagerFactory.close();
	}

	private void processEntitiesInBatches(List<T> entities, Consumer<T> action) {
		int i = 0;
		for (T entity : entities) {
			if (i > 0 && i % BATCH_SIZE == 0) {
				entityManager.flush();
				entityManager.clear();
			}
			action.accept(entity);
			i++;
		}
	}
}
