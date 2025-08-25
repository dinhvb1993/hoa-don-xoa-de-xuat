package com.simple_official_spring_template.api;

import com.simple_official_spring_template.dto.ActionDTO;
import com.simple_official_spring_template.service.IActionService;
import com.simple_official_spring_template.service.ITotalUserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ActionAPI {
    @Autowired
    private IActionService actionService;

    @Autowired
    private ITotalUserPermissionService totalUserPermissionService;

    @GetMapping(value = "/api-action/{id}")
    public ActionDTO getActionById(@PathVariable(value = "id", required = true) Integer id){
        return actionService.findOneById(id);
    }

    @GetMapping(value = "/api-get-action-by-name/{name}")
    public ActionDTO getActionByName(@PathVariable(value = "name", required = true) String name){
        return actionService.findOneByName(name);
    }

    @GetMapping(value = "/api-action")
    public List<ActionDTO> getActions(){
        return actionService.findAll();
    }


    @PostMapping(value = "/api-action")
    public ActionDTO createAction(@RequestHeader("Authorization") String token, @RequestBody ActionDTO actionDTO){

        if (!totalUserPermissionService.requireMasterRole(token)){
            return null;
        }

        return actionService.save(actionDTO);
    }

    @PutMapping(value = "/api-action")
    public ActionDTO updateAction(@RequestHeader("Authorization") String token, @RequestBody ActionDTO actionDTO){
        if (!totalUserPermissionService.requireMasterRole(token)){
            return null;
        }
        return actionService.update(actionDTO);
    }

    @DeleteMapping(value = "/api-action")
    public void deleteActions(@RequestHeader("Authorization") String token, @RequestBody ActionDTO actionDTO){
        if (!totalUserPermissionService.requireMasterRole(token)){
            return;
        }
        actionService.deleteByIdIn(actionDTO.getIds());
    }
}
