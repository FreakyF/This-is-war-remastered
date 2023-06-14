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
import java.util.Objects;
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

	public Game(final Player firstPlayer,
	            final Player secondPlayer,
	            final List<Round> rounds,
	            final Result result) {
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
		final Game game = (Game) o;
		return Objects.equals(firstPlayer, game.firstPlayer) && Objects.equals(secondPlayer, game.secondPlayer) && Objects.equals(rounds, game.rounds) && result == game.result;
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), firstPlayer, secondPlayer, rounds, result);
	}

	@Override
	public String toString() {
		return "Game{" +
				"firstPlayer=" + firstPlayer +
				", secondPlayer=" + secondPlayer +
				", rounds=" + rounds +
				", result=" + result +
				'}';
	}
}
