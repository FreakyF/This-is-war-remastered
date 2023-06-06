package pl.kielce.tu.drylofudala.entity;

import jakarta.persistence.*;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Round round = (Round) o;
        return Objects.equals(getFirstPlayerMeleeRow(), round.getFirstPlayerMeleeRow())
                && Objects.equals(getFirstPlayerRangeRow(), round.getFirstPlayerRangeRow())
                && Objects.equals(getSecondPlayerMeleeRow(), round.getSecondPlayerMeleeRow())
                && Objects.equals(getSecondPlayerRangeRow(), round.getSecondPlayerRangeRow());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstPlayerMeleeRow(), getFirstPlayerRangeRow(), getSecondPlayerMeleeRow(), getSecondPlayerRangeRow());
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
