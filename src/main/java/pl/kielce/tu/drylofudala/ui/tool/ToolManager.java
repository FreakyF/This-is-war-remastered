package pl.kielce.tu.drylofudala.ui.tool;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public final class ToolManager {
	private ToolManager() {
	}

	public static void addBorderToJPanel(final JPanel panel) {
		panel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 2));
	}
}
