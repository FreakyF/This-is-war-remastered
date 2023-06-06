package pl.kielce.tu.drylofudala.persistance.resource;

import org.jetbrains.annotations.NotNull;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

public interface IResourceRepository {
	@NotNull
	URL getResourceFromPath(@NotNull String pathToResource);

	@NotNull
	ImageIcon getImageIconForPath(@NotNull String pathToResource);

	@NotNull
	Image getImageForPath(@NotNull String pathToResource) throws IOException;
}
