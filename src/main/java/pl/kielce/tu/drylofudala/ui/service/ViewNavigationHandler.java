package pl.kielce.tu.drylofudala.ui.service;

import pl.kielce.tu.drylofudala.ui.view.factory.ViewFactory;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Component;
import java.awt.event.ActionListener;

public class ViewNavigationHandler {
	private static ViewNavigationHandler instance;

	public static ViewNavigationHandler getInstance() {
		if (instance == null) {
			instance = new ViewNavigationHandler();
		}
		return instance;
	}

	private final ViewFactory viewFactory;

	private ViewNavigationHandler() {
		viewFactory = ViewFactory.getInstance();
	}

	public ActionListener navigateToLoginView() {
		return e -> {
			JPanel loginView = viewFactory.getLoginViewFactory().createView();

			JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
			if (frame != null) {
				frame.setTitle("This is war - Login");
				frame.setContentPane(loginView);
				frame.revalidate();
				frame.repaint();
			}
		};
	}

	public ActionListener navigateToRegisterView() {
		return e -> {
			JPanel registerView = viewFactory.getRegisterViewFactory().createView();

			JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
			if (frame != null) {
				frame.setTitle("This is war - Register");
				frame.setContentPane(registerView);
				frame.revalidate();
				frame.repaint();
			}
		};
	}

	public ActionListener navigateToUserView() {
		return e -> {
			JPanel registerView = viewFactory.getUserViewFactory().createView();

			JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
			if (frame != null) {
				frame.setTitle("This is war - Menu");
				frame.setContentPane(registerView);
				frame.revalidate();
				frame.repaint();
			}
		};
	}

	public ActionListener navigateToGameView() {
		return e -> {
			JPanel registerView = viewFactory.getGameViewFactory().createView();

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
