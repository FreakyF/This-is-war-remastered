package pl.kielce.tu.drylofudala.config;

import pl.kielce.tu.drylofudala.model.CardResource;
import pl.kielce.tu.drylofudala.model.PositionType;

import java.util.List;

public final class CardConfiguration {
    private CardConfiguration() {
    }

    public static final List<CardResource> CARD_RESOURCES = List.of(
            new CardResource("grafika\\card_Andrzej.png", 15, PositionType.MELEE),
            new CardResource("grafika\\card_Infantry_1.png", 1, PositionType.MELEE),
            new CardResource("grafika\\card_Infantry_2.png", 2, PositionType.MELEE),
            new CardResource("grafika\\card_Infantry_3.png", 3, PositionType.MELEE),
            new CardResource("grafika\\card_Jacek_Chrzan.png", 15, PositionType.MELEE),
            new CardResource("grafika\\card_Jan_Knot.png", 15, PositionType.MELEE),
            new CardResource("grafika\\card_Jan_Rambo.png", 15, PositionType.MELEE),
            new CardResource("grafika\\card_Rocket_6.png", 6, PositionType.RANGED),
            new CardResource("grafika\\card_Rocket_7.png", 7, PositionType.RANGED),
            new CardResource("grafika\\card_Rocket_8.png", 8, PositionType.RANGED),
            new CardResource("grafika\\card_Tank_4.png", 4, PositionType.RANGED),
            new CardResource("grafika\\card_Tank_6.png", 6, PositionType.RANGED));
}
