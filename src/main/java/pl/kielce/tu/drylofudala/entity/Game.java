package pl.kielce.tu.drylofudala.entity;

import jakarta.persistence.*;
import pl.kielce.tu.drylofudala.model.Result;

import java.util.List;

@Entity
@Table(name = "games")
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
}
