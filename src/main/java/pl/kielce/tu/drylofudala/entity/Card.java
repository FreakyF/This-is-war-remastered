package pl.kielce.tu.drylofudala.entity;

import jakarta.persistence.*;
import pl.kielce.tu.drylofudala.model.PositionType;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private PositionType type;
    private int points;
    private String imageFileName;
}
