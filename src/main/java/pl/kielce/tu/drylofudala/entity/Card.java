package pl.kielce.tu.drylofudala.entity;

import jakarta.persistence.*;
import pl.kielce.tu.drylofudala.model.PositionType;

@Entity
@Table(name = "cards")
public class Card extends BaseEntity {
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

    public String getName() {
        return name;
    }

    public PositionType getType() {
        return type;
    }

    public int getPoints() {
        return points;
    }

    public String getImageFileName() {
        return imageFileName;
    }
}
