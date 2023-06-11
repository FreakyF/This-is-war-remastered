package pl.kielce.tu.drylofudala.persistance.resource;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public final class ResourceRepository implements IResourceRepository {
	public URL getResourceFromPath(final String pathToResource) {
		return ResourceRepository.class.getClassLoader().getResource(pathToResource);
	}

	public Image getImageFromPath(final String pathToResource) {
		try {
			URL resource = getResourceFromPath(pathToResource);
			if (resource == null) {
				throw new IllegalArgumentException(String.format("File %s is null!", pathToResource));
			}

			return ImageIO
					.read(resource)
					.getScaledInstance(-1, -1, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
