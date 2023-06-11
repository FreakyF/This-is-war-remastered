package pl.kielce.tu.drylofudala.persistance;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.kielce.tu.drylofudala.entity.BaseEntity;

import java.io.Closeable;
import java.util.List;

public interface IRepository<T extends BaseEntity> extends Closeable {
    @Nullable T find(long id);

    @NotNull List<T> findAll();

    void save(@NotNull T entity);

    void saveRange(@NotNull List<T> entities);

    void update(@NotNull T entity);

    void delete(@NotNull T entity);

    void deleteRange(@NotNull List<T> entities);

    boolean isEmpty();
}
