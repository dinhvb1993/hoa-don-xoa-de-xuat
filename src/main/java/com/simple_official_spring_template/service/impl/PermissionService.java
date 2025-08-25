package com.simple_official_spring_template.service.impl;

import com.simple_official_spring_template.converter.PermissionConverter;
import com.simple_official_spring_template.dto.PermissionDTO;
import com.simple_official_spring_template.entity.PermissionEntity;
import com.simple_official_spring_template.repository.IPermissionRepository;
import com.simple_official_spring_template.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PermissionService implements IPermissionService {

    @Autowired
    private IPermissionRepository permissionRepository;

    @Autowired
    private PermissionConverter permissionConverter;

    @Override
    public PermissionDTO save(PermissionDTO permissionDTO) {
        PermissionEntity permissionEntity = permissionConverter.toEntity(permissionDTO, new PermissionEntity());
        return permissionConverter.toDTO(permissionRepository.save(permissionEntity));
    }

    @Override
    public PermissionDTO update(PermissionDTO permissionDTO) {
        PermissionEntity permissionEntity = permissionRepository.findOneById(permissionDTO.getId());
        permissionEntity = permissionConverter.toEntity(permissionDTO, permissionEntity);
        return permissionConverter.toDTO(permissionRepository.save(permissionEntity));
    }

    @Override
    public PermissionDTO findOneById(Integer id) {
        return permissionConverter.toDTO(permissionRepository.findOneById(id));
    }

    @Override
    public PermissionDTO findOneByName(String name) {
        return permissionConverter.toDTO(permissionRepository.findOneByName(name));
    }

    @Override
    public List<PermissionDTO> findAll() {

        int page = 1;
        int limit = Integer.MAX_VALUE;
        Pageable pageable;
        Sort sort = Sort.by("id").ascending();
        pageable = PageRequest.of(page-1, limit, sort);

        List<PermissionDTO> permissionDTOS = new ArrayList<>();
        for (PermissionEntity permissionEntity : permissionRepository.findAll(pageable)){
            permissionDTOS.add(permissionConverter.toDTO(permissionEntity));
        }
        return permissionDTOS;
    }

    @Override
    public void deleteByIdIn(Integer[] ids) {
        permissionRepository.deleteByIdIn(ids);
    }

//    @Override
//    public void delete(PermissionDTO permissionDTO) {
//        permissionRepository.deleteAllById(permissionDTO.getIds());
//    }
}
