package chbucket.inventory.systemrepositoryservice.controller;

import chbucket.inventory.systemrepositoryservice.dto.request.AuthRequestDto;
import chbucket.inventory.systemrepositoryservice.dto.user.UserAccountDto;
import chbucket.inventory.systemrepositoryservice.dto.user.UserSessionDto;
import chbucket.inventory.systemrepositoryservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerTest {

    private MockMvc mockMvc;
    private UserService userService;

    private UserAccountDto userAccountDto;
    private AuthRequestDto authRequestDto;
    private UserSessionDto userSessionDto;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        UserController userController = new UserController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        // Initialize DTO objects for testing
        userAccountDto = new UserAccountDto();
        userAccountDto.setId("1L");
        userAccountDto.setUsername("testuser");
        userAccountDto.setPassword("testpassword");
        userAccountDto.setEmail("test@example.com");

        authRequestDto = new AuthRequestDto();
        authRequestDto.setUsername("testuser");
        authRequestDto.setPassword("testpassword");

        userSessionDto = new UserSessionDto();
        userSessionDto.setId("1L");
        userSessionDto.setUserAccountId("1L");
        userSessionDto.setToken("test_token");
    }

    @Test
    void userAccountRegisterSuccess() throws Exception{
        // Mock the service method call
        when(userService.saveUserAccount(any(UserAccountDto.class))).thenReturn(userAccountDto);

        // Perform the HTTP POST request
        mockMvc.perform(post("/repository/user/v1/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"testuser\", \"password\":\"testpassword\", \"email\":\"test@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.username").value("testuser"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.email").value("test@example.com"));

        // Verify that the service method was called
        verify(userService, times(1)).saveUserAccount(any(UserAccountDto.class));
    }

    @Test
    void userAccountRegisterIsBadRequest() throws Exception {
        // Mock the service method call to throw an exception
        when(userService.saveUserAccount(any(UserAccountDto.class)))
                .thenThrow(new RuntimeException("Test Exception"));

        // Perform the HTTP POST request and expect an internal server error (500)
        mockMvc.perform(post("/repository/user/v1/register")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        // Verify that the service method was called
        verify(userService, times(0)).saveUserAccount(any(UserAccountDto.class));
    }

    @Test
    void userAccountRetrieveByUsernameSuccess() throws Exception{
        String username = "testuser";

        // Mock the service method call
        when(userService.retrieveUserAccountByUserName(username)).thenReturn(userAccountDto);

        // Perform the HTTP GET request and expect a successful response (200)
        mockMvc.perform(get("/repository/user/v1/retrieve/user")
                        .param("username", username))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.username").value(username))
                .andExpect(jsonPath("$.data.email").value("test@example.com"));

        // Verify that the service method was called
        verify(userService, times(1)).retrieveUserAccountByUserName(username);
    }

    @Test
    void userAccountRetrieveByUsernameIsBadRequest() throws Exception{
        String username = "testuser";

        // Mock the service method call
        when(userService.retrieveUserAccountByUserName(username)).thenReturn(userAccountDto);

        // Perform the HTTP GET request and expect a successful response (200)
        mockMvc.perform(get("/repository/user/v1/retrieve/user")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        // Verify that the service method was called
        verify(userService, times(0)).retrieveUserAccountByUserName(username);
    }

    @Test
    void userAccountLoginSuccess() throws Exception{
        // Mock the service method call
        when(userService.authUserAccount(any(AuthRequestDto.class))).thenReturn(userAccountDto);

        // Perform the HTTP POST request
        mockMvc.perform(post("/repository/user/v1/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"testuser\", \"password\":\"testpassword\", \"email\":\"test@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.username").value("testuser"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.email").value("test@example.com"));

        // Verify that the service method was called
        verify(userService, times(1)).authUserAccount(any(AuthRequestDto.class));
    }

    @Test
    void userAccountLoginIsBadRequest() throws Exception{
        // Mock the service method call
        when(userService.authUserAccount(any(AuthRequestDto.class))).thenReturn(userAccountDto);

        // Perform the HTTP POST request
        mockMvc.perform(post("/repository/user/v1/login")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        // Verify that the service method was called
        verify(userService, times(0)).authUserAccount(any(AuthRequestDto.class));
    }

    @Test
    void userSessionSaveSuccess() throws Exception{
        // Mock the service method call
        when(userService.saveUserSession(any(UserSessionDto.class))).thenReturn(userSessionDto);

        // Perform the HTTP POST request
        mockMvc.perform(post("/repository/user/v1/save/session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userAccountId\":\"1L\", \"token\":\"test_token\"}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.userAccountId").value("1L"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.token").value("test_token"));

        // Verify that the service method was called
        verify(userService, times(1)).saveUserSession(any(UserSessionDto.class));
    }

    @Test
    void userSessionSaveIsBadRequest() throws Exception{
        // Mock the service method call
        when(userService.saveUserSession(any(UserSessionDto.class))).thenReturn(userSessionDto);

        // Perform the HTTP POST request
        mockMvc.perform(post("/repository/user/v1/save/session")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        // Verify that the service method was called
        verify(userService, times(0)).saveUserSession(any(UserSessionDto.class));
    }

    @Test
    void userSessionRetrieveByUserIdSuccess() throws Exception{
        String userId = "1L";
        // Mock the service method call
        when(userService.retrieveUserSessionById(userId)).thenReturn(userSessionDto);

        // Perform the HTTP POST request
        mockMvc.perform(get("/repository/user/v1/retrieve/session")
                        .param("userId",userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userAccountId\":\"1L\", \"token\":\"test_token\"}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.userAccountId").value("1L"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.token").value("test_token"));

        // Verify that the service method was called
        verify(userService, times(1)).retrieveUserSessionById(userId);
    }

    @Test
    void userSessionRetrieveByUserIdIsBadRequest() throws Exception{
        String userId = "1L";
        // Mock the service method call
        when(userService.retrieveUserSessionById(userId)).thenReturn(userSessionDto);

        // Perform the HTTP POST request
        mockMvc.perform(get("/repository/user/v1/retrieve/session")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        // Verify that the service method was called
        verify(userService, times(0)).retrieveUserSessionById(userId);
    }


}