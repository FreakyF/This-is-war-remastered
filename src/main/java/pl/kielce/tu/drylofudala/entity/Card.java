package pl.kielce.tu.drylofudala.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.util.Objects;
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

	public Card(String name, PositionType type, int points, String imageFileName) {
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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Card card = (Card) o;
		return getPoints() == card.getPoints()
				&& Objects.equals(getName(), card.getName())
				&& getType() == card.getType()
				&& Objects.equals(getImageFileName(), card.getImageFileName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getName(), getType(), getPoints(), getImageFileName());
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
