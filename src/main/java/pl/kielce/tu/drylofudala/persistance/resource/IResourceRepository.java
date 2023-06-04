package pl.kielce.tu.drylofudala.persistance.resource;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public interface IResourceRepository {
	@NotNull
	URL getResourceFromPath(@NotNull final String pathToResource);

	@NotNull
	ImageIcon getImageIconForPath(@NotNull String pathToResource);

	@NotNull
	Image getImageForPath(@NotNull final String pathToResource) throws IOException;
}
