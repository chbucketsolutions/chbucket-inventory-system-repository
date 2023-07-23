package chbucket.inventory.systemrepositoryservice.mapper;

import chbucket.inventory.systemrepositoryservice.dto.user.UserSessionDto;
import chbucket.inventory.systemrepositoryservice.model.user.UserAccount;
import chbucket.inventory.systemrepositoryservice.model.user.UserSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserSessionMapperTest {

    private UserSessionDto userSessionDto;
    private UserSession userSession;
    private UserAccount userAccount;
    private UserSessionMapper userSessionMapper;

    @BeforeEach
    void setUp() {
        userAccount = new UserAccount();
        userAccount.setId("1L");
        userAccount.setUsername("testuser");
        userAccount.setPassword("testpassword");
        userAccount.setEmail("test@example.com");
        userAccount.setContactNumber("1234567890");
        userAccount.setAddress("Test Address");
        userAccount.setFullName("Test User");

        userSessionDto = new UserSessionDto();
        userSessionDto.setId("1L");
        userSessionDto.setUserAccountId("1L");
        userSessionDto.setToken("test_token");

        userSession = new UserSession();
        userSession.setId("1L");
        userSession.setUserAccount(userAccount);
        userSession.setToken("test_token");

        userSessionMapper = UserSessionMapper.of();
    }

    @Test
    void testMapUserSessionDtotoUserSession() {
        UserSession result = userSessionMapper.setUserSessionDto(userSessionDto).setUserAccount(userAccount)
                .mapUserSessionDtotoUserSession();

        assertEquals(userSession.getUserAccount().getId(), result.getUserAccount().getId());
        assertEquals(userSession.getToken(), result.getToken());
    }

    @Test
    void testMapUserSessiontoUserSessionDto() {
        UserSessionDto result = userSessionMapper.setUserSession(userSession).mapUserSessiontoUserSessionDto();

        assertEquals(userSessionDto.getId(), result.getId());
        assertEquals(userSession.getUserAccount().getId(), result.getUserAccountId());
        assertEquals(userSession.getToken(), result.getToken());
    }

    @Test
    void testMapUserSessionDtotoUserSessionWithNullUserSession() {
        UserSession result = userSessionMapper.setUserSessionDto(buildNullUserSessionDto()).setUserAccount(buildNullUserAccount())
                .mapUserSessionDtotoUserSession();

        // Ensure that a new UserSession is created when the input userSession is null
        assertNull(result.getUserAccount().getId());
        assertNull(result.getToken());
    }

    @Test
    void testMapUserSessiontoUserSessionDtoWithNullUserSessionDto() {
        UserSessionDto result = userSessionMapper.setUserSession(buildNullUserSession()).mapUserSessiontoUserSessionDto();

        // Ensure that a new UserSessionDto is created when the input userSessionDto is null
        assertNull(result.getId());
        assertNull(result.getUserAccountId());
        assertNull(result.getToken());
    }

    UserSession buildNullUserSession(){
        UserSession userSession = new UserSession();
        userSession.setToken(null);
        userSession.setUserAccount(buildNullUserAccount());
        return userSession;
    }

    UserSessionDto buildNullUserSessionDto(){
        UserSessionDto userSessionDto = new UserSessionDto();
        userSessionDto.setToken(null);
        userSessionDto.setUserAccountId(null);
        userSessionDto.setId(null);
        return userSessionDto;
    }

    UserAccount buildNullUserAccount(){
        UserAccount userAccount = new UserAccount();
        return userAccount;
    }
}