package com.simple_official_spring_template.converter;

import com.simple_official_spring_template.authentication.utils.Hashing;
import com.simple_official_spring_template.dto.AdminDTO;
import com.simple_official_spring_template.entity.AdminEntity;
import org.springframework.stereotype.Component;

@Component
public class AdminConverter {
    public AdminDTO toDTO(AdminEntity adminEntity){
        return new AdminDTO(adminEntity.getId(), adminEntity.getPassword(), adminEntity.getFullName(), adminEntity.getPhoneNumber(), adminEntity.getEmail(), adminEntity.getIsActive(), adminEntity.getActiveCode(), adminEntity.getFacebookAccount(), adminEntity.getType());
    }

    public AdminEntity toEntity(AdminDTO adminDTO, AdminEntity adminEntity){
        if (adminDTO.getId() != null){
            adminEntity.setId(adminDTO.getId());
        }
        if (adminDTO.getPassword() != null){
            adminEntity.setPassword(Hashing.hash(adminDTO.getPassword()));
        }
        if (adminDTO.getFullName() != null){
            adminEntity.setFullName(adminDTO.getFullName());
        }
        if (adminDTO.getPhoneNumber() != null){
            adminEntity.setPhoneNumber(adminDTO.getPhoneNumber());
        }
        if (adminDTO.getEmail() != null){
            adminEntity.setEmail(adminDTO.getEmail());
        }
//        if (adminDTO.getAvatarUrl() != null){
//            adminEntity.setAvatarUrl(adminDTO.getAvatarUrl());
//        }
        if (adminDTO.getIsActive() != null){
            adminEntity.setIsActive(adminDTO.getIsActive());
        }
        if (adminDTO.getActiveCode() != null) {
            adminEntity.setActiveCode(adminDTO.getActiveCode());
        }
        if (adminDTO.getFacebookAccount() != null) {
            adminEntity.setFacebookAccount(adminDTO.getFacebookAccount());
        }
        if (adminDTO.getType() != null){
            adminEntity.setType(adminDTO.getId());
        }

        return adminEntity;
    }
}
