package chbucket.inventory.systemrepositoryservice.mapper;

import chbucket.inventory.systemrepositoryservice.dto.user.UserAccountProductDto;
import chbucket.inventory.systemrepositoryservice.model.user.UserAccount;

public class UserAccountProductMapper {
    private UserAccountProductDto userAccountProductDto;
    private UserAccount userAccount;

    private UserAccountProductMapper(){}

    public static UserAccountProductMapper of(){
        return new UserAccountProductMapper();
    }

    public UserAccountProductMapper setUserAccountProductDto(UserAccountProductDto userAccountProductDto) {
        this.userAccountProductDto = userAccountProductDto;
        return this;
    }

    public UserAccountProductMapper setUserAccountProduct(UserAccount userAccount) {
        this.userAccount = userAccount;
        return this;
    }

    public UserAccount mapUserDtoToUserAccountProduct(){
        if(userAccount == null){
            userAccount = new UserAccount();
        }
        userAccount.setUsername(userAccountProductDto.getUsername());
        userAccount.setFullName(userAccountProductDto.getFullName());
        return userAccount;
    }

    public UserAccountProductDto mapUserAccountToUserDto(){
        if(userAccountProductDto == null){
            userAccountProductDto = new UserAccountProductDto();
            userAccountProductDto.setId(userAccount.getId());
        }

        userAccountProductDto.setUsername(userAccount.getUsername());
        userAccountProductDto.setFullName(userAccount.getFullName());

        return userAccountProductDto;
    }
}
