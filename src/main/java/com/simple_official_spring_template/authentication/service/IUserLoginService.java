package com.simple_official_spring_template.authentication.service;

import com.simple_official_spring_template.dto.UserDTO;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface IUserLoginService {
    UserDTO googleLogin(String token) throws GeneralSecurityException, IOException;
    UserDTO facebookLogin(String token);
    UserDTO login(String id, String password);
    String parseToken(String token);
    void invalidateRelatedTokens(String token);
}
