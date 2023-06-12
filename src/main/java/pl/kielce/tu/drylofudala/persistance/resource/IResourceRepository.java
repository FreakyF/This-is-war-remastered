package pl.kielce.tu.drylofudala.persistance.resource;

import java.awt.Image;
import java.net.URL;
import org.jetbrains.annotations.NotNull;

public interface IResourceRepository {
	URL getResourceFromPath(@NotNull String pathToResource);

	Image getImageFromPath(@NotNull String pathToResource);
}
