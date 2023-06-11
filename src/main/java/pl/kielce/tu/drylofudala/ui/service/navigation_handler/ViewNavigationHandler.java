package pl.kielce.tu.drylofudala.ui.service.navigation_handler;

import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import pl.kielce.tu.drylofudala.persistance.resource.IResourceRepository;
import pl.kielce.tu.drylofudala.ui.view.factory.IViewFactory;

public class ViewNavigationHandler implements IViewNavigationHandler {
	private final IResourceRepository resourceRepository;
	private final IViewFactory viewFactory;

	public ViewNavigationHandler(IViewFactory viewFactory, IResourceRepository resourceRepository) {
		this.resourceRepository = resourceRepository;
		this.viewFactory = viewFactory;
	}

	@Override
	public ActionListener navigateToLoginView() {
		return e -> {
			JPanel loginView = viewFactory.getLoginViewFactory().createView(this, resourceRepository);

			JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
			if (frame != null) {
				frame.setTitle("This is war - Login");
				frame.setContentPane(loginView);
				frame.revalidate();
				frame.repaint();
			}
		};
	}

	@Override
	public ActionListener navigateToRegisterView() {
		return e -> {
			JPanel registerView = viewFactory.getRegisterViewFactory().createView(this, resourceRepository);

			JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
			if (frame != null) {
				frame.setTitle("This is war - Register");
				frame.setContentPane(registerView);
				frame.revalidate();
				frame.repaint();
			}
		};
	}

	@Override
	public ActionListener navigateToUserView() {
		return e -> {
			JPanel registerView = viewFactory.getUserViewFactory().createView(this, resourceRepository);

			JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
			if (frame != null) {
				frame.setTitle("This is war - Menu");
				frame.setContentPane(registerView);
				frame.revalidate();
				frame.repaint();
			}
		};
	}

	@Override
	public ActionListener navigateToGameView() {
		return e -> {
			JPanel registerView = viewFactory.getGameViewFactory().createView(this, resourceRepository);

			JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
			if (frame != null) {
				frame.setTitle("This is war - Game");
				frame.setContentPane(registerView);
				frame.revalidate();
				frame.repaint();
			}
		};
	}
}
