package com.simple_official_spring_template.converter;

import com.simple_official_spring_template.dto.UserPermissionDTO;
import com.simple_official_spring_template.entity.UserPermissionEntity;
import com.simple_official_spring_template.repository.IPermissionRepository;
import com.simple_official_spring_template.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserPermissionConverter {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private PermissionConverter permissionConverter;
    
    @Autowired
    private IPermissionRepository permissionRepository;
    
    @Autowired
    private IUserRepository userRepository;

    public UserPermissionDTO toDTO(UserPermissionEntity userPermissionEntity){

        return new UserPermissionDTO(
                userPermissionEntity.getUserPermissionId(),
                userConverter.toDTO(userPermissionEntity.getUser()),
                permissionConverter.toDTO(userPermissionEntity.getPermission()),
                userPermissionEntity.isLicensed()
        );
    }



    public UserPermissionEntity toEntity(UserPermissionDTO userPermissionDTO, UserPermissionEntity userPermissionEntity){
        if (userPermissionDTO.getUserPermissionId() != null){
            userPermissionEntity.setUserPermissionId(userPermissionDTO.getUserPermissionId());
        }

        if (userPermissionDTO.getPermissionDTO() != null){
            userPermissionEntity.setPermission(permissionRepository.findOneById(userPermissionDTO.getPermissionDTO().getId()));
        }
        if (userPermissionDTO.getUserDTO() != null){
            userPermissionEntity.setUser(userRepository.findOneById(userPermissionDTO.getUserDTO().getId()));
        }
        userPermissionEntity.setLicensed(userPermissionDTO.isLicensed());
        
        return userPermissionEntity;
    }
}
