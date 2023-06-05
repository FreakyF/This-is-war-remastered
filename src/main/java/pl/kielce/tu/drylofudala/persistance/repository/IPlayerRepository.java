package pl.kielce.tu.drylofudala.persistance.repository;

public interface IPlayerRepository {
	boolean isNicknameAlreadyTaken(final String nickname);
}
