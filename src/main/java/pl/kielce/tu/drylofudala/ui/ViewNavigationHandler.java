package pl.kielce.tu.drylofudala.ui;

import pl.kielce.tu.drylofudala.ui.factory.view.ViewFactory;

import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewNavigationHandler {
	private final ViewFactory viewFactory;

	public ViewNavigationHandler(){
		viewFactory = ViewFactory.getInstance();
	}

	public ActionListener navigateToLoginView(final JFrame window) {
		return (ActionEvent e) -> {
			var loginView = viewFactory.getLoginViewFactory().createView();

			window.getContentPane().removeAll();
			window.getContentPane().add(loginView);
			window.revalidate();
			window.repaint();

			window.setTitle("This is war - Login");
		};
	}
}
