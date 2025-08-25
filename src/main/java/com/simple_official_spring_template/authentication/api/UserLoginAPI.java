package com.simple_official_spring_template.authentication.api;

import com.simple_official_spring_template.authentication.service.IUserLoginService;
import com.simple_official_spring_template.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
public class UserLoginAPI {
    @Autowired
    private IUserLoginService userLoginService;

    @PostMapping(value = "/api-user-google-login")
    public UserDTO loginGoogle(@RequestBody UserDTO userDTO) throws GeneralSecurityException, IOException {
        return userLoginService.googleLogin(userDTO.getToken());
    }

    @PostMapping(value = "/api-user-facebook-login")
    public UserDTO loginFacebook(@RequestBody UserDTO userDTO){
        return userLoginService.facebookLogin(userDTO.getToken());
    }

    @PostMapping(value = "/api-user-login")
    public UserDTO login(@RequestBody UserDTO userDTO) {
        return userLoginService.login(userDTO.getId(), userDTO.getPassword());
    }

    @GetMapping(value = "/api-user-logout")
    public void logout(@RequestHeader("Authorization") String token) {
        userLoginService.invalidateRelatedTokens(token.replace("Bearer ", ""));
    }
}
