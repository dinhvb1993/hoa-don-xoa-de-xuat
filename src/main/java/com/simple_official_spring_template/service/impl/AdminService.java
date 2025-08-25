package com.simple_official_spring_template.service.impl;

import com.simple_official_spring_template.converter.AdminConverter;
import com.simple_official_spring_template.dto.AdminDTO;
import com.simple_official_spring_template.entity.AdminEntity;
import com.simple_official_spring_template.repository.IAdminRepository;
import com.simple_official_spring_template.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService implements IAdminService {

    @Autowired
    private IAdminRepository adminRepository;

    @Autowired
    private AdminConverter adminConverter;

    @Override
    public AdminDTO save(AdminDTO adminDTO) {
        AdminEntity adminEntity = adminConverter.toEntity(adminDTO, new AdminEntity());
        return adminConverter.toDTO(adminRepository.save(adminEntity));
    }

    @Override
    public AdminDTO update(AdminDTO adminDTO) {
        AdminEntity adminEntity = adminRepository.findOneById(adminDTO.getId());
        adminEntity = adminConverter.toEntity(adminDTO, adminEntity);
        return adminConverter.toDTO(adminRepository.save(adminEntity));
    }

    @Override
    public AdminDTO findOneById(String id) {
        return adminConverter.toDTO(adminRepository.findOneById(id));
    }

    @Override
    public List<AdminDTO> findAll() {
        List<AdminDTO> adminDTOS = new ArrayList<>();
        for (AdminEntity adminEntity: adminRepository.findAll()){
            adminDTOS.add(adminConverter.toDTO(adminEntity));
        }
        return adminDTOS;
    }

    @Override
    public void delete(AdminDTO adminDTO) {
        adminRepository.deleteByIds(adminDTO.getIds());
    }

    @Override
    public AdminDTO getAccount(String roleUsername) {
        if (adminRepository.findOneById(roleUsername.split("_")[1]) != null){
            return adminConverter.toDTO(adminRepository.findOneById(roleUsername.split("_")[1]));
        }
        return null;
    }

    @Override
    public AdminDTO findOneByEmail(String email) {
        return adminConverter.toDTO(adminRepository.findByEmail(email));
    }
}
