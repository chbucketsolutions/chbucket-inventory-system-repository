package chbucket.inventory.systemrepositoryservice.service.impl;

import chbucket.inventory.systemrepositoryservice.dto.request.AuthRequestDto;
import chbucket.inventory.systemrepositoryservice.dto.user.UserAccountDto;
import chbucket.inventory.systemrepositoryservice.dto.user.UserSessionDto;
import chbucket.inventory.systemrepositoryservice.mapper.UserAccountMapper;
import chbucket.inventory.systemrepositoryservice.mapper.UserSessionMapper;
import chbucket.inventory.systemrepositoryservice.model.user.UserAccount;
import chbucket.inventory.systemrepositoryservice.model.user.UserSession;
import chbucket.inventory.systemrepositoryservice.repository.UserAccountRepository;
import chbucket.inventory.systemrepositoryservice.repository.UserSessionRepository;
import chbucket.inventory.systemrepositoryservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    UserSessionRepository userSessionRepository;

    private Optional<UserAccount> isUserExist;
    private Optional<UserSession> isUserSessionExist;

    public UserAccountDto saveUserAccount(UserAccountDto userAccountDto) {
        UserAccount userAccount = userAccountRepository.save(UserAccountMapper.of()
                .setUserAccountDto(userAccountDto)
                .mapUserDtoToUserAccount());

        return UserAccountMapper.of()
                .setUserAccount(userAccount)
                .setUserAccountDto(null)
                .mapUserAccountToUserDto();
    }

    public UserAccountDto retrieveUserAccountByUserName(String username){
        isUserExist = userAccountRepository.findByUsername(username);

        if(isUserExist.isPresent()) {
            return UserAccountMapper.of()
                    .setUserAccount(isUserExist.orElse(null))
                    .mapUserAccountToUserDto();
        }
        return null;
    }


    public UserAccountDto authUserAccount(AuthRequestDto authRequestDto) {
        isUserExist = userAccountRepository.findByUsername(authRequestDto.getUsername());
        if(isUserExist.isPresent()) {
            return UserAccountMapper.of()
                    .setUserAccount(isUserExist.orElse(null))
                    .mapUserAccountToUserDto();
        }
        return null;
    }


    public UserSessionDto saveUserSession(UserSessionDto userSessionDto) {
        isUserExist = userAccountRepository.findById(userSessionDto.getUserAccountId()).stream().findFirst();

        if(isUserExist.isPresent()) {
            UserSession userSession = userSessionRepository.save(UserSessionMapper.of()
                    .setUserSessionDto(userSessionDto)
                    .setUserAccount(isUserExist.orElse(null))
                    .mapUserSessionDtotoUserSession());

            return UserSessionMapper.of()
                    .setUserSession(userSession)
                    .setUserSessionDto(null)
                    .mapUserSessiontoUserSessionDto();
        }
        return null;
    }


    public UserSessionDto retrieveUserSessionById(String id) {
        isUserExist = userAccountRepository.findById(id).stream().findFirst();
        isUserSessionExist = userSessionRepository.findByUserAccount(isUserExist.orElse(null));

        if(isUserExist.isPresent() && isUserSessionExist.isPresent()){
            return Optional.ofNullable(UserSessionMapper.of()
                    .setUserAccount(isUserExist.orElse(null))
                    .setUserSession(isUserSessionExist.orElse(null))
                    .mapUserSessiontoUserSessionDto()).orElse(null);
        }

        return null;
    }
}
