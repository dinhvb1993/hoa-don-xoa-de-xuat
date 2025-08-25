package com.simple_official_spring_template.service;

import com.simple_official_spring_template.dto.UserSearchHistoryDTO;

import java.util.List;

public interface IUserSearchHistoryService {
    UserSearchHistoryDTO save(UserSearchHistoryDTO userSearchHistoryDTO);
    UserSearchHistoryDTO update(UserSearchHistoryDTO userSearchHistoryDTO);
    UserSearchHistoryDTO findOneById(Integer id);
    List<UserSearchHistoryDTO> findByUserId(String customerId);
    List<UserSearchHistoryDTO> findAll();
    void delete(UserSearchHistoryDTO userSearchHistoryDTO);
}
