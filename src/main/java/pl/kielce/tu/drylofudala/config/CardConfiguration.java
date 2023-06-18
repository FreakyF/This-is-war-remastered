package pl.kielce.tu.drylofudala.config;

import java.nio.file.Path;
import java.util.List;
import pl.kielce.tu.drylofudala.model.CardResource;
import pl.kielce.tu.drylofudala.model.PositionType;
import pl.kielce.tu.drylofudala.ui.UiResource;

public final class CardConfiguration {
	public static final List<CardResource> CARD_RESOURCES = List.of(
			new CardResource(Path.of(UiResource.GRAPHICS_FOLDER, UiResource.CARDS_FOLDER, "card_Andrzej_15.png").toString(), 15, PositionType.MELEE),
			new CardResource(Path.of(UiResource.GRAPHICS_FOLDER, UiResource.CARDS_FOLDER, "card_Infantry_1.png").toString(), 1, PositionType.MELEE),
			new CardResource(Path.of(UiResource.GRAPHICS_FOLDER, UiResource.CARDS_FOLDER, "card_Infantry_2.png").toString(), 2, PositionType.MELEE),
			new CardResource(Path.of(UiResource.GRAPHICS_FOLDER, UiResource.CARDS_FOLDER, "card_Infantry_3.png").toString(), 3, PositionType.MELEE),

			new CardResource(Path.of(UiResource.GRAPHICS_FOLDER, UiResource.CARDS_FOLDER, "card_JacekChrzan_15.png").toString(), 15, PositionType.MELEE),
			new CardResource(Path.of(UiResource.GRAPHICS_FOLDER, UiResource.CARDS_FOLDER, "card_JanKnot_15.png").toString(), 15, PositionType.MELEE),
			new CardResource(Path.of(UiResource.GRAPHICS_FOLDER, UiResource.CARDS_FOLDER, "card_JanRambo_15.png").toString(), 15, PositionType.MELEE),

			new CardResource(Path.of(UiResource.GRAPHICS_FOLDER, UiResource.CARDS_FOLDER, "card_Rocket_6.png").toString(), 6, PositionType.RANGED),
			new CardResource(Path.of(UiResource.GRAPHICS_FOLDER, UiResource.CARDS_FOLDER, "card_Rocket_7.png").toString(), 7, PositionType.RANGED),
			new CardResource(Path.of(UiResource.GRAPHICS_FOLDER, UiResource.CARDS_FOLDER, "card_Rocket_8.png").toString(), 8, PositionType.RANGED),

			new CardResource(Path.of(UiResource.GRAPHICS_FOLDER, UiResource.CARDS_FOLDER, "card_Tank_4.png").toString(), 4, PositionType.RANGED),
			new CardResource(Path.of(UiResource.GRAPHICS_FOLDER, UiResource.CARDS_FOLDER, "card_Tank_6.png").toString(), 6, PositionType.RANGED));

	private CardConfiguration() {
	}

}
