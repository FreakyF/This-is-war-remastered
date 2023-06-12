package pl.kielce.tu.drylofudala.ui.service.navigation_handler;

import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import pl.kielce.tu.drylofudala.authentication.service.IAuthenticationService;
import pl.kielce.tu.drylofudala.persistance.resource.IResourceRepository;
import pl.kielce.tu.drylofudala.ui.MainWindow;
import pl.kielce.tu.drylofudala.ui.view.factory.IViewFactory;

public class ViewNavigationHandler implements IViewNavigationHandler {
	private final IResourceRepository resourceRepository;
	private final IViewFactory viewFactory;
	private final IAuthenticationService authenticationService;

	public ViewNavigationHandler(final IAuthenticationService authenticationService, final IViewFactory viewFactory, final IResourceRepository resourceRepository) {
		this.authenticationService = authenticationService;
		this.resourceRepository = resourceRepository;
		this.viewFactory = viewFactory;
	}

	@Override
	public ActionListener navigateToLoginView(final MainWindow parentWindow) {
		return e -> {
			final JPanel loginView = viewFactory.getLoginViewFactory().createView(parentWindow, authenticationService, this, resourceRepository);

			final JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
			if (frame != null) {
				frame.setTitle("This is war - Login");
				frame.setContentPane(loginView);
				frame.revalidate();
				frame.repaint();
			}
		};
	}

	@Override
	public ActionListener navigateToRegisterView(final MainWindow parentWindow) {
		return e -> {
			final JPanel registerView = viewFactory.getRegisterViewFactory().createView(parentWindow, authenticationService, this, resourceRepository);

			final JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
			if (frame != null) {
				frame.setTitle("This is war - Register");
				frame.setContentPane(registerView);
				frame.revalidate();
				frame.repaint();
			}
		};
	}

	@Override
	public ActionListener navigateToUserView(final MainWindow parentWindow) {
		return e -> {
			final JPanel registerView = viewFactory.getUserViewFactory().createView(parentWindow, this, resourceRepository);

			final JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
			if (frame != null) {
				frame.setTitle("This is war - Menu");
				frame.setContentPane(registerView);
				frame.revalidate();
				frame.repaint();
			}
		};
	}

	@Override
	public ActionListener navigateToGameView(final MainWindow parentWindow) {
		return e -> {
			final JPanel registerView = viewFactory.getGameViewFactory().createView(parentWindow, this, resourceRepository);

			final JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
			if (frame != null) {
				frame.setTitle("This is war - Game");
				frame.setContentPane(registerView);
				frame.revalidate();
				frame.repaint();
			}
		};
	}

	@Override
	public ActionListener exitToUserView(final MainWindow parentWindow) {
		return e -> {
			final JPanel registerView = viewFactory.getUserViewFactory().createView(parentWindow, this, resourceRepository);

			final JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
			if (frame != null) {
				frame.setTitle("This is war - Menu");
				frame.setContentPane(registerView);
				frame.revalidate();
				frame.repaint();
			}
			// TODO: reset all necessary variables associated with leaving the game.
		};
	}
}
