package pl.kielce.tu.drylofudala.test.authentication;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import pl.kielce.tu.drylofudala.authentication.*;
import pl.kielce.tu.drylofudala.persistance.repository.player.*;

class AuthenticationServiceTest {
	private IPlayerRepository playerRepository;
	private IAuthenticationService authenticationService;
	private IHasher hasher;

	@BeforeEach
	public void setUp() {
		playerRepository = Mockito.mock(IPlayerRepository.class);
		hasher = Mockito.mock(IHasher.class);
		authenticationService = new AuthenticationService(playerRepository, hasher);
	}

	@Test
	void testRegisterWithExistingNickname() {
		String nickname = "ExistingNickname";
		String password = "P@ssw0rd";

		when(playerRepository.isNicknameTaken(nickname)).thenReturn(true);

		RegistrationResult result = authenticationService.register(nickname, password);

		assertEquals(RegistrationResultType.NICKNAME_ALREADY_TAKEN, result.type());
		assertNull(result.newPlayer());
		verify(playerRepository, never()).save(any());
	}

	@Test
	void testRegisterWithNewNickname() {
		String nickname = "NewNickname";
		String password = "P@ssw0rd";

		when(playerRepository.isNicknameTaken(nickname)).thenReturn(false);

		RegistrationResult result = authenticationService.register(nickname, password);

		assertEquals(RegistrationResultType.SUCCESS, result.type());
		assertNotNull(result.newPlayer());
		verify(playerRepository).save(any());
	}

	@Test
	void testIsPasswordValid_InvalidPassword() {
		String password = "pass";

		ValidationResult result = authenticationService.isPasswordValid(password);

		assertFalse(result.isValid());
		assertNotNull(result.getMessage());
	}

	@Test
	void testIsPasswordValid_ValidPassword() {
		String password = "P@ssw0rd";

		ValidationResult result = authenticationService.isPasswordValid(password);

		assertTrue(result.isValid());
		assertNull(result.getMessage());
	}

	@Test
	void testIsNicknameValid_InvalidNickname() {
		String nickname = "short";

		ValidationResult result = authenticationService.isNicknameValid(nickname);

		assertFalse(result.isValid());
		assertNotNull(result.getMessage());
	}

	@Test
	void testIsNicknameValid_ValidNickname() {
		String nickname = "ValidNick";

		ValidationResult result = authenticationService.isNicknameValid(nickname);

		assertTrue(result.isValid());
		assertNull(result.getMessage());
	}
}
