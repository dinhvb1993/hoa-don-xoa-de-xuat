package com.simple_official_spring_template.api;

import com.simple_official_spring_template.dto.ActionGroupDTO;
import com.simple_official_spring_template.dto.UserGroupDTO;
import com.simple_official_spring_template.service.IGeneralService;
import com.simple_official_spring_template.service.ITotalUserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GeneralAPI {
    @Autowired
    private IGeneralService generalService;


    @Autowired
    private ITotalUserPermissionService totalUserPermissionService;

    @GetMapping(value = "/api-general/user-group")
    public List<UserGroupDTO> findAllUserGroupDTO(@RequestHeader("Authorization") String token){

        if (!totalUserPermissionService.requireMasterRole(token)){
            return null;
        }

        return generalService.findAllUserGroupDTO();
    }


    @GetMapping(value = "/api-general/action-group")
    public List<ActionGroupDTO> findAllActionGroupDTO(@RequestHeader("Authorization") String token){

        if (!totalUserPermissionService.requireMasterRole(token)){
            return null;
        }

        return generalService.findAllActionGroupDTO();
    }


    @GetMapping(value = "/api-general/delete-image-slider-item")
    public void deleteImageSliderItem(
            @RequestHeader("Authorization") String token,
            @RequestParam(value = "tableName") String tableName,
            @RequestParam(value = "columnCatIdName") String columnCatIdName,
            @RequestParam(value = "cateId") Integer cateId,
            @RequestParam(value = "imageSliderItemId") Integer imageSliderItemId
    ){

        if (!totalUserPermissionService.requireMasterRole(token)){
            return;
        }

        generalService.deleteImageSliderItem(tableName, columnCatIdName, cateId, imageSliderItemId);
    }



    //    @PostMapping(value = "/api-general")
//    public void processReturnProduct(@RequestBody OrderRequestDTO orderRequestDTO){
//        generalService.
//                processReturnProduct(orderRequestDTO.getItemOfOrderDTO(), orderRequestDTO.getAction());
//    }
//
//    @PostMapping(value = "/api-general-send-exchange-or-return-item")
//    public void sendExchangeOrReturnItem(@RequestBody ItemOfOrderDTO itemOfOrderDTO){
//        generalService.sendExchangeOrReturnItem(itemOfOrderDTO);
//    }


}
