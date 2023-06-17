package pl.kielce.tu.drylofudala.ui.service.ui_component_creator;

import java.awt.Font;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import pl.kielce.tu.drylofudala.ui.model.CardLabel;
import pl.kielce.tu.drylofudala.ui.model.ImagePanel;

public interface IUiComponentCreator {
	List<CardLabel> createCardLabels();

	JButton createButton(final String text);

	JButton createReturnButton();

	ImagePanel createBackgroundPanel();

	ImagePanel createBoardBackgroundPanel();

	JButton createButton(final String text, final int width, final int height);

	JLabel createLabel(final String text, final Font fontStyle);
}
