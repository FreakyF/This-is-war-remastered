package pl.kielce.tu.drylofudala.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

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
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}

		final Round round = (Round) o;

		if (getFirstPlayerMeleeRow() != null ? !getFirstPlayerMeleeRow().equals(round.getFirstPlayerMeleeRow()) : round.getFirstPlayerMeleeRow() != null) {
			return false;
		}
		if (getFirstPlayerRangeRow() != null ? !getFirstPlayerRangeRow().equals(round.getFirstPlayerRangeRow()) : round.getFirstPlayerRangeRow() != null) {
			return false;
		}
		if (getSecondPlayerMeleeRow() != null ? !getSecondPlayerMeleeRow().equals(round.getSecondPlayerMeleeRow()) : round.getSecondPlayerMeleeRow() != null) {
			return false;
		}
		return getSecondPlayerRangeRow() != null ? getSecondPlayerRangeRow().equals(round.getSecondPlayerRangeRow()) : round.getSecondPlayerRangeRow() == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (getFirstPlayerMeleeRow() != null ? getFirstPlayerMeleeRow().hashCode() : 0);
		result = 31 * result + (getFirstPlayerRangeRow() != null ? getFirstPlayerRangeRow().hashCode() : 0);
		result = 31 * result + (getSecondPlayerMeleeRow() != null ? getSecondPlayerMeleeRow().hashCode() : 0);
		result = 31 * result + (getSecondPlayerRangeRow() != null ? getSecondPlayerRangeRow().hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Round{" +
				"firstPlayerMeeleRow=" + firstPlayerMeleeRow +
				", firstPlayerRangeRow=" + firstPlayerRangeRow +
				", secondPlayerMeeleRow=" + secondPlayerMeleeRow +
				", secondPlayerRangeRow=" + secondPlayerRangeRow +
				"} " + super.toString();
	}
}
