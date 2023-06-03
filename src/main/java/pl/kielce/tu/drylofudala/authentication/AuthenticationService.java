package pl.kielce.tu.drylofudala.authentication;

import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;
import org.jetbrains.annotations.NotNull;
import pl.kielce.tu.drylofudala.entity.Player;

import java.security.SecureRandom;

public class AuthenticationService implements IAuthenticationService {

    @Override
    public Player register(@NotNull String nickname, @NotNull String password) {
        byte[] salt = generateSalt();
        byte[] hashedPassword = hashPassword(password, salt);
        // TODO: Implement adding user to the database;
        return null;
    }

    private byte[] hashPassword(String password, byte[] salt) {
        Argon2Parameters parameters = new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
                .withSalt(salt)
                .withIterations(AuthenticationConfig.ITERATIONS)
                .withMemoryAsKB(AuthenticationConfig.MEMORY)
                .build();

        Argon2BytesGenerator generator = new Argon2BytesGenerator();
        generator.init(parameters);

        byte[] hashedPassword = new byte[AuthenticationConfig.HASHED_PASSWORD_LENGTH];
        generator.generateBytes(password.getBytes(), hashedPassword);

        return hashedPassword;
    }

    private byte[] generateSalt() {
        byte[] salt = new byte[AuthenticationConfig.SALT_LENGTH];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(salt);

        return salt;
    }

    @Override
    public AuthenticationResult login(@NotNull String nickname, @NotNull String password) {
        // TODO: Implement login method.
        return null;
    }

    @Override
    public ValidationResult isPasswordValid(@NotNull String password) {
        StringBuilder validationMessage = new StringBuilder();
        if (isTooShort(password, AuthenticationConfig.MIN_PASSWORD_LENGTH)) {
            validationMessage.append(ValidationMessage.PASSWORD_TOO_SHORT);
            validationMessage.append('\n');
        }
        if (isTooLong(password, AuthenticationConfig.MAX_PASSWORD_LENGTH)) {
            validationMessage.append(ValidationMessage.PASSWORD_TOO_LONG);
            validationMessage.append('\n');
        }
        if (containsLowercase(password)) {
            validationMessage.append(ValidationMessage.PASSWORD_WITHOUT_LOWERCASE);
            validationMessage.append('\n');
        }
        if (containsUppercase(password)) {
            validationMessage.append(ValidationMessage.PASSWORD_WITHOUT_UPPERCASE);
            validationMessage.append('\n');
        }
        if (containsSpecialCharacters(password)) {
            validationMessage.append(ValidationMessage.PASSWORD_WITHOUT_SPECIAL_CHARACTER);
            validationMessage.append('\n');
        }
        if (containsNumber(password)) {
            validationMessage.append(ValidationMessage.PASSWORD_WITHOUT_NUMBER);
            validationMessage.append('\n');
        }

        return !validationMessage.isEmpty()
                ? new ValidationResult(false, validationMessage.toString())
                : new ValidationResult(true);
    }

    @Override
    public ValidationResult isNicknameValid(@NotNull String nickname) {
        StringBuilder validationMessage = new StringBuilder();
        if (isTooShort(nickname, AuthenticationConfig.MIN_NICKNAME_LENGTH)) {
            validationMessage.append(ValidationMessage.NICKNAME_TOO_SHORT);
        } else if (isTooLong(nickname, AuthenticationConfig.MAX_NICKNAME_LENGTH)) {
            validationMessage.append(ValidationMessage.NICKNAME_TOO_LONG);
        }
        return !validationMessage.isEmpty()
                ? new ValidationResult(false, validationMessage.toString())
                : new ValidationResult(true);
    }

    private boolean isTooShort(final String text, int minLength) {
        return text.length() < minLength;
    }

    private boolean isTooLong(final String text, int maxLength) {
        return text.length() > maxLength;
    }

    private boolean containsLowercase(final String password) {
        return password.matches(".*[a-z].*");
    }

    private boolean containsUppercase(final String password) {
        return password.matches(".*[A-Z].*");
    }

    private boolean containsSpecialCharacters(final String password) {
        return password.matches(".*[^a-zA-Z0-9].*");
    }

    private boolean containsNumber(final String password) {
        return password.matches(".*\\d.*");
    }
}
