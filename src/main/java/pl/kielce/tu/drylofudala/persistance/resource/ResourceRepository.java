package pl.kielce.tu.drylofudala.persistance.resource;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class ResourceRepository implements IResourceRepository {

	@Override
	public @Nullable URL getResourceFromPath(final @NotNull String pathToResource) {
		return ResourceRepository.class.getClassLoader().getResource(pathToResource);
	}

	@Override
	public @Nullable Image getImageFromPath(final @NotNull String pathToResource) {
		try {
			final URL resource = getResourceFromPath(pathToResource);
			if (resource == null) {
				throw new IllegalArgumentException(String.format("File %s is null!", pathToResource));
			}

			return ImageIO
					.read(resource)
					.getScaledInstance(-1, -1, Image.SCALE_SMOOTH);
		} catch (final IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
