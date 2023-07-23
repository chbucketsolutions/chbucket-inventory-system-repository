package chbucket.inventory.systemrepositoryservice.mapper;

import chbucket.inventory.systemrepositoryservice.dto.user.UserAccountDto;
import chbucket.inventory.systemrepositoryservice.model.user.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserAccountMapperTest {

    private UserAccountDto userAccountDto;
    private UserAccount userAccount;
    private UserAccountMapper userAccountMapper;

    @BeforeEach
    void setUp() {
        userAccountDto = new UserAccountDto();
        userAccountDto.setId("1L");
        userAccountDto.setUsername("testuser");
        userAccountDto.setPassword("testpassword");
        userAccountDto.setEmail("test@example.com");
        userAccountDto.setContactNumber("1234567890");
        userAccountDto.setAddress("Test Address");
        userAccountDto.setFullName("Test User");

        userAccount = new UserAccount();
        userAccount.setId("1L");
        userAccount.setUsername("testuser");
        userAccount.setPassword("testpassword");
        userAccount.setEmail("test@example.com");
        userAccount.setContactNumber("1234567890");
        userAccount.setAddress("Test Address");
        userAccount.setFullName("Test User");

        userAccountMapper = UserAccountMapper.of();
    }

    @Test
    void testMapUserDtoToUserAccount() {
        UserAccount result = userAccountMapper.setUserAccountDto(userAccountDto).mapUserDtoToUserAccount();

        assertEquals(userAccount.getUsername(), result.getUsername());
        assertEquals(userAccount.getPassword(), result.getPassword());
        assertEquals(userAccount.getFullName(), result.getFullName());
        assertEquals(userAccount.getAddress(), result.getAddress());
        assertEquals(userAccount.getEmail(), result.getEmail());
        assertEquals(userAccount.getContactNumber(), result.getContactNumber());
    }

    @Test
    void testMapUserAccountToUserDto() {
        UserAccountDto result = userAccountMapper.setUserAccount(userAccount).mapUserAccountToUserDto();

        assertEquals(userAccountDto.getId(), result.getId());
        assertEquals(userAccount.getUsername(), result.getUsername());
        assertEquals(userAccount.getPassword(), result.getPassword());
        assertEquals(userAccount.getEmail(), result.getEmail());
        assertEquals(userAccount.getContactNumber(), result.getContactNumber());
        assertEquals(userAccount.getAddress(), result.getAddress());
        assertEquals(userAccount.getFullName(), result.getFullName());
    }

    @Test
    void testMapUserDtoToUserAccountWithNullUserAccount() {
        UserAccount result = userAccountMapper.setUserAccountDto(buildNullUserAccountDto()).mapUserDtoToUserAccount();

        // Ensure that a new UserAccount is created when the input userAccount is null
        assertNull(result.getUsername());
        assertNull(result.getPassword());
        assertNull(result.getFullName());
        assertNull(result.getAddress());
        assertNull(result.getEmail());
        assertNull(result.getContactNumber());
    }

    @Test
    void testMapUserAccountToUserDtoWithNullUserAccountDto() {
        UserAccountDto result = userAccountMapper.setUserAccount(buildNullUserAccount()).mapUserAccountToUserDto();

        // Ensure that a new UserAccountDto is created when the input userAccountDto is null
        assertNull(result.getId());
        assertNull(result.getUsername());
        assertNull(result.getPassword());
        assertNull(result.getEmail());
        assertNull(result.getContactNumber());
        assertNull(result.getAddress());
        assertNull(result.getFullName());
    }

    UserAccount buildNullUserAccount(){
        UserAccount userAccount = new UserAccount();
        return userAccount;
    }

    UserAccountDto buildNullUserAccountDto(){
        UserAccountDto userAccountDto = new UserAccountDto();
        return userAccountDto;
    }
}