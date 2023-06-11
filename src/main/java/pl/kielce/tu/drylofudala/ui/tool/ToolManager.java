package pl.kielce.tu.drylofudala.ui.tool;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.Color;

public class ToolManager {
	private ToolManager(){
	}

	public static void addBorderToJPanel(JPanel panel){
		panel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2));
	}
}
