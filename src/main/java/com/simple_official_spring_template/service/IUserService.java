package com.simple_official_spring_template.service;

import com.simple_official_spring_template.dto.UserDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {
    UserDTO save(UserDTO userDTO);
    UserDTO update(UserDTO userDTO);
    UserDTO findOneById(String id);
    UserDTO findOneByEmail(String email);
    List<UserDTO> findAll();
    List<UserDTO> findAll(Pageable pageable);
    UserDTO getAccount(String roleUsername);
    void delete(UserDTO userDTO);
}
