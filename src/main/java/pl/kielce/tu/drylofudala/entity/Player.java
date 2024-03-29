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

	public Player(final String name, final String hashedPassword, final byte[] passwordSalt) {
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

	public byte[] getPasswordSalt() {
		return passwordSalt;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		final Player player = (Player) o;
		return Objects.equals(name, player.name) && Objects.equals(hashedPassword, player.hashedPassword) && Arrays.equals(passwordSalt, player.passwordSalt);
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(super.hashCode(), name, hashedPassword);
		result = 31 * result + Arrays.hashCode(passwordSalt);
		return result;
	}

	@Override
	public String toString() {
		return "Player{" +
				"name='" + name + '\'' +
				", hashedPassword='" + hashedPassword + '\'' +
				", passwordSalt=" + Arrays.toString(passwordSalt) +
				'}';
	}
}
