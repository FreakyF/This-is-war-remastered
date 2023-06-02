package pl.kielce.tu.drylofudala.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "rounds")
public class Round extends BaseEntity {
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "round_id")
    private List<Card> firstPlayerMeeleRow;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "round_id")
    private List<Card> firstPlayerRangeRow;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "round_id")
    private List<Card> secondPlayerMeeleRow;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "round_id")
    private List<Card> secondPlayerRangeRow;

    public List<Card> getFirstPlayerMeeleRow() {
        return firstPlayerMeeleRow;
    }

    public List<Card> getFirstPlayerRangeRow() {
        return firstPlayerRangeRow;
    }

    public List<Card> getSecondPlayerMeeleRow() {
        return secondPlayerMeeleRow;
    }

    public List<Card> getSecondPlayerRangeRow() {
        return secondPlayerRangeRow;
    }
}
