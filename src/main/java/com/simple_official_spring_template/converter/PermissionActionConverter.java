package com.simple_official_spring_template.converter;

import com.simple_official_spring_template.dto.PermissionActionDTO;
import com.simple_official_spring_template.entity.PermissionActionEntity;
import com.simple_official_spring_template.entity.PermissionEntity;
import com.simple_official_spring_template.repository.IActionRepository;
import com.simple_official_spring_template.repository.IPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PermissionActionConverter {

    @Autowired
    private ActionConverter actionConverter;

    @Autowired
    private PermissionConverter permissionConverter;

    @Autowired
    private IPermissionRepository permissionRepository;

    @Autowired
    private IActionRepository actionRepository;

    public PermissionActionDTO toDTO(PermissionActionEntity permissionActionEntity){
        return new PermissionActionDTO(
                permissionActionEntity.getPermissionActionId(),
                permissionConverter.toDTO(permissionActionEntity.getPermission()),
                actionConverter.toDTO(permissionActionEntity.getAction()),
                permissionActionEntity.isLicensed()
        );
    }



    public PermissionActionEntity toEntity(PermissionActionDTO permissionActionDTO, PermissionActionEntity permissionActionEntity){
        if (permissionActionDTO.getPermissionActionId() != null){
            permissionActionEntity.setPermissionActionId(permissionActionDTO.getPermissionActionId());
        }

        if (permissionActionDTO.getPermissionDTO() != null){
            permissionActionEntity.setPermission(permissionRepository.findOneById(permissionActionDTO.getPermissionDTO().getId()));
        }
        if (permissionActionDTO.getActionDTO() != null){
            permissionActionEntity.setAction(actionRepository.findOneById(permissionActionDTO.getActionDTO().getId()));
        }
        permissionActionEntity.setLicensed(permissionActionDTO.isLicensed());

        return permissionActionEntity;
    }
}
