package pl.kielce.tu.drylofudala.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table
public class Round extends BaseEntity {
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "round_id")
	private List<Card> firstPlayerMeleeRow = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "round_id")
	private List<Card> firstPlayerRangeRow = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "round_id")
	private List<Card> secondPlayerMeleeRow = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "round_id")
	private List<Card> secondPlayerRangeRow = new ArrayList<>();

	protected Round() {
	}

	public List<Card> getFirstPlayerMeleeRow() {
		return firstPlayerMeleeRow;
	}

	public List<Card> getFirstPlayerRangeRow() {
		return firstPlayerRangeRow;
	}

	public List<Card> getSecondPlayerMeleeRow() {
		return secondPlayerMeleeRow;
	}

	public List<Card> getSecondPlayerRangeRow() {
		return secondPlayerRangeRow;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final Round round)) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		return Objects.equals(getFirstPlayerMeleeRow(), round.getFirstPlayerMeleeRow()) && Objects.equals(getFirstPlayerRangeRow(), round.getFirstPlayerRangeRow()) && Objects.equals(getSecondPlayerMeleeRow(), round.getSecondPlayerMeleeRow()) && Objects.equals(getSecondPlayerRangeRow(), round.getSecondPlayerRangeRow());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getFirstPlayerMeleeRow(), getFirstPlayerRangeRow(), getSecondPlayerMeleeRow(), getSecondPlayerRangeRow());
	}

	@Override
	public String toString() {
		return "Round{" +
				"firstPlayerMeleeRow=" + firstPlayerMeleeRow +
				", firstPlayerRangeRow=" + firstPlayerRangeRow +
				", secondPlayerMeleeRow=" + secondPlayerMeleeRow +
				", secondPlayerRangeRow=" + secondPlayerRangeRow +
				'}';
	}
}
