package pl.kielce.tu.drylofudala.ui;

import pl.kielce.tu.drylofudala.ui.model.DialogBox;
import pl.kielce.tu.drylofudala.ui.view.GuestView;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;

public class MainWindow extends JFrame {

	public MainWindow() {
		initializeWindow();
	}

	private void initializeWindow() {
		setTitle("This is war - Guest"); // First view is always guest.
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(UiConfig.WINDOW_MIN_WIDTH, UiConfig.WINDOW_MIN_HEIGHT));

		setLocationRelativeTo(null); // set to null because window has no parent. The window is itself a parent.
		setDefaultLookAndFeelDecorated(true);

		GuestView guestView = new GuestView();
		JPanel viewPanel = guestView.createView();
		add(viewPanel);

		setVisible(true);

		new DialogBox(this, "");
	}
}
