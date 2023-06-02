package pl.kielce.tu.drylofudala.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "players")
public class Player extends BaseEntity {
    private String name;
    private String hashedPassword;

    public String getName() {
        return name;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }
}
