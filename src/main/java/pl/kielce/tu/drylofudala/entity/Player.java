package pl.kielce.tu.drylofudala.entity;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table
@Access(AccessType.FIELD)
public class Player extends BaseEntity {
	private String name;
	private String hashedPassword;

	private byte[] passwordSalt;

	protected Player() {
	}

	public Player(String name, String hashedPassword, byte[] passwordSalt) {
		this.name = name;
		this.hashedPassword = hashedPassword;
		this.passwordSalt = passwordSalt;
	}

	public String getName() {
		return name;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}
}
