package pl.kielce.tu.drylofudala.persistance.repository.card;

import pl.kielce.tu.drylofudala.entity.Card;
import pl.kielce.tu.drylofudala.persistance.dbcontext.DbContext;

public class CardRepository extends DbContext<Card> implements ICardRepository {
	public CardRepository() {
		super(Card.class);
	}
}
