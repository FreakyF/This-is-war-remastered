package pl.kielce.tu.drylofudala.ui.view;

import pl.kielce.tu.drylofudala.ui.UiConfig;
import pl.kielce.tu.drylofudala.ui.model.ImagePanel;
import pl.kielce.tu.drylofudala.ui.service.UiComponentCreator;
import pl.kielce.tu.drylofudala.ui.service.ViewNavigationHandler;
import pl.kielce.tu.drylofudala.ui.view.factory.IView;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;


public class UserView implements IView {
	private final ViewNavigationHandler viewNavigationHandler = ViewNavigationHandler.getInstance();

	@Override
	public JPanel createView() {
		return initializeView();
	}

	private JPanel initializeView() {
		final BorderLayout layout = new BorderLayout();
		final JPanel view = new JPanel(layout);

		ImagePanel backgroundPanel = UiComponentCreator.createBackgroundPanel();
		view.add(backgroundPanel);

		JPanel contentPanel = createContentPanel();
		backgroundPanel.add(contentPanel);

		view.setVisible(true);
		return view;
	}

	private JPanel createContentPanel() {
		JPanel contentPanel = new JPanel(new GridBagLayout());
		contentPanel.setOpaque(false);


		GridBagConstraints gbc = UiComponentCreator.createGridBagConstrains(UiConfig.GBC_INSETS);

		return contentPanel;
	}
}
