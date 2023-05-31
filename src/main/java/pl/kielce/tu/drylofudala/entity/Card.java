package pl.kielce.tu.drylofudala.entity;

import pl.kielce.tu.drylofudala.model.PositionType;

public final class Card extends BaseEntity {
    private final String name;
    private final PositionType type;
    private final int points;
    private final String imageFileName;

    public Card(int id, String name, PositionType type, int points, String imageFileName) {
        super(id);
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