package com.simple_official_spring_template.api;

import com.simple_official_spring_template.dto.UserAddressDTO;
import com.simple_official_spring_template.service.IUserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserAddressAPI {
    @Autowired
    private IUserAddressService userAddressService;

    @GetMapping(value = "/api-user-address")
    public List<UserAddressDTO> getUserAddresses(@RequestParam(name = "userId", required = false) String userId) {
        if (userId != null) {
            return userAddressService.findByUserId(userId);
        }
        else {
            return userAddressService.findAll();
        }
    }

    @GetMapping(value = "/api-user-address/{id}")
    public UserAddressDTO getUserAddressById(@PathVariable(name = "id", required = true) Integer id) {
        return userAddressService.findOneById(id);
    }

    @PostMapping(value = "/api-user-address")
    public UserAddressDTO createUserAddress(@RequestBody UserAddressDTO userAddressDTO) {
        return userAddressService.save(userAddressDTO);
    }

    @PutMapping(value = "/api-user-address")
    public UserAddressDTO updateUserAddress(@RequestBody UserAddressDTO userAddressDTO) {
        return userAddressService.update(userAddressDTO);
    }

    @DeleteMapping(value = "/api-user-address")
    public void deleteUserAddresses(@RequestBody UserAddressDTO userAddressDTO) {
        userAddressService.delete(userAddressDTO);
    }
}
