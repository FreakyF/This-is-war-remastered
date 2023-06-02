package pl.kielce.tu.drylofudala;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.kielce.tu.drylofudala.config.CardConfiguration;
import pl.kielce.tu.drylofudala.entity.Card;
import pl.kielce.tu.drylofudala.persistance.DbContext;
import pl.kielce.tu.drylofudala.persistance.IRepository;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        try (IRepository<Card> cardRepository = new DbContext<>(Card.class)) {
            CardConfiguration.CARD_RESOURCES.forEach(cardResource -> {
                String cardName = cardResource.name().split("grafika\\\\card_")[1];
                cardName = cardName.substring(0, cardName.lastIndexOf('.'));
                Card card = new Card(cardName, cardResource.positionType(), cardResource.points(), cardResource.name());
                cardRepository.save(card);
            });
        } catch (Exception e) {
            logger.error("Error while saving card", e);
        }
    }
}