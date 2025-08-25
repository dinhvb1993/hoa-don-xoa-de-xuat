package com.simple_official_spring_template.service.impl;

import com.simple_official_spring_template.converter.PermissionActionConverter;
import com.simple_official_spring_template.dto.PermissionActionDTO;
import com.simple_official_spring_template.entity.PermissionActionEntity;
import com.simple_official_spring_template.entity.embeddedid.PermissionActionId;
import com.simple_official_spring_template.repository.IPermissionActionRepository;
import com.simple_official_spring_template.service.IPermissionActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PermissionActionService implements IPermissionActionService {

    @Autowired
    private IPermissionActionRepository permissionActionRepository;

    @Autowired
    private PermissionActionConverter permissionActionConverter;

    @Override
    public PermissionActionDTO save(PermissionActionDTO permissionActionDTO) {
        PermissionActionEntity permissionActionEntity = permissionActionConverter.toEntity(permissionActionDTO, new PermissionActionEntity());

        permissionActionEntity.setPermissionActionId(new PermissionActionId(
                permissionActionDTO.getPermissionDTO().getId(),
                permissionActionDTO.getActionDTO().getId()
        ));

        permissionActionRepository.save(permissionActionEntity);


        permissionActionDTO.setPermissionActionId(permissionActionEntity.getPermissionActionId());
        return permissionActionDTO;
    }

    @Override
    public PermissionActionDTO update(PermissionActionDTO permissionActionDTO) {
        PermissionActionId permissionActionId = new PermissionActionId(
                permissionActionDTO.getPermissionDTO().getId(),
                permissionActionDTO.getActionDTO().getId()
        );

        PermissionActionEntity permissionActionEntity = permissionActionRepository.findOneByPermissionActionId(permissionActionId);

        permissionActionEntity.setLicensed(permissionActionDTO.isLicensed());
        permissionActionRepository.save(permissionActionEntity);

        permissionActionDTO.setPermissionActionId(permissionActionEntity.getPermissionActionId());
        return permissionActionDTO;

    }

    @Override
    public PermissionActionDTO findOneByPermissionActionId(PermissionActionId permissionActionId) {
        return permissionActionConverter.toDTO(permissionActionRepository.findOneByPermissionActionId(permissionActionId));
    }


    @Override
    public List<PermissionActionDTO> findAll() {
        List<PermissionActionDTO> permissionActionDTOS = new ArrayList<>();
        for (PermissionActionEntity permissionActionEntity : permissionActionRepository.findAll()){
            permissionActionDTOS.add(permissionActionConverter.toDTO(permissionActionEntity));
        }
        return permissionActionDTOS;
    }

    @Override
    public void deleteByPermissionActionIdIn(PermissionActionId[] permissionActionIds) {
        permissionActionRepository.deleteByPermissionActionIdIn(permissionActionIds);
    }

//    @Override
//    public void delete(PermissionActionDTO permissionActionDTO) {
//        permissionActionRepository.deleteAllById(permissionActionDTO.getIds());
//    }
}
