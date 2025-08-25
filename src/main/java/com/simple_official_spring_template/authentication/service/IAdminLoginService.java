package com.simple_official_spring_template.authentication.service;

import com.simple_official_spring_template.dto.AdminDTO;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface IAdminLoginService {
    AdminDTO googleLogin(String token) throws GeneralSecurityException, IOException;
    AdminDTO login(String id, String password);
    String parseToken(String token);
    void invalidateRelatedTokens(String token);
}
