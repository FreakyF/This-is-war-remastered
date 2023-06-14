package pl.kielce.tu.drylofudala.model;

import java.util.Objects;
import pl.kielce.tu.drylofudala.entity.Card;

public record CardResource(String imageFilePath, int points, PositionType positionType) {
	public Card toCard() {
		return new Card(getCardName(imageFilePath), positionType, points, imageFilePath);
	}

	private String getCardName(final String cardResourceName) {
		final var cardNameWithFileExtension = cardResourceName.replace("grafika\\\\card_", "");
		return cardNameWithFileExtension.substring(0, cardNameWithFileExtension.lastIndexOf('.'));
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final CardResource that)) {
			return false;
		}
		return points == that.points && Objects.equals(imageFilePath, that.imageFilePath) && positionType == that.positionType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(imageFilePath, points, positionType);
	}

	@Override
	public String toString() {
		return "CardResource{" +
				"imageFilePath='" + imageFilePath + '\'' +
				", points=" + points +
				", positionType=" + positionType +
				'}';
	}
}
