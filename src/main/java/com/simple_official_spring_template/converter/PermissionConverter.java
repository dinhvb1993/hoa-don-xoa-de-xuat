package com.simple_official_spring_template.converter;

import com.simple_official_spring_template.dto.PermissionDTO;
import com.simple_official_spring_template.entity.PermissionEntity;
import org.springframework.stereotype.Component;


@Component
public class PermissionConverter {

    public PermissionDTO toDTO(PermissionEntity permissionEntity){
        return new PermissionDTO(
                permissionEntity.getId(), 
                permissionEntity.getName()
        );
    }

    public PermissionEntity toEntity(PermissionDTO permissionDTO, PermissionEntity permissionEntity){
        if (permissionDTO.getId() != null){
            permissionEntity.setId(permissionDTO.getId());
        }
        if (permissionDTO.getName() != null){
            permissionEntity.setName(permissionDTO.getName());
        }

        return permissionEntity;
    }
}
