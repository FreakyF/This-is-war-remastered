package pl.kielce.tu.drylofudala.model;

import pl.kielce.tu.drylofudala.entity.Card;

public record CardResource(String imageFilePath, int points, PositionType positionType) {
	public Card toCard() {
		return new Card(getCardName(imageFilePath), positionType, points, imageFilePath);
	}

	private String getCardName(String cardResourceName) {
		var cardNameWithFileExtension = cardResourceName.replace("grafika\\\\card_", "");
		return cardNameWithFileExtension.substring(0, cardNameWithFileExtension.lastIndexOf('.'));
	}
}
