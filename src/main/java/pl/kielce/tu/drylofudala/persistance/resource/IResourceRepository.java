package pl.kielce.tu.drylofudala.persistance.resource;

import java.awt.Image;
import java.net.URL;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface IResourceRepository {
	@Nullable
	URL getResourceFromPath(@NotNull String pathToResource);

	@Nullable
	Image getImageFromPath(@NotNull String pathToResource);
}
