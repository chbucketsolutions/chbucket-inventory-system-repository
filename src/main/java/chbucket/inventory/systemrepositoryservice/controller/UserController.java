package chbucket.inventory.systemrepositoryservice.controller;

import chbucket.inventory.systemrepositoryservice.dto.request.AuthRequestDto;
import chbucket.inventory.systemrepositoryservice.dto.user.UserAccountDto;
import chbucket.inventory.systemrepositoryservice.dto.response.ResponseDto;
import chbucket.inventory.systemrepositoryservice.dto.user.UserSessionDto;
import chbucket.inventory.systemrepositoryservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("repository/user/v1")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/register")
    public ResponseDto<UserAccountDto> userAccountRegister(@RequestBody UserAccountDto userAccountDto){
        return new ResponseDto(userService.saveUserAccount(userAccountDto));
    }

    @GetMapping("/retrieve/user")
    public ResponseDto<UserAccountDto> userAccountRetrieveByUsername(@RequestParam(value = "username") String username){
        return new ResponseDto(userService.retrieveUserAccountByUserName(username));
    }

    @PostMapping("/login")
    public ResponseDto<UserAccountDto> userAccountLogin(@RequestBody AuthRequestDto authRequestDto){
        return new ResponseDto(userService.authUserAccount(authRequestDto));
    }

    @PostMapping("/save/session")
    public ResponseDto<UserSessionDto> userSessionSave(@RequestBody UserSessionDto userSessionDto){
        return new ResponseDto(userService.saveUserSession(userSessionDto));
    }

    @GetMapping("/retrieve/session")
    public ResponseDto<UserSessionDto> userSessionRetrieveByUserId(@RequestParam(value = "userId") String userId){
        return new ResponseDto(userService.retrieveUserSessionById(userId));
    }

}
