package pl.kielce.tu.drylofudala.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "player")
public class Player extends BaseEntity {
    private String name;
    private String hashedPassword;

    protected Player() {
    }

    public Player(String name, String hashedPassword) {
        this.name = name;
        this.hashedPassword = hashedPassword;
    }

    public String getName() {
        return name;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(getName(), player.getName()) && Objects.equals(getHashedPassword(), player.getHashedPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getHashedPassword());
    }
}
