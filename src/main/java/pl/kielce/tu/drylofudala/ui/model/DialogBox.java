package pl.kielce.tu.drylofudala.ui.model;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import pl.kielce.tu.drylofudala.persistance.resource.IResourceRepository;
import pl.kielce.tu.drylofudala.ui.UiConfig;
import pl.kielce.tu.drylofudala.ui.service.UiComponentCreator;
import pl.kielce.tu.drylofudala.ui.tool.ToolManager;

public class DialogBox extends JDialog {
	private final String text;
	private final transient UiComponentCreator uiComponentCreator;

	public DialogBox(JFrame parent, String text, IResourceRepository resourceRepository) {
		super(parent, "This is war - Dialog", true);
		this.uiComponentCreator = new UiComponentCreator(resourceRepository);

		this.text = text;

		int minWidth = (int) (UiConfig.WINDOW_MIN_WIDTH * 0.25);
		int minHeight = (int) (UiConfig.WINDOW_MIN_HEIGHT * 0.25);
		setMinimumSize(new Dimension(minWidth, minHeight));

		int maxWidth = (int) (UiConfig.WINDOW_MIN_WIDTH * 0.75);
		int maxHeight = (int) (UiConfig.WINDOW_MIN_HEIGHT * 0.75);
		setMaxSize(new Dimension(maxWidth, maxHeight));

		initalizeDialog();
	}

	private void setMaxSize(Dimension maxSize) {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent evt) {
				if (getWidth() > maxSize.width) {
					setSize(maxSize.width, getHeight());
				}
				if (getHeight() > maxSize.height) {
					setSize(getWidth(), maxSize.height);
				}
			}
		});
	}

	private void initalizeDialog() {
		var gbc = new GridBagConstraints();
		setLocationRelativeTo(super.getParent());

		gbc.fill = GridBagConstraints.BOTH;
		ImagePanel backgroundPanel = uiComponentCreator.createBackgroundPanel();
		backgroundPanel.setLayout(new GridBagLayout());
		add(backgroundPanel);

		JPanel contentPanel = createContentPanel();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		backgroundPanel.add(contentPanel, gbc);

		pack();
		setVisible(true);
	}

	private JPanel createContentPanel() {
		var gbc = new GridBagConstraints();
		JPanel contentPanel = new JPanel(new GridBagLayout());
		contentPanel.setOpaque(false);

		gbc.fill = GridBagConstraints.BOTH;

		var messagePanel = createMessagePanel();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 0.5;
		contentPanel.add(messagePanel, gbc);

		var buttonPanel = createButtonPanel();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 0.5;
		contentPanel.add(buttonPanel, gbc);

		return contentPanel;
	}

	private JPanel createMessagePanel() {
		var gbc = new GridBagConstraints();

		var messagePanel = new JPanel(new GridBagLayout());
		messagePanel.setOpaque(false);

		ToolManager.addBorderToJPanel(messagePanel); // TODO: Remove

		JLabel infoLabel = uiComponentCreator.createLabel(text, UiConfig.COPYRIGHT_FONT);
		infoLabel.setVerticalAlignment(SwingConstants.TOP);
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.NORTH;
		messagePanel.add(infoLabel, gbc);

		return messagePanel;
	}

	private JPanel createButtonPanel() {
		var gbc = new GridBagConstraints();

		var buttonPanel = new JPanel(new GridBagLayout());
		buttonPanel.setOpaque(false);

		ToolManager.addBorderToJPanel(buttonPanel); // TODO: Remove

		JButton okButton = uiComponentCreator.createButton("Ok", 60, 30);
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.anchor = GridBagConstraints.SOUTH;
		okButton.addActionListener(e -> dispose()); // Close the dialog when the OK button is clicked
		buttonPanel.add(okButton, gbc);
		return buttonPanel;
	}
}
