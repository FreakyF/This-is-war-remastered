package pl.kielce.tu.drylofudala.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.kielce.tu.drylofudala.entity.Card;

import java.util.ArrayList;
import java.util.List;

public class Row {
    private static final Logger logger = LogManager.getLogger(Row.class);
    private final PositionType type;
    private final List<Card> cards = new ArrayList<>();

    public Row(PositionType type) {
        this.type = type;
    }

    public PositionType getType() {
        return type;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) throws IllegalArgumentException {
        if (card.getType() != type) {
            logger.debug("Card {} with type {} type does not match row type {}", card.getName(), card.getType(), type);
            throw new IllegalArgumentException("Card type does not match row type");
        }

        cards.add(card);
    }

    public int calculatePoints() {
        return cards.stream().mapToInt(Card::getPoints).sum();
    }
}
