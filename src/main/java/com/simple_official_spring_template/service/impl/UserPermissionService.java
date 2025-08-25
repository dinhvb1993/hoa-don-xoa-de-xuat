package com.simple_official_spring_template.service.impl;

import com.simple_official_spring_template.converter.UserPermissionConverter;
import com.simple_official_spring_template.dto.UserPermissionDTO;
import com.simple_official_spring_template.entity.UserPermissionEntity;
import com.simple_official_spring_template.entity.embeddedid.UserPermissionId;
import com.simple_official_spring_template.repository.IUserPermissionRepository;
import com.simple_official_spring_template.service.IUserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;



@Service
@Transactional
public class UserPermissionService implements IUserPermissionService {

    @Autowired
    private IUserPermissionRepository userPermissionRepository;

    @Autowired
    private UserPermissionConverter userPermissionConverter;

    @Override
    public UserPermissionDTO save(UserPermissionDTO userPermissionDTO) {
        UserPermissionEntity userPermissionEntity = userPermissionConverter.toEntity(userPermissionDTO, new UserPermissionEntity());
        userPermissionEntity.setUserPermissionId(new UserPermissionId(userPermissionDTO.getUserDTO().getId(), userPermissionDTO.getPermissionDTO().getId()));

        return userPermissionConverter.toDTO(userPermissionRepository.save(userPermissionEntity));
    }

    @Override
    public UserPermissionDTO update(UserPermissionDTO userPermissionDTO) {

        String userId = userPermissionDTO.getUserDTO().getId();
        Integer permissionId = userPermissionDTO.getPermissionDTO().getId();


        UserPermissionEntity userPermissionEntity = userPermissionRepository.findOneByUserPermissionId(new UserPermissionId(userId, permissionId));

        userPermissionEntity.setLicensed(userPermissionDTO.isLicensed());

        userPermissionRepository.save(userPermissionEntity);


        userPermissionDTO.setUserPermissionId(userPermissionEntity.getUserPermissionId());
        return userPermissionDTO;
    }

    @Override
    public UserPermissionDTO findOneByUserPermissionId(UserPermissionId userPermissionId) {
        if (userPermissionRepository.findOneByUserPermissionId(userPermissionId) != null){
            return userPermissionConverter.toDTO(userPermissionRepository.findOneByUserPermissionId(userPermissionId));
        }
        else {
            return null;
        }


//        return userPermissionConverter.toDTO(userPermissionRepository.findOneByUserPermissionId(userPermissionId));
    }


    @Override
    public List<UserPermissionDTO> findAll() {
        List<UserPermissionDTO> userPermissionDTOS = new ArrayList<>();
        for (UserPermissionEntity userPermissionEntity : userPermissionRepository.findAll()){
            userPermissionDTOS.add(userPermissionConverter.toDTO(userPermissionEntity));
        }
        return userPermissionDTOS;
    }

    @Override
    public void deleteByUserPermissionIdIn(UserPermissionId[] userPermissionIds) {
        userPermissionRepository.deleteByUserPermissionIdIn(userPermissionIds);
    }

//    @Override
//    public void delete(UserPermissionDTO userPermissionDTO) {
//        userPermissionRepository.deleteAllById(userPermissionDTO.getIds());
//    }
}
