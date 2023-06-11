package pl.kielce.tu.drylofudala.persistance.resource;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

public final class ResourceRepository {
	private static ResourceRepository instance;

	public static ResourceRepository getInstance() {
		if (instance == null) {
			instance = new ResourceRepository();
		}
		return instance;
	}

	private ResourceRepository(){
	}

	public URL getResourceFromPath(final String pathToResource) {
		return ResourceRepository.class.getClassLoader().getResource(pathToResource);
	}

	public ImageIcon getImageIconForPath(final String pathToResource) {
		URL resource = getResourceFromPath(pathToResource);
		if(resource == null){
			return null;
		}
		return new ImageIcon(resource);
	}

	public Image getImageFromPath(final String pathToResource) {
		try {
			URL resource = getResourceFromPath(pathToResource);
			if(resource == null) {
				return null;
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
