package com.simple_official_spring_template.converter;

import com.simple_official_spring_template.dto.UserAddressDTO;
import com.simple_official_spring_template.entity.UserAddressEntity;
import com.simple_official_spring_template.repository.IUserRepository;
import com.simple_official_spring_template.repository.IWardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAddressConverter {
    @Autowired
    private IWardRepository wardRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private WardConverter wardConverter;

    public UserAddressEntity toEntity(UserAddressDTO userAddressDTO, UserAddressEntity userAddressEntity) {
        if (userAddressDTO.getId() != null) {
            userAddressEntity.setId(userAddressDTO.getId());
        }
        if (userAddressDTO.getStreet() != null) {
            userAddressEntity.setStreet(userAddressDTO.getStreet());
        }
        if (userAddressDTO.getWardDTO() != null) {
            userAddressEntity.setWard(wardRepository.findOneById(userAddressDTO.getWardDTO().getId()));
        }
        if (userAddressDTO.getUserId() != null) {
            userAddressEntity.setUser(userRepository.findOneById(userAddressDTO.getUserId()));
        }
        return userAddressEntity;
    }

    public UserAddressDTO toDTO(UserAddressEntity userAddressEntity) {
        return new UserAddressDTO(
                userAddressEntity.getId(),
                userAddressEntity.getStreet(),
                wardConverter.toDTO(wardRepository.findOneById(userAddressEntity.getWard().getId())),
                userAddressEntity.getUser().getId()
        );
    }
}
