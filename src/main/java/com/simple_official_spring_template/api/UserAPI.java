package com.simple_official_spring_template.api;

import com.simple_official_spring_template.authentication.service.IAdminLoginService;
import com.simple_official_spring_template.authentication.service.IUserLoginService;
import com.simple_official_spring_template.dto.UserDTO;
import com.simple_official_spring_template.service.IAdminService;
import com.simple_official_spring_template.service.ITotalUserPermissionService;
import com.simple_official_spring_template.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.simple_official_spring_template.authentication.utils.Constant.DELETE_POST;
import static com.simple_official_spring_template.authentication.utils.Constant.VIEW_POST;

@RestController
public class UserAPI {
    @Autowired
    private IUserService userService;

    @Autowired
    private IUserLoginService userLoginService;

    @Autowired
    private IAdminService adminService;

    @Autowired
    private IAdminLoginService adminLoginService;

    @Autowired
    private ITotalUserPermissionService totalUserPermissionService;

    @GetMapping(value = "/api-user/{id}")
    public UserDTO getUserById(@PathVariable(value = "id", required = true) String id){
        return userService.findOneById(id);
    }

    @GetMapping(value = "/api-get-user-by-email/{email}")
    public UserDTO getUserByEmail(@PathVariable(value = "email", required = true) String email){
        return userService.findOneByEmail(email);
    }

    @GetMapping(value = "/api-user")
    public List<UserDTO> getUsers(
            @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "order_by", required = false) String orderBy, @RequestParam(value = "order_type", required = false) String orderType
    ){

        if (page == null || limit == null){
            page = 1;
            limit = Integer.MAX_VALUE;
        }

        Pageable pageable;
        Sort sort;
        if (orderBy == null || orderType == null){
            pageable = PageRequest.of(page-1, limit);
        }
        else {
            if (orderType.equals("desc")){
                sort = Sort.by(orderBy).descending();
            }
            else {
                sort = Sort.by(orderBy).ascending();
            }

            pageable = PageRequest.of(page-1, limit, sort);
        }



        return userService.findAll(pageable);
    }

    @GetMapping(value = "/api-get-user")
    public UserDTO getUserByToken(@RequestHeader("Authorization") String token) {
        return userService.getAccount(userLoginService.parseToken(token.replace("Bearer ", "")));
    }

    @PostMapping(value = "/api-user")
    public UserDTO createUser(@RequestBody UserDTO userDTO){
        return userService.save(userDTO);
    }

    @PutMapping(value = "/api-user")
    public UserDTO updateUser(@RequestHeader("Authorization") String token, @RequestBody UserDTO userDTO){

        if (token.equals("Bearer")){
            if (!userDTO.isException()){
                return null;
            }
//            userDTO.setException(false);
        }
        else if (
                !totalUserPermissionService.requireMasterRole(token)
                        && !userService.getAccount(userLoginService.parseToken(token.replace("Bearer ", ""))).getId().equals(userDTO.getId())
        ){
            return null;
        }


        return userService.update(userDTO);
    }

    @DeleteMapping(value = "/api-user")
    public void deleteUsers(@RequestHeader("Authorization") String token, @RequestBody UserDTO userDTO){
        if (!totalUserPermissionService.requireMasterRole(token)){
            return;
        }
        userService.delete(userDTO);
    }
}
