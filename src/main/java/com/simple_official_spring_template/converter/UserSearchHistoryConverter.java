package com.simple_official_spring_template.converter;

import com.simple_official_spring_template.dto.UserSearchHistoryDTO;
import com.simple_official_spring_template.entity.UserSearchHistoryEntity;
import com.simple_official_spring_template.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserSearchHistoryConverter {

    @Autowired
    private IUserRepository userRepository;

    public UserSearchHistoryEntity toEntity(UserSearchHistoryDTO userSearchHistoryDTO, UserSearchHistoryEntity userSearchHistoryEntity){
        if (userSearchHistoryDTO.getId() != null){
            userSearchHistoryEntity.setId(userSearchHistoryDTO.getId());
        }
        if (userSearchHistoryDTO.getUserId() != null){
            userSearchHistoryEntity.setUser(userRepository.findOneById(userSearchHistoryDTO.getUserId()));
        }
        if (userSearchHistoryDTO.getKeyword() != null){
            userSearchHistoryEntity.setKeyword(userSearchHistoryDTO.getKeyword());
        }
        if (userSearchHistoryDTO.getTime() != null){
            userSearchHistoryEntity.setTime(userSearchHistoryDTO.getTime());
        }
        return userSearchHistoryEntity;
    }

    public UserSearchHistoryDTO toDTO(UserSearchHistoryEntity userSearchHistoryEntity){
        return new UserSearchHistoryDTO(
                userSearchHistoryEntity.getId(),
                userSearchHistoryEntity.getUser().getId(),
                userSearchHistoryEntity.getKeyword(),
                userSearchHistoryEntity.getTime()
        );
    }
}
