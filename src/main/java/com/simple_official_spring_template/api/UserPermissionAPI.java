package com.simple_official_spring_template.api;

import com.simple_official_spring_template.dto.UserPermissionDTO;
import com.simple_official_spring_template.entity.embeddedid.UserPermissionId;
import com.simple_official_spring_template.service.IUserPermissionService;
import com.simple_official_spring_template.service.ITotalUserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserPermissionAPI {
    @Autowired
    private IUserPermissionService userPermissionService;

    @Autowired
    private ITotalUserPermissionService totalUserPermissionService;

    @GetMapping(value = "/api-user-permission/{userId}/{permissionId}")
    public UserPermissionDTO getUserPermissionById(@PathVariable(value = "userId", required = true) String userId, @PathVariable(value = "permissionId", required = true) Integer permissionId){
        return userPermissionService.findOneByUserPermissionId(new UserPermissionId(userId, permissionId));
    }



    @GetMapping(value = "/api-user-permission")
    public List<UserPermissionDTO> getUserPermissions(){
        return userPermissionService.findAll();
    }


    @PostMapping(value = "/api-user-permission")
    public UserPermissionDTO createUserPermission(@RequestHeader("Authorization") String token, @RequestBody UserPermissionDTO userPermissionDTO){

        if (!totalUserPermissionService.requireMasterRole(token)){
            return null;
        }

        return userPermissionService.save(userPermissionDTO);
    }

    @PutMapping(value = "/api-user-permission")
    public UserPermissionDTO updateUserPermission(@RequestHeader("Authorization") String token, @RequestBody UserPermissionDTO userPermissionDTO){
        if (!totalUserPermissionService.requireMasterRole(token)){
            return null;
        }
        return userPermissionService.update(userPermissionDTO);
    }

    @DeleteMapping(value = "/api-user-permission")
    public void deleteUserPermissions(@RequestHeader("Authorization") String token, @RequestBody UserPermissionDTO userPermissionDTO){
        if (!totalUserPermissionService.requireMasterRole(token)){
            return;
        }
        userPermissionService.deleteByUserPermissionIdIn( userPermissionDTO.getUserPermissionIds());
    }
}
