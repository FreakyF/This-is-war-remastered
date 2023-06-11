package pl.kielce.tu.drylofudala.model;

import pl.kielce.tu.drylofudala.entity.Card;

import java.util.ArrayList;
import java.util.List;

public class Row {
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

    public void addCard(final Card card) {
        cards.add(card);
    }

    public void addCards(List<Card> cards) {
        this.cards.addAll(cards);
    }
}
