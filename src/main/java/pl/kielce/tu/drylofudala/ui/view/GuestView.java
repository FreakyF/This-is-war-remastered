package pl.kielce.tu.drylofudala.ui.view;

import org.jetbrains.annotations.NotNull;
import pl.kielce.tu.drylofudala.persistance.resource.ResourceRepository;
import pl.kielce.tu.drylofudala.ui.model.ImagePanel;
import pl.kielce.tu.drylofudala.ui.UiConfig;
import pl.kielce.tu.drylofudala.ui.service.ViewNavigationHandler;
import pl.kielce.tu.drylofudala.ui.view.factory.IView;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.net.URL;

public class GuestView implements IView {
	private final ViewNavigationHandler navigationHandler = ViewNavigationHandler.getInstance();

	@Override
	public JPanel createView() {
		return initializeView();
	}

	JPanel initializeView() {

		final JPanel view = new JPanel();
		view.setLayout(new BorderLayout());

		ImagePanel backgroundPanel = createBackgroundPanel();
		JPanel contentPanel = createContentPanel();
		backgroundPanel.add(contentPanel, BorderLayout.CENTER);
		view.add(backgroundPanel);

		view.setVisible(true);
		return view;
	}

	private ImagePanel createBackgroundPanel() {
		Image backgroundImage = ResourceRepository.getInstance().getImageFromPath("graphics\\UI\\background.png");
		return new ImagePanel(backgroundImage);
	}

	private JPanel createContentPanel() {
		JPanel contentPanel = new JPanel(new BorderLayout());

		contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		contentPanel.setOpaque(false);

		JLabel label = createLabel();
		contentPanel.add(label, BorderLayout.NORTH);

		JPanel buttonPanel = createButtonPanel();
		contentPanel.add(buttonPanel, BorderLayout.CENTER);

		return contentPanel;
	}

	private JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel(new GridBagLayout());
		buttonPanel.setOpaque(false);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);

		JButton loginButton = createLoginButton(buttonPanel, gbc);
		loginButton.addActionListener(navigationHandler.navigateToLoginView());

		JButton registerButton = createRegisterButton(buttonPanel, gbc);
		registerButton.addActionListener(navigationHandler.navigateToRegisterView());

		return buttonPanel;
	}

	@NotNull
	private JButton createRegisterButton(JPanel buttonPanel, GridBagConstraints gbc) {
		JButton registerButton = createButton("Register");
		gbc.gridx = 0;
		gbc.gridy = 1;
		buttonPanel.add(registerButton, gbc);
		return registerButton;
	}

	@NotNull
	private JButton createLoginButton(JPanel buttonPanel, GridBagConstraints gbc) {
		JButton loginButton = createButton("Login");
		gbc.gridx = 0;
		gbc.gridy = 0;
		buttonPanel.add(loginButton, gbc);
		return loginButton;
	}

	private JButton createButton(String buttonName) {
		JButton button = new JButton(buttonName);
		button.setPreferredSize(new Dimension(300, 100));
		button.setFont(UiConfig.BUTTON_FONT);

		URL buttonBackground = GuestView.class.getClassLoader().getResource("graphics\\UI\\HandPanel.png");
		button.setIcon(new ImageIcon(buttonBackground));

		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.setVerticalTextPosition(SwingConstants.CENTER);

		return button;
	}

	private JLabel createLabel() {
		JLabel label = new JLabel("This is war");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(UiConfig.TITLE_FONT);

		return label;
	}
}
