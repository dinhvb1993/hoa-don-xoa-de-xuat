package com.simple_official_spring_template.service;

import com.simple_official_spring_template.dto.PermissionDTO;

import java.util.List;

public interface IPermissionService {
    PermissionDTO save(PermissionDTO permissionDTO);
    PermissionDTO update(PermissionDTO permissionDTO);
    PermissionDTO findOneById(Integer id);
    PermissionDTO findOneByName(String name);
    List<PermissionDTO> findAll();
    void deleteByIdIn(Integer[] ids);
}
