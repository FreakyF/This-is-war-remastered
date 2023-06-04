package pl.kielce.tu.drylofudala.persistance;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.kielce.tu.drylofudala.config.CardConfiguration;
import pl.kielce.tu.drylofudala.entity.Card;

public class DatabaseSeeder {
    private final IRepository<Card> cardRepository;
    private static final Logger logger = LogManager.getLogger(DatabaseSeeder.class);

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

        logger.debug("Seeding cards");
        CardConfiguration.CARD_RESOURCES.forEach(cardResource -> {
            var cardName = getCardName(cardResource.name());
            Card card = new Card(cardName, cardResource.positionType(), cardResource.points(), cardResource.name());
            cardRepository.save(card);
        });
    }

    private String getCardName(String cardResourceName) {
        var cardNameWithFileExtension = cardResourceName.split("grafika\\\\card_")[1];
        return cardNameWithFileExtension.substring(0, cardNameWithFileExtension.lastIndexOf('.'));
    }
}
