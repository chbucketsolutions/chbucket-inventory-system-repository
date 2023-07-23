package chbucket.inventory.systemrepositoryservice.service.impl;

import chbucket.inventory.systemrepositoryservice.dto.request.AuthRequestDto;
import chbucket.inventory.systemrepositoryservice.dto.user.UserAccountDto;
import chbucket.inventory.systemrepositoryservice.dto.user.UserSessionDto;
import chbucket.inventory.systemrepositoryservice.model.user.UserAccount;
import chbucket.inventory.systemrepositoryservice.model.user.UserSession;
import chbucket.inventory.systemrepositoryservice.repository.UserAccountRepository;
import chbucket.inventory.systemrepositoryservice.repository.UserSessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @Mock
    private UserAccountRepository userAccountRepository;

    @Mock
    private UserSessionRepository userSessionRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private UserAccount userAccount;

    private UserAccountDto userAccountDto;

    private AuthRequestDto authRequestDto;

    private UserSessionDto userSessionDto;

    private UserSession userSession;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        String username = "testuser";
        String userAccountId = "userAccountId";

        userAccountDto = new UserAccountDto();
        userAccountDto.setUsername("testuser");
        userAccountDto.setEmail("test@example.com");

        userAccount = new UserAccount();
        userAccount.setId(userAccountId);
        userAccount.setUsername(username);
        userAccount.setEmail("test@example.com");

        authRequestDto = new AuthRequestDto();
        authRequestDto.setUsername(username);

        userSessionDto = new UserSessionDto();
        userSessionDto.setUserAccountId(userAccountId);
        userSessionDto.setToken("test_token");

        userSession = new UserSession();
        userSession.setUserAccount(userAccount);
        userSession.setToken("test_token");
    }

    @Test
    void saveUserAccountSuccess() {

        when(userAccountRepository.save(Mockito.any(UserAccount.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        UserAccountDto savedUserAccountDto = userService.saveUserAccount(userAccountDto);

        assertNotNull(savedUserAccountDto);
        assertEquals(userAccountDto.getUsername(), savedUserAccountDto.getUsername());
        assertEquals(userAccountDto.getEmail(), savedUserAccountDto.getEmail());
    }

    @Test
    void retrieveUserAccountByUserNameSuccess() {
        String username = "testuser";

        when(userAccountRepository.findByUsername(username)).thenReturn(Optional.of(userAccount));

        UserAccountDto retrievedUserAccountDto = userService.retrieveUserAccountByUserName(username);

        assertNotNull(retrievedUserAccountDto);
        assertEquals(username, retrievedUserAccountDto.getUsername());
        assertEquals("test@example.com", retrievedUserAccountDto.getEmail());
    }

    @Test
    void retrieveUserAccountByUserNameReturnsNull() {
        // Set up the mock behavior for the userAccountRepository
        when(userAccountRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        // Call the method under test
        UserAccountDto result = userService.retrieveUserAccountByUserName("nonExistentUser");

        // Assert that the result is null
        assertNull(result);
    }

    @Test
    void authUserAccountSuccess() {
        String username = "testuser";

        when(userAccountRepository.findByUsername(username)).thenReturn(Optional.of(userAccount));

        UserAccountDto authUserAccountDto = userService.authUserAccount(authRequestDto);

        assertNotNull(authUserAccountDto);
        assertEquals(username, authUserAccountDto.getUsername());
        assertEquals("test@example.com", authUserAccountDto.getEmail());
    }

    @Test
    void authUserAccountReturnsNull() {

        when(userAccountRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        UserAccountDto authUserAccountDto = userService.authUserAccount(authRequestDto);

        assertNull(authUserAccountDto);
    }

    @Test
    void saveUserSessionSuccess() {
        String userAccountId = "userAccountId";

        when(userAccountRepository.findById(userAccountId)).thenReturn(Optional.of(userAccount));
        when(userSessionRepository.save(Mockito.any(UserSession.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        UserSessionDto savedUserSessionDto = userService.saveUserSession(userSessionDto);

        assertNotNull(savedUserSessionDto);
        assertEquals(userAccountId, savedUserSessionDto.getUserAccountId());
        assertEquals("test_token", savedUserSessionDto.getToken());
    }

    @Test
    void saveUserSessionReturnsNull() {
        when(userAccountRepository.findById(anyString())).thenReturn(Optional.empty());

        UserSessionDto savedUserSessionDto = userService.saveUserSession(userSessionDto);

        assertNull(savedUserSessionDto);
    }


    @Test
    void retrieveUserSessionByIdSuccess() {
        String userAccountId = "userAccountId";

        when(userAccountRepository.findById(userAccountId)).thenReturn(Optional.of(userAccount));
        when(userSessionRepository.findByUserAccount(userAccount)).thenReturn(Optional.of(userSession));

        UserSessionDto retrievedUserSessionDto = userService.retrieveUserSessionById(userAccountId);

        assertNotNull(retrievedUserSessionDto);
        assertEquals(userAccountId, retrievedUserSessionDto.getUserAccountId());
        assertEquals("test_token", retrievedUserSessionDto.getToken());
    }

    @Test
    void retrieveUserSessionByIdReturnsNull() {
        when(userAccountRepository.findById(anyString())).thenReturn(Optional.empty());
        when(userSessionRepository.findByUserAccount(any())).thenReturn(Optional.empty());

        UserSessionDto retrievedUserSessionDto = userService.retrieveUserSessionById(anyString());

        assertNull(retrievedUserSessionDto);
    }
}