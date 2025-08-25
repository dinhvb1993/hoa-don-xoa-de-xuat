package com.simple_official_spring_template.authentication.service.impl;

import com.simple_official_spring_template.authentication.service.IUserLoginService;
import com.simple_official_spring_template.authentication.utils.Hashing;
import com.simple_official_spring_template.authentication.utils.JwtUtil;
import com.simple_official_spring_template.converter.UserConverter;
import com.simple_official_spring_template.dto.UserDTO;
import com.simple_official_spring_template.entity.UserEntity;
import com.simple_official_spring_template.repository.IUserRepository;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import static com.simple_official_spring_template.authentication.utils.Constant.*;

@Service
public class UserLoginService implements IUserLoginService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDTO googleLogin(String token) throws GeneralSecurityException, IOException {
        NetHttpTransport transport = new NetHttpTransport();
        JacksonFactory jacksonFactory = new JacksonFactory();

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jacksonFactory)
                .setAudience(Collections.singletonList(GOOGLE_CLIENT_ID))
                .build();

        GoogleIdToken idToken = GoogleIdToken.parse(verifier.getJsonFactory(), token);
        boolean tokenIsValid = (idToken != null) && verifier.verify(idToken);

        if (tokenIsValid) {
            GoogleIdToken.Payload payload = idToken.getPayload();
            UserEntity userEntity = userRepository.findOneByEmail(payload.getEmail());
            if (userEntity != null){
                UserDTO userDTO = userConverter.toDTO(userEntity);
                userDTO.setToken(JwtUtil.generateToken(USER + "_" + userEntity.getId()));
                return userDTO;
            }
            else{
                UserDTO userDTO = new UserDTO();
                userDTO.setEmail(payload.getEmail());
                return userDTO;
            }
        }
        return null;
    }

    @Override
    public UserDTO facebookLogin(String token) {
        FacebookConnectionFactory factory = new FacebookConnectionFactory(FACEBOOK_APP_ID, FACEBOOK_APP_SECRET);

        OAuth2Operations operations = factory.getOAuthOperations();
        AccessGrant accessToken = new AccessGrant(token);

        Connection<Facebook> connection = factory.createConnection(accessToken);
        Facebook facebook = connection.getApi();
        String[] fields = { "id", "email", "first_name", "last_name" };
        User userProfile = facebook.fetchObject("me", User.class, fields);


        UserEntity userEntity = userRepository.findOneByFacebookAccount(userProfile.getId());
        if (userEntity != null){
            UserDTO userDTO = userConverter.toDTO(userEntity);
            userDTO.setToken(JwtUtil.generateToken(USER + "_" + userEntity.getId()));
            return userDTO;
        }
        else{
            UserDTO userDTO = new UserDTO();
//            userDTO.setFacebookAccount(userProfile.getId());
            return userDTO;
        }
    }

    @Override
    public UserDTO login(String id, String password) {
        UserEntity userEntity = userRepository.findOneByIdAndPassword(id, Hashing.hash(password));
        if (userEntity != null) {
            UserDTO userDTO = userConverter.toDTO(userEntity);
            userDTO.setToken(JwtUtil.generateToken(USER + "_" + id));
            return userDTO;
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
