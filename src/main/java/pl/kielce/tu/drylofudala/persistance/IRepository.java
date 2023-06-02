package pl.kielce.tu.drylofudala.persistance;

import java.util.List;

public interface IRepository<T> extends AutoCloseable {
    T find(long id);

    List<T> findAll();

    void save(T entity);

    void saveRange(List<T> entities);

    void update(T entity);

    void delete(T entity);
    void deleteRange(List<T> entities);
}
