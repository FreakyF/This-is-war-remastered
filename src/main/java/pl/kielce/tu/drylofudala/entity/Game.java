package pl.kielce.tu.drylofudala.entity;

import jakarta.persistence.*;
import pl.kielce.tu.drylofudala.model.Result;

import java.util.List;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

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
}
