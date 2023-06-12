package pl.kielce.tu.drylofudala.persistance.resource;

import java.awt.Image;
import java.net.URL;
import org.jetbrains.annotations.NotNull;

public interface IResourceRepository {
	@NotNull
	URL getResourceFromPath(@NotNull String pathToResource);

	@NotNull
	Image getImageFromPath(@NotNull String pathToResource);
}
