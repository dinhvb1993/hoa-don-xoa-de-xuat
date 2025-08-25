package com.simple_official_spring_template.service.impl;

import com.simple_official_spring_template.authentication.service.impl.UserLoginService;
import com.simple_official_spring_template.dto.UserDTO;
import com.simple_official_spring_template.entity.UserEntity;
import com.simple_official_spring_template.entity.UserPermissionEntity;
import com.simple_official_spring_template.service.ITotalUserPermissionService;
import com.simple_official_spring_template.utils.nativequery.GeneralNativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TotalUserPermissionService implements ITotalUserPermissionService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserLoginService userLoginService;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private GeneralNativeQuery generalNativeQuery;



    @Override
    public boolean requireMasterRole(String token) {
        UserDTO userDTO = userService.getAccount(userLoginService.parseToken(token.replace("Bearer ", "")));
        return (userDTO != null && userDTO.isMaster());
    }

    @Override
    public boolean requireActionName(String token, String actionName) {
        UserDTO userDTO = userService.getAccount(userLoginService.parseToken(token.replace("Bearer ", "")));
        List<UserEntity> userEntityList = generalNativeQuery.checkUserActionLicense(entityManager, userDTO.getId(), actionName);

        return userEntityList.size() > 0;
    }
}
