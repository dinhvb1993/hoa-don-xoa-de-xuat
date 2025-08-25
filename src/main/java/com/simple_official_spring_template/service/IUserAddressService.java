package com.simple_official_spring_template.service;

import com.simple_official_spring_template.dto.UserAddressDTO;

import java.util.List;

public interface IUserAddressService {
    List<UserAddressDTO> findAll();
    List<UserAddressDTO> findByUserId(String customerId);
    UserAddressDTO findOneById(Integer id);
    UserAddressDTO save(UserAddressDTO userAddressDTO);
    UserAddressDTO update(UserAddressDTO userAddressDTO);
    void delete(UserAddressDTO userAddressDTO);
}
