package pl.kielce.tu.drylofudala.entity;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Arrays;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		Player player = (Player) o;
		return Objects.equals(getName(), player.getName()) && Objects.equals(getHashedPassword(), player.getHashedPassword()) && Arrays.equals(passwordSalt, player.passwordSalt);
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(super.hashCode(), getName(), getHashedPassword());
		result = 31 * result + Arrays.hashCode(passwordSalt);
		return result;
	}

	@Override
	public String toString() {
		return "Player{" +
				"name='" + name + '\'' +
				", hashedPassword='" + hashedPassword + '\'' +
				", passwordSalt=" + Arrays.toString(passwordSalt) +
				"} " + super.toString();
	}
}
