package pl.kielce.tu.drylofudala.persistance;

import pl.kielce.tu.drylofudala.entity.BaseEntity;

import java.util.List;

public interface IRepository<T extends BaseEntity> extends java.io.Closeable {
    T find(long id);

    List<T> findAll();

    void save(T entity);

    void saveRange(List<T> entities);

    void update(T entity);

    void delete(T entity);

    void deleteRange(List<T> entities);

    boolean isEmpty();
}
