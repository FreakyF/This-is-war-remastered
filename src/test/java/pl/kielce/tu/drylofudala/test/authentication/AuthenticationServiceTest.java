package pl.kielce.tu.drylofudala.test.authentication;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import pl.kielce.tu.drylofudala.authentication.hasher.IHasher;
import pl.kielce.tu.drylofudala.authentication.result.RegistrationResult;
import pl.kielce.tu.drylofudala.authentication.result.ValidationResult;
import pl.kielce.tu.drylofudala.authentication.service.AuthenticationService;
import pl.kielce.tu.drylofudala.authentication.service.IAuthenticationService;
import pl.kielce.tu.drylofudala.persistance.repository.player.IPlayerRepository;

class AuthenticationServiceTest {
	private IPlayerRepository playerRepository;
	private IAuthenticationService authenticationService;
	private IHasher hasher;

	static Stream<Arguments> providePasswordsAndExpectedMessages() {
		return Stream.of(
				Arguments.of(
						"short",
						List.of(ValidationResult.PASSWORD_TOO_SHORT,
								ValidationResult.PASSWORD_WITHOUT_UPPERCASE,
								ValidationResult.PASSWORD_WITHOUT_SPECIAL_CHARACTER,
								ValidationResult.PASSWORD_WITHOUT_NUMBER)
				),
				Arguments.of("Password123",
						List.of(ValidationResult.PASSWORD_WITHOUT_SPECIAL_CHARACTER)
				),
				Arguments.of("Password@@@@",
						List.of(ValidationResult.PASSWORD_WITHOUT_NUMBER)
				),
				Arguments.of("password123@",
						List.of(ValidationResult.PASSWORD_WITHOUT_UPPERCASE)
				),
				Arguments.of("PASSWORD123@",
						List.of(ValidationResult.PASSWORD_WITHOUT_LOWERCASE)
				),
				Arguments.of(new String(new char[33]).replace("\0", "A") + "a123@",
						List.of(ValidationResult.PASSWORD_TOO_LONG)
				)
		);
	}

	@BeforeEach
	public void setUp() {
		playerRepository = Mockito.mock(IPlayerRepository.class);
		hasher = Mockito.mock(IHasher.class);
		authenticationService = new AuthenticationService(playerRepository, hasher);
	}

	@Test
	void register_when_nicknameIsAlreadyTaken_then_returnsRegistrationResult_with_NICKNAME_ALREADY_TAKEN_type() {
		// given
		String nickname = "ExistingNickname";
		when(playerRepository.isNicknameTaken(nickname)).thenReturn(true);

		// when
		RegistrationResult result = authenticationService.register(nickname, "");

		// then
		assertFalse(result.success());
		assertNull(result.newPlayer());
		verify(playerRepository, never()).save(any());
	}

	@Test
	void register_when_playerWithGivenNicknameDoesNotExists_then_returnsRegistrationResult_with_SUCCESS_type() {
		// given
		String nickname = "NewNickname";
		when(playerRepository.isNicknameTaken(nickname)).thenReturn(false);

		// when
		RegistrationResult result = authenticationService.register(nickname, "");

		// then
		assertTrue(result.success());
		assertNotNull(result.newPlayer());
		verify(playerRepository).save(any());
	}

	@ParameterizedTest
	@MethodSource("providePasswordsAndExpectedMessages")
	void isPasswordValid_when_InvalidPasswordGiven_Returns_validValidationResult(String password, List<String> expectedMessages) {
		// when
		ValidationResult result = authenticationService.isPasswordValid(password);

		// then
		assertFalse(result.valid());
		assertNotNull(result.messages());
		assertEquals(expectedMessages.size(), result.messages().size());
		assertEquals(new HashSet<>(expectedMessages), new HashSet<>(result.messages()));
	}

	@Test
	void isPasswordValid_whenValidPasswordGiven_Returns_ValidationResult_with_emptyMessages() {
		// given
		final String password = "Password123@";

		// when
		ValidationResult result = authenticationService.isPasswordValid(password);

		// then
		assertTrue(result.valid());
		assertNull(result.messages());
	}

	@Test
	void isNicknameValid_when_tooShortNicknameGiven_returns_ValidationResult_with_NICKNAME_TOO_SHORT_message() {
		// given
		String nickname = "ab";

		// when
		ValidationResult result = authenticationService.isNicknameValid(nickname);

		// then
		assertFalse(result.valid());
		assertNotNull(result.messages());
		assertEquals(1, result.messages().size());
		assertEquals(ValidationResult.NICKNAME_TOO_SHORT, result.messages().get(0));
	}

	@Test
	void isNicknameValid_when_TooLongNicknameGiven_Returns_ValidationResult_with_NICKNAME_TOO_LONG_message() {
		// given
		String nickname = "ValidNick";

		// when
		ValidationResult result = authenticationService.isNicknameValid(nickname);

		// then
		assertTrue(result.valid());
		assertNull(result.messages());
	}
}
