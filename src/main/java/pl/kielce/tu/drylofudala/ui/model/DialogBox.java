package pl.kielce.tu.drylofudala.ui.model;

import pl.kielce.tu.drylofudala.ui.UiConfig;
import pl.kielce.tu.drylofudala.ui.UiResource;
import pl.kielce.tu.drylofudala.ui.service.UiComponentCreator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class DialogBox extends JDialog {
	public DialogBox(JFrame parent, String text) {
		super(parent, "This is war - Dialog", true);

		initalizeDialog(text);
	}

	private void initalizeDialog(String text) {
		setSize(400, 250);
		setLocationRelativeTo(null);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;

		ImagePanel backgroundPanel = UiComponentCreator.createBackgroundPanel();
		backgroundPanel.setLayout(new GridBagLayout());
		add(backgroundPanel);

		JPanel contentPanel = createContentPanel(text);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		backgroundPanel.add(contentPanel, gbc);

		setVisible(true);
	}

	private JPanel createContentPanel(String text) {
		JPanel contentPanel = new JPanel(new GridBagLayout());
		contentPanel.setOpaque(false);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);

		// infoLabel
		JLabel infoLabel = UiComponentCreator.createLabel(text, UiConfig.COPYRIGHT_FONT);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weighty = 0;
		contentPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
		contentPanel.add(infoLabel, gbc);

		JButton okButton = UiComponentCreator.createButton(UiResource.BUTTON_OK_TEXT, 75, 50);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.PAGE_END; // Added anchor to align the button at the bottom

		okButton.addActionListener(e -> dispose()); // Close the dialog when the OK button is clicked
		contentPanel.add(okButton, gbc);

		return contentPanel;
	}
}
