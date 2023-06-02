package pl.kielce.tu.drylofudala.persistance;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DbContext implements AutoCloseable {
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public DbContext(String persistenceUnitName) {
        entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
        entityManager = entityManagerFactory.createEntityManager();

        // Perform your database-related operations using the EntityManager
    }

    @Override
    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
