package pl.kielce.tu.drylofudala.entity;

import pl.kielce.tu.drylofudala.model.PositionType;
import pl.kielce.tu.drylofudala.model.Row;

public class Round extends BaseEntity {
    private final Row firstPlayerMeleeCards = new Row(PositionType.MELEE);
    private final Row firstPlayerRangedCards = new Row(PositionType.RANGED);
    private final Row secondPlayerMeleeCards = new Row(PositionType.MELEE);
    private final Row secondPlayerRangedCards = new Row(PositionType.RANGED);

    protected Round(int id) {
        super(id);
    }

    public Row getFirstPlayerMeleeCards() {
        return firstPlayerMeleeCards;
    }

    public Row getFirstPlayerRangedCards() {
        return firstPlayerRangedCards;
    }

    public Row getSecondPlayerMeleeCards() {
        return secondPlayerMeleeCards;
    }

    public Row getSecondPlayerRangedCards() {
        return secondPlayerRangedCards;
    }

    public int calculateFirstPlayerPoints() {
        return firstPlayerMeleeCards.calculatePoints() + firstPlayerRangedCards.calculatePoints();
    }

    public int calculateSecondPlayerPoints() {
        return secondPlayerMeleeCards.calculatePoints() + secondPlayerRangedCards.calculatePoints();
    }
}
