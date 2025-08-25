package com.simple_official_spring_template.service;

import com.simple_official_spring_template.dto.AdminDTO;

import java.util.List;

public interface IAdminService {
    AdminDTO save(AdminDTO adminDTO);
    AdminDTO update(AdminDTO adminDTO);
    AdminDTO findOneById(String id);
    List<AdminDTO> findAll();
    void delete(AdminDTO adminDTO);
    AdminDTO getAccount(String roleUsername);
    AdminDTO findOneByEmail(String email);
}
