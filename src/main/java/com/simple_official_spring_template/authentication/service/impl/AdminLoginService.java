package com.simple_official_spring_template.authentication.service.impl;

import com.simple_official_spring_template.authentication.service.IAdminLoginService;
import com.simple_official_spring_template.authentication.utils.Hashing;
import com.simple_official_spring_template.authentication.utils.JwtUtil;
import com.simple_official_spring_template.converter.AdminConverter;
import com.simple_official_spring_template.dto.AdminDTO;
import com.simple_official_spring_template.entity.AdminEntity;
import com.simple_official_spring_template.repository.IAdminRepository;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import static com.simple_official_spring_template.authentication.utils.Constant.*;

@Service
public class AdminLoginService implements IAdminLoginService {
    @Autowired
    private IAdminRepository adminRepository;

    @Autowired
    private AdminConverter adminConverter;

    @Override
    public AdminDTO googleLogin(String token) throws GeneralSecurityException, IOException {
        NetHttpTransport transport = new NetHttpTransport();
        JacksonFactory jacksonFactory = new JacksonFactory();

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jacksonFactory)
                .setAudience(Collections.singletonList(GOOGLE_CLIENT_ID))
                .build();
        GoogleIdToken idToken = GoogleIdToken.parse(verifier.getJsonFactory(), token);
        boolean tokenIsValid = (idToken != null) && verifier.verify(idToken);

        if (tokenIsValid) {
            GoogleIdToken.Payload payload = idToken.getPayload();
            AdminEntity adminEntity = adminRepository.findByEmail(payload.getEmail());
            if (adminEntity != null){
                AdminDTO adminDTO = adminConverter.toDTO(adminEntity);
                adminDTO.setToken(JwtUtil.generateToken(ADMIN + "_" + adminEntity.getId()));
                return adminDTO;
            }
            else{
                AdminDTO adminDTO = new AdminDTO();
                adminDTO.setEmail(payload.getEmail());
                return adminDTO;
            }
        }
        return null;
    }

    @Override
    public AdminDTO login(String id, String password) {
        AdminEntity adminEntity = adminRepository.findByIdAndPassword(id, Hashing.hash(password));
        if (adminEntity != null) {
            AdminDTO adminDTO = adminConverter.toDTO(adminEntity);
            adminDTO.setToken(JwtUtil.generateToken(ADMIN + "_" + id));
            return adminDTO;
        }
        return null;
    }

    @Override
    public String parseToken(String token) {
        return JwtUtil.parseToken(token);
    }

    @Override
    public void invalidateRelatedTokens(String token) {
        JwtUtil.invalidateRelatedTokens(token);
    }
}
