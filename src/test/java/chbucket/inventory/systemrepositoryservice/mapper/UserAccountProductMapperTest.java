package chbucket.inventory.systemrepositoryservice.mapper;

import chbucket.inventory.systemrepositoryservice.dto.user.UserAccountProductDto;
import chbucket.inventory.systemrepositoryservice.model.user.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserAccountProductMapperTest {
    private UserAccount userAccount;
    private UserAccountProductDto userAccountProductDto;
    private UserAccountProductMapper userAccountProductMapper;

    @BeforeEach
    void setUp(){
        userAccount = new UserAccount();
        userAccount.setId("1L");
        userAccount.setUsername("testuser");
        userAccount.setPassword("testpassword");
        userAccount.setEmail("test@example.com");
        userAccount.setContactNumber("1234567890");
        userAccount.setAddress("Test Address");
        userAccount.setFullName("Test User");

        userAccountProductDto = new UserAccountProductDto();
        userAccountProductDto.setId("1L");
        userAccountProductDto.setUsername("testuser");
        userAccountProductDto.setFullName("Test User");

        userAccountProductMapper = UserAccountProductMapper.of();
    }

    @Test
    void testMapUserAccountProductDtoToUserAccount(){
        UserAccount result = userAccountProductMapper.setUserAccountProductDto(userAccountProductDto)
                .mapUserDtoToUserAccountProduct();

        assertEquals(userAccount.getUsername(), result.getUsername());
        assertEquals(userAccount.getFullName(), result.getFullName());
    }

    @Test
    void testMapUserAccountToUserAccountProductDto(){
        UserAccountProductDto result = userAccountProductMapper.setUserAccountProduct(userAccount)
                .mapUserAccountToUserDto();

        assertEquals(userAccountProductDto.getId(), result.getId());
        assertEquals(userAccountProductDto.getUsername(), result.getUsername());
        assertEquals(userAccountProductDto.getFullName(), result.getFullName());
    }

    @Test
    void testMapUserAccountProductDtoToUserAccountWithNullUserAccount(){
        UserAccount result = userAccountProductMapper.setUserAccountProductDto(buildNullUserAccountProductDto())
                .mapUserDtoToUserAccountProduct();

        assertNull(result.getUsername());
        assertNull(result.getFullName());
    }

    @Test
    void testMapUserAccountToUserAccountProductDtoWithNullUserAccountProductDto(){
        UserAccountProductDto result = userAccountProductMapper.setUserAccountProduct(buildNullUserAccount())
                .mapUserAccountToUserDto();

        assertNull(result.getId());
        assertNull(result.getUsername());
        assertNull(result.getFullName());
    }

    UserAccount buildNullUserAccount(){
        UserAccount userAccount = new UserAccount();
        userAccount.setId(null);
        userAccount.setUsername(null);
        userAccount.setPassword(null);
        userAccount.setEmail(null);
        userAccount.setContactNumber(null);
        userAccount.setAddress(null);
        userAccount.setFullName(null);

        return userAccount;
    }

    UserAccountProductDto buildNullUserAccountProductDto(){
        UserAccountProductDto userAccountProductDto = new UserAccountProductDto();

        userAccountProductDto.setId(null);
        userAccountProductDto.setUsername(null);
        userAccountProductDto.setFullName(null);

        return userAccountProductDto;
    }

}