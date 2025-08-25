package com.simple_official_spring_template.service.impl;

import com.simple_official_spring_template.authentication.utils.Hashing;
import com.simple_official_spring_template.converter.UserConverter;
import com.simple_official_spring_template.dto.UserDTO;
import com.simple_official_spring_template.entity.UserEntity;
import com.simple_official_spring_template.repository.IUserRepository;
import com.simple_official_spring_template.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDTO save(UserDTO userDTO) {

        if (userDTO.getPassword() != null){
            userDTO.setPassword(Hashing.hash(userDTO.getPassword()));
        }

        UserEntity userEntity = userConverter.toEntity(userDTO, new UserEntity());
        return userConverter.toDTO(userRepository.save(userEntity));
    }

    @Override
    public UserDTO update(UserDTO userDTO) {

        if (userDTO.getPassword() != null){
            userDTO.setPassword(Hashing.hash(userDTO.getPassword()));
        }

        UserEntity userEntity = userRepository.findOneById(userDTO.getId());
        userEntity = userConverter.toEntity(userDTO, userEntity);
        return userConverter.toDTO(userRepository.save(userEntity));
    }

    @Override
    public UserDTO findOneById(String id) {
        return userConverter.toDTO(userRepository.findOneById(id));
    }

    @Override
    public UserDTO findOneByEmail(String email) {
        return userConverter.toDTO(userRepository.findOneByEmail(email));
    }

    @Override
    public List<UserDTO> findAll() {
        List<UserDTO> userDTOS = new ArrayList<>();
        for (UserEntity userEntity : userRepository.findAll()){
            userDTOS.add(userConverter.toDTO(userEntity));
        }
        return userDTOS;
    }

    @Override
    public List<UserDTO> findAll(Pageable pageable) {
        List<UserDTO> userDTOS = new ArrayList<>();
        for (UserEntity userEntity : userRepository.findAll(pageable).getContent()){
            userDTOS.add(userConverter.toDTO(userEntity));
        }
        return userDTOS;
    }

    @Override
    public UserDTO getAccount(String roleUsername) {
        String userName = roleUsername.replaceAll(roleUsername.split("_")[0] + "_", "");
        if (userRepository.findOneById(userName)  != null){
            return userConverter.toDTO(userRepository.findOneById(userName));
        }
        return null;
    }

    @Override
    public void delete(UserDTO userDTO) {
        userRepository.deleteByIds(userDTO.getIds());
    }
}
