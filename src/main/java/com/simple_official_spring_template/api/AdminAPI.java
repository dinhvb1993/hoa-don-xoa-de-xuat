package com.simple_official_spring_template.api;

import com.simple_official_spring_template.authentication.service.IAdminLoginService;
import com.simple_official_spring_template.dto.AdminDTO;
import com.simple_official_spring_template.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminAPI {
    @Autowired
    private IAdminService adminService;

    @Autowired
    private IAdminLoginService adminLoginService;

    @GetMapping(value = "/api-admin/{id}")
    public AdminDTO getAdminById(@PathVariable(value = "id", required = true) String id){
        return adminService.findOneById(id);
    }

    @GetMapping(value = "/api-admin")
    public List<AdminDTO> getAdmins(){
        return adminService.findAll();
    }

    @GetMapping(value = "/api-get-admin-by-email/{email}")
    public AdminDTO getAdminByEmail(@PathVariable(value = "email", required = true) String email){
        return adminService.findOneByEmail(email);
    }

    @GetMapping(value = "/api-get-admin")
    public AdminDTO getAdminByToken(@RequestHeader("Authorization") String token) {
        return adminService.getAccount(adminLoginService.parseToken(token.replace("Bearer ", "")));
    }

    @PostMapping(value = "/api-admin")
    public AdminDTO createAdmin(@RequestBody AdminDTO adminDTO){
        return adminService.save(adminDTO);
    }

    @PutMapping(value = "/api-admin")
    public AdminDTO updateAdmin(@RequestBody AdminDTO adminDTO){
        return adminService.update(adminDTO);
    }

    @DeleteMapping(value = "/api-admin")
    public void deleteAdmins(@RequestBody AdminDTO adminDTO){
        adminService.delete(adminDTO);
    }
}
