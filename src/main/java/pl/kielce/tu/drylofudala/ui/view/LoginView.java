package pl.kielce.tu.drylofudala.ui.view;

import pl.kielce.tu.drylofudala.persistance.resource.ResourceRepository;
import pl.kielce.tu.drylofudala.ui.model.ImagePanel;
import pl.kielce.tu.drylofudala.ui.UiConfig;
import pl.kielce.tu.drylofudala.ui.view.factory.IView;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;

public class LoginView implements IView {
	@Override
	public JPanel createView() {
		return initializeView();
	}

	private JPanel initializeView() {
		final JPanel view = new JPanel();

		view.setLayout(new BorderLayout());

		ImagePanel backgroundPanel = createBackgroundPanel();
		JPanel contentPanel = createContentPanel();
		backgroundPanel.add(contentPanel, BorderLayout.CENTER);

		JLabel label = new JLabel("LOGIN VIEW");
		label.setFont(UiConfig.TITLE_FONT);

		label.setForeground(Color.RED);

		backgroundPanel.add(label);

		view.add(backgroundPanel);

		view.setVisible(true);
		return view;
	}

	private JPanel createContentPanel() {
		JPanel contentPanel = new JPanel(new BorderLayout());
		// TODO: Implement
		return contentPanel;
	}

	private ImagePanel createBackgroundPanel() {
		Image backgroundImage = ResourceRepository.getInstance().getImageForPath("graphics\\UI\\tlostart.png");
		return new ImagePanel(backgroundImage);
	}
}
