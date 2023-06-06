package pl.kielce.tu.drylofudala.ui;


import pl.kielce.tu.drylofudala.persistance.resource.IResourceRepository;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;

public class StartMenu extends JFrame {
	private final transient IResourceRepository iResourceRepository;

	transient Image backgroundImage;

	public StartMenu(IResourceRepository iResourceRepository) {
		this.iResourceRepository = iResourceRepository;
		initializeWindow();
	}

	private void initializeWindow() {
		setTitle("This is war - Guest");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setLocationRelativeTo(null); // set to null because window has no parent. The window is itself a parent.

		ImagePanel mainPanel = createMainPanel();
		JLabel copyrightLabel = createBottomLabel("Made by Kamil Fudala & Karol Dryło", UiConfig.COPYRIGHT_FONT);
		mainPanel.add(copyrightLabel, BorderLayout.SOUTH);

		JPanel titlePanel = createTitlePanel();
		mainPanel.add(titlePanel, BorderLayout.CENTER);

		setVisible(true);
	}

	private ImagePanel createMainPanel() {
		try {
			backgroundImage = iResourceRepository.getImageForPath("graphics\\UI\\tlostart.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImagePanel mainPanel = new ImagePanel(backgroundImage);
		setContentPane(mainPanel);
		mainPanel.setLayout(new BorderLayout());

		return mainPanel;
	}

	private JPanel createTitlePanel() {
		JPanel titlePanel = new JPanel(new BorderLayout());
		int topPadding = 20;

		titlePanel.setBorder(BorderFactory.createEmptyBorder(topPadding, 0, 0, 0));
		titlePanel.setOpaque(false);
		JLabel label = createLabel("This is war", UiConfig.TITLE_FONT);
		titlePanel.add(label, BorderLayout.NORTH);

		JPanel buttonPanel = createButtonPanel();
		titlePanel.add(buttonPanel, BorderLayout.CENTER);

		return titlePanel;
	}

	private JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel(new GridBagLayout());
		buttonPanel.setOpaque(false);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);


		JButton loginButton = createButton("Login", UiConfig.BUTTON_FONT);
		gbc.gridx = 0;
		gbc.gridy = 0;
		buttonPanel.add(loginButton, gbc);

		JButton registerButton = createButton("Register", UiConfig.BUTTON_FONT);
		gbc.gridx = 0;
		gbc.gridy = 1;
		buttonPanel.add(registerButton, gbc);

		return buttonPanel;
	}

	private JLabel createLabel(String labelName, Font font) {
		JLabel label = new JLabel(labelName);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(font);

		return label;
	}

	private JLabel createBottomLabel(String labelName, Font font) {
		JLabel label = new JLabel(labelName);
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 20));
		label.setFont(font);

		return label;
	}

	private JButton createButton(String buttonName, Font font) {
		JButton button = new JButton(buttonName);
		button.setPreferredSize(new Dimension(300, 100));
		button.setFont(font);

		try {
			backgroundImage = iResourceRepository.getImageForPath("graphics\\UI\\HandPanel.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		button.setIcon(new ImageIcon(backgroundImage));

		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.setVerticalTextPosition(SwingConstants.CENTER);

		return button;
	}
}
