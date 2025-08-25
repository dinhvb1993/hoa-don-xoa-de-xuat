package com.simple_official_spring_template.api;

import com.simple_official_spring_template.dto.PermissionDTO;
import com.simple_official_spring_template.service.IPermissionService;
import com.simple_official_spring_template.service.ITotalUserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PermissionAPI {
    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private ITotalUserPermissionService totalUserPermissionService;

    @GetMapping(value = "/api-permission/{id}")
    public PermissionDTO getPermissionById(@PathVariable(value = "id", required = true) Integer id){
        return permissionService.findOneById(id);
    }

    @GetMapping(value = "/api-get-permission-by-name/{name}")
    public PermissionDTO getPermissionByName(@PathVariable(value = "name", required = true) String name){
        return permissionService.findOneByName(name);
    }

    @GetMapping(value = "/api-permission")
    public List<PermissionDTO> getPermissions(){
        return permissionService.findAll();
    }


    @PostMapping(value = "/api-permission")
    public PermissionDTO createPermission(@RequestHeader("Authorization") String token, @RequestBody PermissionDTO permissionDTO){

        if (!totalUserPermissionService.requireMasterRole(token)){
            return null;
        }

        return permissionService.save(permissionDTO);
    }

    @PutMapping(value = "/api-permission")
    public PermissionDTO updatePermission(@RequestHeader("Authorization") String token, @RequestBody PermissionDTO permissionDTO){
        if (!totalUserPermissionService.requireMasterRole(token)){
            return null;
        }
        return permissionService.update(permissionDTO);
    }

    @DeleteMapping(value = "/api-permission")
    public void deletePermissions(@RequestHeader("Authorization") String token, @RequestBody PermissionDTO permissionDTO){
        if (!totalUserPermissionService.requireMasterRole(token)){
            return;
        }
        permissionService.deleteByIdIn(permissionDTO.getIds());
    }
}
