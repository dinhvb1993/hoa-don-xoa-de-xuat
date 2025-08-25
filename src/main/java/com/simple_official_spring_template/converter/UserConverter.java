package com.simple_official_spring_template.converter;

import com.simple_official_spring_template.authentication.utils.Hashing;
import com.simple_official_spring_template.dto.UserDTO;
import com.simple_official_spring_template.entity.UserEntity;

import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserDTO toDTO(UserEntity userEntity){
        return new UserDTO(
                userEntity.getId(),
                userEntity.getPassword(),
                userEntity.getFullName(),
                userEntity.getPhoneNumber(),
                userEntity.getEmail(),
                userEntity.getIsActive(),
                userEntity.getActiveCode(),
                userEntity.getIsFemale(),
                userEntity.getBirthDate(),
                userEntity.isEmailVerifier(),
                userEntity.isMaster(),
                userEntity.getFacebookAccount()
        );
    }

    public UserEntity toEntity(UserDTO userDTO, UserEntity userEntity){
        if (userDTO.getId() != null){
            userEntity.setId(userDTO.getId());
        }
        if (userDTO.getPassword() != null){
            userEntity.setPassword(userDTO.getPassword());
        }
        if (userDTO.getFullName() != null){
            userEntity.setFullName(userDTO.getFullName());
        }
        if (userDTO.getPhoneNumber() != null){
            userEntity.setPhoneNumber(userDTO.getPhoneNumber());
        }
        if (userDTO.getEmail() != null){
            userEntity.setEmail(userDTO.getEmail());
        }
//        if (userDTO.getAvatarUrl() != null){
//            userEntity.setAvatarUrl(userDTO.getAvatarUrl());
//        }
        if (userDTO.getIsActive() != null){
            userEntity.setIsActive(userDTO.getIsActive());
        }
        if (userDTO.getActiveCode() != null){
            userEntity.setActiveCode(userDTO.getActiveCode());
        }

        if (userDTO.getIsFemale() != null){
            userEntity.setIsFemale(userDTO.getIsFemale());
        }
        if (userDTO.getBirthDate() != null){
            userEntity.setBirthDate(userDTO.getBirthDate());
        }

        if (userDTO.getFacebookAccount() != null) {
            userEntity.setFacebookAccount(userDTO.getFacebookAccount());
        }

        userEntity.setEmailVerifier(userDTO.isEmailVerifier());

        userEntity.setMaster(userDTO.isMaster());

        return userEntity;
    }
}
