package pl.kielce.tu.drylofudala.authentication;

public final class AuthenticationConfig {
	public static final int ITERATIONS = 10;
	public static final int MEMORY = 65536;
	public static final int HASHED_PASSWORD_LENGTH = 64;
	public static final int SALT_LENGTH = 16;
	public static final int MIN_PASSWORD_LENGTH = 8;
	public static final int MAX_PASSWORD_LENGTH = 32;
	public static final int MIN_NICKNAME_LENGTH = 3;
	public static final int MAX_NICKNAME_LENGTH = 50;

	private AuthenticationConfig() {

	}
}
