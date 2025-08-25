package com.simple_official_spring_template.api;

import com.simple_official_spring_template.dto.UserSearchHistoryDTO;
import com.simple_official_spring_template.service.IUserSearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserSearchHistoryAPI {
    @Autowired
    private IUserSearchHistoryService userSearchHistoryService;

    @GetMapping(value = "/api-user-search-history/{id}")
    public UserSearchHistoryDTO getUserSearchHistoryById(@PathVariable(value = "id", required = true) Integer id){
        return userSearchHistoryService.findOneById(id);
    }

    @GetMapping(value = "/api-user-search-history")
    public List<UserSearchHistoryDTO> getUserSearchHistories(@RequestParam(name = "userId", required = false) String userId){
        if (userId != null) {
            return userSearchHistoryService.findByUserId(userId);
        }
        return userSearchHistoryService.findAll();
    }


    @PostMapping(value = "/api-user-search-history")
    public UserSearchHistoryDTO createUserSearchHistory(@RequestBody UserSearchHistoryDTO userSearchHistoryDTO){
        return userSearchHistoryService.save(userSearchHistoryDTO);
    }

    @PutMapping(value = "/api-user-search-history")
    public UserSearchHistoryDTO updateUserSearchHistory(@RequestBody UserSearchHistoryDTO userSearchHistoryDTO){
        return userSearchHistoryService.update(userSearchHistoryDTO);
    }

    @DeleteMapping(value = "/api-user-search-history")
    public void deleteUserSearchHistories(@RequestBody UserSearchHistoryDTO userSearchHistoryDTO){
        userSearchHistoryService.delete(userSearchHistoryDTO);
    }
}
