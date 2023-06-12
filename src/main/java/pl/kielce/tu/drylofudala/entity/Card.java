package pl.kielce.tu.drylofudala.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import pl.kielce.tu.drylofudala.model.PositionType;

@Entity
@Table
public class Card extends BaseEntity {
	private String name;
	@Enumerated(EnumType.STRING)
	private PositionType type;
	private int points;
	private String imageFileName;

	protected Card() {
	}

	public Card(final String name, final PositionType type, final int points, final String imageFileName) {
		this.name = name;
		this.type = type;
		this.points = points;
		this.imageFileName = imageFileName;
	}

	public String getName() {
		return name;
	}

	public PositionType getType() {
		return type;
	}

	public int getPoints() {
		return points;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}

		final Card card = (Card) o;

		if (getPoints() != card.getPoints()) {
			return false;
		}
		if (getName() != null ? !getName().equals(card.getName()) : card.getName() != null) {
			return false;
		}
		if (getType() != card.getType()) {
			return false;
		}
		return getImageFileName() != null ? getImageFileName().equals(card.getImageFileName()) : card.getImageFileName() == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (getName() != null ? getName().hashCode() : 0);
		result = 31 * result + (getType() != null ? getType().hashCode() : 0);
		result = 31 * result + getPoints();
		result = 31 * result + (getImageFileName() != null ? getImageFileName().hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Card{" +
				"name='" + name + '\'' +
				", type=" + type +
				", points=" + points +
				", imageFileName='" + imageFileName + '\'' +
				"} " + super.toString();
	}
}
