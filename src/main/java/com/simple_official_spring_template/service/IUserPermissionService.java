package com.simple_official_spring_template.service;

import com.simple_official_spring_template.dto.UserPermissionDTO;
import com.simple_official_spring_template.entity.embeddedid.UserPermissionId;

import java.util.List;

public interface IUserPermissionService {
    UserPermissionDTO save(UserPermissionDTO userPermissionDTO);
    UserPermissionDTO update(UserPermissionDTO userPermissionDTO);
    UserPermissionDTO findOneByUserPermissionId(UserPermissionId userPermissionId);
    List<UserPermissionDTO> findAll();
    void deleteByUserPermissionIdIn(UserPermissionId[] userPermissionIds);
}
