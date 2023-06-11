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
@Table(name = "round")
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

    protected Round() {
        firstPlayerMeeleRow = new ArrayList<>();
        firstPlayerRangeRow = new ArrayList<>();
        secondPlayerMeeleRow = new ArrayList<>();
        secondPlayerRangeRow = new ArrayList<>();
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Round round = (Round) o;
        return Objects.equals(getFirstPlayerMeeleRow(), round.getFirstPlayerMeeleRow()) && Objects.equals(getFirstPlayerRangeRow(), round.getFirstPlayerRangeRow()) && Objects.equals(getSecondPlayerMeeleRow(), round.getSecondPlayerMeeleRow()) && Objects.equals(getSecondPlayerRangeRow(), round.getSecondPlayerRangeRow());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstPlayerMeeleRow(), getFirstPlayerRangeRow(), getSecondPlayerMeeleRow(), getSecondPlayerRangeRow());
    }
}
