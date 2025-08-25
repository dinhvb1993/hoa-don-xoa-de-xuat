package com.simple_official_spring_template.api;

import com.simple_official_spring_template.dto.PermissionActionDTO;
import com.simple_official_spring_template.entity.embeddedid.PermissionActionId;
import com.simple_official_spring_template.service.IPermissionActionService;
import com.simple_official_spring_template.service.ITotalUserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PermissionActionAPI {
    @Autowired
    private IPermissionActionService permissionActionService;

    @Autowired
    private ITotalUserPermissionService totalUserPermissionService;

    @GetMapping(value = "/api-permission-action/{permissionId}/{actionId}")
    public PermissionActionDTO getPermissionActionById(@PathVariable(value = "permissionId", required = true) Integer permissionId, @PathVariable(value = "actionId", required = true) Integer actionId){
        return permissionActionService.findOneByPermissionActionId(new PermissionActionId(permissionId, actionId));
    }



    @GetMapping(value = "/api-permission-action")
    public List<PermissionActionDTO> getPermissionActions(){
        return permissionActionService.findAll();
    }


    @PostMapping(value = "/api-permission-action")
    public PermissionActionDTO createPermissionAction(@RequestHeader("Authorization") String token, @RequestBody PermissionActionDTO permissionActionDTO){

        if (!totalUserPermissionService.requireMasterRole(token)){
            return null;
        }

        return permissionActionService.save(permissionActionDTO);
    }

    @PutMapping(value = "/api-permission-action")
    public PermissionActionDTO updatePermissionAction(@RequestHeader("Authorization") String token, @RequestBody PermissionActionDTO permissionActionDTO){
        if (!totalUserPermissionService.requireMasterRole(token)){
            return null;
        }
        return permissionActionService.update(permissionActionDTO);
    }

    @DeleteMapping(value = "/api-permission-action")
    public void deletePermissionActions(@RequestHeader("Authorization") String token, @RequestBody PermissionActionDTO permissionActionDTO){
        if (!totalUserPermissionService.requireMasterRole(token)){
            return;
        }
        permissionActionService.deleteByPermissionActionIdIn( permissionActionDTO.getPermissionActionIds());
    }
}
