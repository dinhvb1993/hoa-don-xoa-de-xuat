package com.simple_official_spring_template.service;

import com.simple_official_spring_template.dto.PermissionActionDTO;
import com.simple_official_spring_template.entity.embeddedid.PermissionActionId;

import java.util.List;

public interface IPermissionActionService {
    PermissionActionDTO save(PermissionActionDTO permissionActionDTO);
    PermissionActionDTO update(PermissionActionDTO permissionActionDTO);
    PermissionActionDTO findOneByPermissionActionId(PermissionActionId permissionActionId);
    List<PermissionActionDTO> findAll();
    void deleteByPermissionActionIdIn(PermissionActionId[] permissionActionIds);
}
