package pl.kielce.tu.drylofudala.entity;

import jakarta.persistence.*;
import pl.kielce.tu.drylofudala.model.PositionType;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private PositionType type;
    private int points;
    private String imageFileName;

    protected Card(){
    }

    public Card(String name, PositionType type, int points, String imageFileName) {
        this.name = name;
        this.type = type;
        this.points = points;
        this.imageFileName = imageFileName;
    }
}
