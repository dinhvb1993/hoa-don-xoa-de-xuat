package com.simple_official_spring_template.service.impl;

import com.simple_official_spring_template.converter.UserAddressConverter;
import com.simple_official_spring_template.dto.UserAddressDTO;
import com.simple_official_spring_template.entity.UserAddressEntity;
import com.simple_official_spring_template.repository.IUserAddressRepository;
import com.simple_official_spring_template.service.IUserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAddressService implements IUserAddressService {
    @Autowired
    private IUserAddressRepository userAddressRepository;

    @Autowired
    private UserAddressConverter userAddressConverter;

    @Override
    public List<UserAddressDTO> findAll() {
        List<UserAddressDTO> userAddressDTOS = new ArrayList<>();

        List<UserAddressEntity> userAddressEntities = userAddressRepository.findAll();
        for (UserAddressEntity userAddressEntity : userAddressEntities) {
            userAddressDTOS.add(userAddressConverter.toDTO(userAddressEntity));
        }

        return userAddressDTOS;
    }

    @Override
    public List<UserAddressDTO> findByUserId(String userId) {
        List<UserAddressDTO> userAddressDTOS = new ArrayList<>();

        List<UserAddressEntity> userAddressEntities = userAddressRepository.findByUserId(userId);
        for (UserAddressEntity userAddressEntity : userAddressEntities) {
            userAddressDTOS.add(userAddressConverter.toDTO(userAddressEntity));
        }

        return userAddressDTOS;
    }

    @Override
    public UserAddressDTO findOneById(Integer id) {
        UserAddressEntity userAddressEntity = userAddressRepository.findOneById(id);
        return userAddressConverter.toDTO(userAddressEntity);
    }

    @Override
    public UserAddressDTO save(UserAddressDTO userAddressDTO) {
        UserAddressEntity userAddressEntity = userAddressConverter.toEntity(userAddressDTO, new UserAddressEntity());
        userAddressEntity = userAddressRepository.save(userAddressEntity);
        return userAddressConverter.toDTO(userAddressEntity);
    }

    @Override
    public UserAddressDTO update(UserAddressDTO userAddressDTO) {
        UserAddressEntity userAddressEntity = userAddressRepository.findOneById(userAddressDTO.getId());
        userAddressEntity = userAddressConverter.toEntity(userAddressDTO, userAddressEntity);
        userAddressEntity = userAddressRepository.save(userAddressEntity);
        return userAddressConverter.toDTO(userAddressEntity);
    }

    @Override
    public void delete(UserAddressDTO userAddressDTO) {
        userAddressRepository.deleteByIds(userAddressDTO.getIds());
    }
}
