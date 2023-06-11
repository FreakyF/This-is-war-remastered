package pl.kielce.tu.drylofudala.persistance.dbcontext;

import java.io.Closeable;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.kielce.tu.drylofudala.entity.BaseEntity;

public interface IDbContext<T extends BaseEntity> extends Closeable {
	@Nullable T find(long id);

	@NotNull List<T> findAll();

	void save(@NotNull T entity);

	void saveRange(@NotNull List<T> entities);

	void update(@NotNull T entity);

	void delete(@NotNull T entity);

	void deleteRange(@NotNull List<T> entities);

	boolean isEmpty();
}
