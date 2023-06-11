package pl.kielce.tu.drylofudala.persistance;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.kielce.tu.drylofudala.config.CardConfiguration;
import pl.kielce.tu.drylofudala.entity.Card;
import pl.kielce.tu.drylofudala.model.CardResource;
import pl.kielce.tu.drylofudala.persistance.dbcontext.IDbContext;

public class DatabaseSeeder {
	private static final Logger logger = LogManager.getLogger(DatabaseSeeder.class);
	private final IDbContext<Card> cardRepository;

	public DatabaseSeeder(IDbContext<Card> cardRepository) {
		this.cardRepository = cardRepository;
	}

	public void seedCards() {
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
