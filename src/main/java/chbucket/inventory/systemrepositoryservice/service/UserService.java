package chbucket.inventory.systemrepositoryservice.service;

import chbucket.inventory.systemrepositoryservice.dto.request.AuthRequestDto;
import chbucket.inventory.systemrepositoryservice.dto.user.UserAccountDto;
import chbucket.inventory.systemrepositoryservice.dto.user.UserSessionDto;

import java.util.List;

public interface UserService {

    UserAccountDto saveUserAccount(UserAccountDto userAccountDto);
    UserAccountDto authUserAccount(AuthRequestDto authRequestDto);
    UserAccountDto retrieveUserAccountByUserName(String username);
    UserSessionDto saveUserSession(UserSessionDto userSessionDto);
    UserSessionDto retrieveUserSessionById(String userId);
}
