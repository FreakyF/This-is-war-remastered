package pl.kielce.tu.drylofudala.model;

import java.util.ArrayList;
import java.util.List;
import pl.kielce.tu.drylofudala.entity.Card;

public class Row {
	private final PositionType type;
	private final List<Card> cards = new ArrayList<>();

	public Row(final PositionType type) {
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

	public void addCards(final List<Card> cards) {
		this.cards.addAll(cards);
	}
}
