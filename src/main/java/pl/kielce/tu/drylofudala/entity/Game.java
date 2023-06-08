package pl.kielce.tu.drylofudala.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import pl.kielce.tu.drylofudala.model.Result;

@Entity
@Table
public class Game extends BaseEntity {
	@ManyToOne
	@JoinColumn(name = "first_player_id")
	private Player firstPlayer;

	@ManyToOne
	@JoinColumn(name = "second_player_id")
	private Player secondPlayer;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "game_id")
	private List<Round> rounds;

	@Enumerated(EnumType.STRING)
	private Result result;

	protected Game() {
	}

	public Game(Player firstPlayer,
	            Player secondPlayer,
	            List<Round> rounds,
	            Result result) {
		this.firstPlayer = firstPlayer;
		this.secondPlayer = secondPlayer;
		this.rounds = rounds;
		this.result = result;
	}

	public Player getFirstPlayer() {
		return firstPlayer;
	}

	public Player getSecondPlayer() {
		return secondPlayer;
	}

	public List<Round> getRounds() {
		return rounds;
	}

	public Result getResult() {
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		Game game = (Game) o;

		if (getFirstPlayer() != null ? !getFirstPlayer().equals(game.getFirstPlayer()) : game.getFirstPlayer() != null)
			return false;
		if (getSecondPlayer() != null ? !getSecondPlayer().equals(game.getSecondPlayer()) : game.getSecondPlayer() != null)
			return false;
		if (getRounds() != null ? !getRounds().equals(game.getRounds()) : game.getRounds() != null) return false;
		return getResult() == game.getResult();
	}

	@Override
	public int hashCode() {
		int result1 = super.hashCode();
		result1 = 31 * result1 + (getFirstPlayer() != null ? getFirstPlayer().hashCode() : 0);
		result1 = 31 * result1 + (getSecondPlayer() != null ? getSecondPlayer().hashCode() : 0);
		result1 = 31 * result1 + (getRounds() != null ? getRounds().hashCode() : 0);
		result1 = 31 * result1 + (getResult() != null ? getResult().hashCode() : 0);
		return result1;
	}

	@Override
	public String toString() {
		return "Game{" +
				"firstPlayer=" + firstPlayer +
				", secondPlayer=" + secondPlayer +
				", rounds=" + rounds +
				", result=" + result +
				"} " + super.toString();
	}
}
