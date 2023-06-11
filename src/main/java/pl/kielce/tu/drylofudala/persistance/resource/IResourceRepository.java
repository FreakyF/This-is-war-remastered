package pl.kielce.tu.drylofudala.persistance.resource;

import java.awt.Image;
import java.net.URL;

public interface IResourceRepository {
	URL getResourceFromPath(final String pathToResource);

	Image getImageFromPath(final String pathToResource);
}
