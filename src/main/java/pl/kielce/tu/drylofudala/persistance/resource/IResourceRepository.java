package pl.kielce.tu.drylofudala.persistance.resource;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.swing.ImageIcon;
import org.jetbrains.annotations.NotNull;

public interface IResourceRepository {
	@NotNull
	URL getResourceFromPath(@NotNull String pathToResource);

	@NotNull
	ImageIcon getImageIconForPath(@NotNull String pathToResource);

	@NotNull
	Image getImageForPath(@NotNull String pathToResource) throws IOException;
}
