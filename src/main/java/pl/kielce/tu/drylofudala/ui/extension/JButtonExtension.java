package pl.kielce.tu.drylofudala.ui.extension;

import java.awt.event.ActionListener;
import javax.swing.JButton;

public final class JButtonExtension {
	public static void removeAllListeners(final JButton jButton) {
		final var actionListeners = jButton.getActionListeners();
		for (final ActionListener actionListener : actionListeners) {
			jButton.removeActionListener(actionListener);
		}
	}
}
