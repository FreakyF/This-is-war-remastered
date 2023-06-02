package pl.kielce.tu.drylofudala.persistance;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import pl.kielce.tu.drylofudala.entity.BaseEntity;

import java.util.List;

public class DbContext<T extends BaseEntity> implements IRepository<T> {
    protected final EntityManagerFactory entityManagerFactory;
    protected final EntityManager entityManager;
    protected final Class<T> entityClass;

    public DbContext(Class<T> entityClass) {
        entityManagerFactory = Persistence.createEntityManagerFactory(DbConfig.PERSISTENCE_UNIT_NAME);
        entityManager = entityManagerFactory.createEntityManager();
        this.entityClass = entityClass;
    }

    @Override
    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public T find(long id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(entityClass);
        var root = criteriaQuery.from(entityClass);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public void save(T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void saveRange(List<T> entities) {
        entityManager.getTransaction().begin();
        persistEntitiesInBatches(entities);
        entityManager.getTransaction().commit();
    }

    private void persistEntitiesInBatches(List<T> entities) {
        int i = 0;
        for (T entity : entities) {
            if (i > 0 && i % DbConfig.BATCH_SIZE == 0) {
                entityManager.flush();
                entityManager.clear();
            }

            entityManager.persist(entity);
            i++;
        }
    }

    @Override
    public void update(T entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(T entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteRange(List<T> entities) {
        entityManager.getTransaction().begin();
        removeEntitiesInBatches(entities);
        entityManager.getTransaction().commit();
    }

    private void removeEntitiesInBatches(List<T> entities) {
        int i = 0;
        for (T entity : entities) {
            if (i > 0 && i % DbConfig.BATCH_SIZE == 0) {
                entityManager.flush();
                entityManager.clear();
            }
            entityManager.remove(entity);
            i++;
        }
    }
}
