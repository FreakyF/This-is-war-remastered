package pl.kielce.tu.drylofudala.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame {

	public MainWindow() {
		initializeWindow();
	}

	private void initializeWindow() {
		setTitle("This is war - Guest"); // First view is always guest.
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setLocationRelativeTo(null); // set to null because window has no parent. The window is itself a parent.
		setDefaultLookAndFeelDecorated(true);

		GuestView guestView = new GuestView();
		JPanel viewPanel = guestView.createView();
		add(viewPanel);

		setVisible(true);
	}
}
