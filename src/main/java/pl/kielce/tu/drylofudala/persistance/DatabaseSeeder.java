package pl.kielce.tu.drylofudala.persistance;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.kielce.tu.drylofudala.config.CardConfiguration;
import pl.kielce.tu.drylofudala.entity.Card;
import pl.kielce.tu.drylofudala.model.CardResource;

import java.util.List;

public class DatabaseSeeder {
	private static final Logger logger = LogManager.getLogger(DatabaseSeeder.class);
	private final IRepository<Card> cardRepository;

	public DatabaseSeeder(IRepository<Card> cardRepository) {
		this.cardRepository = cardRepository;
	}

	public void seed() {
		seedCards();
	}

	private void seedCards() {
		if (!cardRepository.isEmpty()) {
			logger.debug("Cards already seeded");
			return;
		}

		final List<Card> cardsToSave = CardConfiguration.CARD_RESOURCES
				.stream()
				.map(CardResource::toCard)
				.toList();

		logger.debug("Seeding cards");
		cardRepository.saveRange(cardsToSave);
	}
}