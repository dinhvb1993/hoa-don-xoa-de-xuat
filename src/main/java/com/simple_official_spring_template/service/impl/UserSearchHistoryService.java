package com.simple_official_spring_template.service.impl;

import com.simple_official_spring_template.converter.UserSearchHistoryConverter;
import com.simple_official_spring_template.dto.UserSearchHistoryDTO;
import com.simple_official_spring_template.entity.UserSearchHistoryEntity;
import com.simple_official_spring_template.repository.IUserSearchHistoryRepository;
import com.simple_official_spring_template.service.IUserSearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSearchHistoryService implements IUserSearchHistoryService {

    @Autowired
    private IUserSearchHistoryRepository userSearchHistoryRepository;

    @Autowired
    private UserSearchHistoryConverter userSearchHistoryConverter;


    @Override
    public UserSearchHistoryDTO save(UserSearchHistoryDTO userSearchHistoryDTO) {
        UserSearchHistoryEntity userSearchHistoryEntity = userSearchHistoryConverter.toEntity(userSearchHistoryDTO, new UserSearchHistoryEntity());
        return userSearchHistoryConverter.toDTO(userSearchHistoryRepository.save(userSearchHistoryEntity));
    }

    @Override
    public UserSearchHistoryDTO update(UserSearchHistoryDTO userSearchHistoryDTO) {
        UserSearchHistoryEntity userSearchHistoryEntity = userSearchHistoryRepository.findOneById(userSearchHistoryDTO.getId());
        userSearchHistoryEntity = userSearchHistoryConverter.toEntity(userSearchHistoryDTO, userSearchHistoryEntity);
        return userSearchHistoryConverter.toDTO(userSearchHistoryRepository.save(userSearchHistoryEntity));
    }

    @Override
    public UserSearchHistoryDTO findOneById(Integer id) {
        return userSearchHistoryConverter.toDTO(userSearchHistoryRepository.findOneById(id));
    }

    @Override
    public List<UserSearchHistoryDTO> findByUserId(String userId) {
        List<UserSearchHistoryDTO> userSearchHistoryDTOS = new ArrayList<>();
        List<UserSearchHistoryEntity> userSearchHistoryEntities = userSearchHistoryRepository.findByUserId(userId);
        for (UserSearchHistoryEntity userSearchHistoryEntity : userSearchHistoryEntities) {
            userSearchHistoryDTOS.add(userSearchHistoryConverter.toDTO(userSearchHistoryEntity));
        }
        return userSearchHistoryDTOS;
    }

    @Override
    public List<UserSearchHistoryDTO> findAll() {
        List<UserSearchHistoryDTO> userSearchHistoryDTOS = new ArrayList<>();
        for (UserSearchHistoryEntity userSearchHistoryEntity : userSearchHistoryRepository.findAll()){
            userSearchHistoryDTOS.add(userSearchHistoryConverter.toDTO(userSearchHistoryEntity));
        }
        return userSearchHistoryDTOS;
    }

    @Override
    public void delete(UserSearchHistoryDTO userSearchHistoryDTO) {
        userSearchHistoryRepository.deleteByIds(userSearchHistoryDTO.getIds());
    }


}
