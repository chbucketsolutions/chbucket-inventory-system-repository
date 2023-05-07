package chbucket.inventory.systemrepositoryservice.mapper;

import chbucket.inventory.systemrepositoryservice.dto.request.AuthRequestDto;
import chbucket.inventory.systemrepositoryservice.dto.user.UserAccountDto;
import chbucket.inventory.systemrepositoryservice.model.user.UserAccount;

public class UserAccountMapper {
    private UserAccountDto userAccountDto;
    private UserAccount userAccount;

    private UserAccountMapper(){}

    public static UserAccountMapper of(){
        return new UserAccountMapper();
    }

    public UserAccountMapper setUserAccountDto(UserAccountDto userAccountDto) {
        this.userAccountDto = userAccountDto;
        return this;
    }

    public UserAccountMapper setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
        return this;
    }

    public UserAccount mapUserDtoToUserAccount(){
        if(userAccount == null){
            userAccount = new UserAccount();
        }
        userAccount.setUsername(userAccountDto.getUsername());
        userAccount.setPassword(userAccountDto.getPassword());
        userAccount.setFullName(userAccountDto.getFullName());
        userAccount.setAddress(userAccountDto.getAddress());
        userAccount.setEmail(userAccountDto.getEmail());
        userAccount.setContactNumber(userAccountDto.getContactNumber());
        return userAccount;
    }

    public UserAccountDto mapUserAccountToUserDto(){
        if(userAccountDto == null){
            userAccountDto = new UserAccountDto();
            userAccountDto.setId(userAccount.getId());
        }

        userAccountDto.setUsername(userAccount.getUsername());
        userAccountDto.setPassword(userAccount.getPassword());
        userAccountDto.setEmail(userAccount.getEmail());
        userAccountDto.setContactNumber(userAccount.getContactNumber());
        userAccountDto.setAddress(userAccount.getAddress());
        userAccountDto.setFullName(userAccount.getFullName());
        return userAccountDto;
    }

}
