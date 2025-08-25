package com.simple_official_spring_template.authentication.api;

import com.simple_official_spring_template.authentication.service.IAdminLoginService;
import com.simple_official_spring_template.dto.AdminDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
public class AdminLoginAPI {
    @Autowired
    private IAdminLoginService adminLoginService;

    @PostMapping(value = "/api-admin-google-login")
    public AdminDTO loginGoogle(@RequestBody AdminDTO adminDTO) throws GeneralSecurityException, IOException {
        return adminLoginService.googleLogin(adminDTO.getToken());
    }

    @PostMapping(value = "/api-admin-login")
    public AdminDTO login(@RequestBody AdminDTO adminDTO) {
        return adminLoginService.login(adminDTO.getId(), adminDTO.getPassword());
    }

    @GetMapping(value = "/api-admin-logout")
    public void logout(@RequestHeader("Authorization") String token) {
        adminLoginService.invalidateRelatedTokens(token.replace("Bearer ", ""));
    }
}
