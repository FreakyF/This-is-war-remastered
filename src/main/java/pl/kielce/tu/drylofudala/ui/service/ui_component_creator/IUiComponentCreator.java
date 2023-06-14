package pl.kielce.tu.drylofudala.ui.service.ui_component_creator;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import pl.kielce.tu.drylofudala.ui.model.ImagePanel;

public interface IUiComponentCreator {
	JButton createButton(final String text);

	JButton createReturnButton();

	ImagePanel createBackgroundPanel(JFrame parentWindow);

	ImagePanel createBoardBackgroundPanel(JFrame parentWindow);

	JButton createButton(final String text, final int width, final int height);

	JLabel createLabel(final String text, final Font fontStyle);
}
