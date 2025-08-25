package com.simple_official_spring_template.service.impl;

import com.simple_official_spring_template.converter.ActionConverter;
import com.simple_official_spring_template.converter.PermissionActionConverter;
import com.simple_official_spring_template.converter.UserConverter;
import com.simple_official_spring_template.converter.UserPermissionConverter;
import com.simple_official_spring_template.dto.*;
import com.simple_official_spring_template.entity.*;
import com.simple_official_spring_template.entity.embeddedid.PermissionActionId;
import com.simple_official_spring_template.entity.embeddedid.UserPermissionId;
import com.simple_official_spring_template.repository.*;
import com.simple_official_spring_template.service.IGeneralService;
import com.simple_official_spring_template.service.IUserService;
import com.simple_official_spring_template.utils.nativequery.GeneralNativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class GeneralService implements IGeneralService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IActionRepository actionRepository;

    @Autowired
    private IUserPermissionRepository userPermissionRepository;

    @Autowired
    private IPermissionActionRepository permissionActionRepository;

    @Autowired
    private IPermissionRepository permissionRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private ActionConverter actionConverter;

    @Autowired
    private UserPermissionConverter userPermissionConverter;

    @Autowired
    private PermissionActionConverter permissionActionConverter;

    @Autowired
    private GeneralNativeQuery generalNativeQuery;

    @Override
    public List<UserGroupDTO> findAllUserGroupDTO() {
        List<UserGroupDTO> userGroupDTOList = new ArrayList<>();

        int page = 1;
        int limit = Integer.MAX_VALUE;
        Pageable pageable;
        Sort sort = Sort.by("id").ascending();

        pageable = PageRequest.of(page-1, limit, sort);


        for (UserEntity userEntity: userRepository.findAll(pageable)){
            UserGroupDTO userGroupDTO = new UserGroupDTO();
            userGroupDTO.setUser(userConverter.toDTO(userEntity));

            List<UserPermissionDTO> userPermissionDTOList = new ArrayList<>();

            for (PermissionEntity permissionEntity: permissionRepository.findAll(pageable)){
                UserPermissionId userPermissionId = new UserPermissionId(userEntity.getId(), permissionEntity.getId());
                UserPermissionEntity userPermissionEntity = userPermissionRepository.findOneByUserPermissionId(userPermissionId);
                if (userPermissionEntity == null){

                    userPermissionEntity = new UserPermissionEntity();
                    userPermissionEntity.setUser(userEntity);
                    userPermissionEntity.setPermission(permissionEntity);
                    userPermissionEntity.setUserPermissionId(userPermissionId);
                    userPermissionEntity.setLicensed(false);

                    userPermissionEntity = userPermissionRepository.save(userPermissionEntity);
                }
                userPermissionDTOList.add(userPermissionConverter.toDTO(userPermissionEntity));
            }

            userGroupDTO.setUserPermissions(userPermissionDTOList);

            userGroupDTOList.add(userGroupDTO);
        }


        return userGroupDTOList;
    }

    @Override
    public List<ActionGroupDTO> findAllActionGroupDTO() {
        List<ActionGroupDTO> actionGroupDTOList = new ArrayList<>();

        int page = 1;
        int limit = Integer.MAX_VALUE;
        Pageable pageable;
        Sort sort = Sort.by("id").ascending();

        pageable = PageRequest.of(page-1, limit, sort);


        for (ActionEntity actionEntity: actionRepository.findAll(pageable)){
            ActionGroupDTO actionGroupDTO = new ActionGroupDTO();
            actionGroupDTO.setAction(actionConverter.toDTO(actionEntity));

            List<PermissionActionDTO> permissionActionDTOList = new ArrayList<>();

            for (PermissionEntity permissionEntity: permissionRepository.findAll(pageable)){
                PermissionActionId permissionActionId = new PermissionActionId(permissionEntity.getId(), actionEntity.getId());
                PermissionActionEntity permissionActionEntity = permissionActionRepository.findOneByPermissionActionId(permissionActionId);
                if (permissionActionEntity == null){

                    permissionActionEntity = new PermissionActionEntity();
                    permissionActionEntity.setAction(actionEntity);
                    permissionActionEntity.setPermission(permissionEntity);
                    permissionActionEntity.setPermissionActionId(permissionActionId);
                    permissionActionEntity.setLicensed(false);

                    permissionActionEntity = permissionActionRepository.save(permissionActionEntity);
                }
                permissionActionDTOList.add(permissionActionConverter.toDTO(permissionActionEntity));
            }

            actionGroupDTO.setPermissionActions(permissionActionDTOList);

            actionGroupDTOList.add(actionGroupDTO);
        }


        return actionGroupDTOList;
    }

    @Override
    public void deleteImageSliderItem(String tableName, String columnCatIdName, Integer cateId, Integer imageSliderItemId) {
        generalNativeQuery.deleteImageSliderItem(this.entityManager, tableName, columnCatIdName, cateId, imageSliderItemId);
    }
}
