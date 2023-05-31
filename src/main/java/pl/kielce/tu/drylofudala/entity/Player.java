package pl.kielce.tu.drylofudala.entity;

import java.util.ArrayList;
import java.util.List;

public class Player extends BaseEntity {
    private final List<Card> cardsInHand = new ArrayList<>();
    protected Player(int id) {
        super(id);
    }

    public List<Card> getCardsInHand() {
        return cardsInHand;
    }
}
