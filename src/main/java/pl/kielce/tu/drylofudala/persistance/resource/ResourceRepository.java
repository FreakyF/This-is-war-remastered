package pl.kielce.tu.drylofudala.persistance.resource;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.jetbrains.annotations.NotNull;

public final class ResourceRepository implements IResourceRepository {
	@NotNull
	public URL getResourceFromPath(@NotNull final String pathToResource) {
		final URL resource = ResourceRepository.class.getClassLoader().getResource(pathToResource);
		if (resource == null) {
			throw new IllegalArgumentException(String.format("File %s is null!", pathToResource));
		}
		return resource;
	}

	@NotNull
	public ImageIcon getImageIconForPath(@NotNull final String pathToResource) {
		return new ImageIcon(getResourceFromPath(pathToResource));
	}

	@NotNull
	public Image getImageForPath(@NotNull final String pathToResource) throws IOException {
		return ImageIO
				.read(getResourceFromPath(pathToResource))
				.getScaledInstance(100, -1, Image.SCALE_SMOOTH);
	}
}
