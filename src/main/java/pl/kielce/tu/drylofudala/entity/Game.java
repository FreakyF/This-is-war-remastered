package pl.kielce.tu.drylofudala.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import pl.kielce.tu.drylofudala.model.Result;

import java.util.List;
import java.util.Objects;

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

    protected Game(){
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
        Game game = (Game) o;
        return Objects.equals(getFirstPlayer(), game.getFirstPlayer())
                && Objects.equals(getSecondPlayer(), game.getSecondPlayer())
                && Objects.equals(getRounds(), game.getRounds())
                && getResult() == game.getResult();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstPlayer(), getSecondPlayer(), getRounds(), getResult());
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
