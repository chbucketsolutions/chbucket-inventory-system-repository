package chbucket.inventory.systemrepositoryservice.mapper;

import chbucket.inventory.systemrepositoryservice.dto.user.UserSessionDto;
import chbucket.inventory.systemrepositoryservice.model.user.UserAccount;
import chbucket.inventory.systemrepositoryservice.model.user.UserSession;

public class UserSessionMapper {
    private UserSessionDto userSessionDto;
    private UserSession userSession;
    private UserAccount userAccount;

    private UserSessionMapper() {
    }

    public static UserSessionMapper of() {
        return new UserSessionMapper();
    }

    public UserSessionMapper setUserSessionDto(UserSessionDto userSessionDto){
        this.userSessionDto = userSessionDto;
        return this;
    }

    public UserSessionMapper setUserSession(UserSession userSession){
        this.userSession = userSession;
        return this;
    }

    public UserSessionMapper setUserAccount(UserAccount userAccount){
        this.userAccount = userAccount;
        return this;
    }

    public UserSession mapUserSessionDtotoUserSession(){
        if(userSession == null){
            userSession = new UserSession();
        }
        userSession.setUserAccount(userAccount);
        userSession.setToken(userSessionDto.getToken());

        return userSession;
    }

    public UserSessionDto mapUserSessiontoUserSessionDto(){
        if(userSessionDto == null){
            userSessionDto = new UserSessionDto();
            userSessionDto.setId(userSession.getId());
        }
        userSessionDto.setUserAccountId(userSession.getUserAccount().getId());
        userSessionDto.setToken(userSession.getToken());

        return userSessionDto;
    }

}
