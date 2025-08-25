package com.simple_official_spring_template.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class UserSearchHistoryDTO {

    private Integer id;
    private String userId;
    private String keyword;
    private Timestamp time;
    private Integer[] ids;

    public UserSearchHistoryDTO(Integer id, String userId, String keyword, Timestamp time) {
        this.id = id;
        this.userId = userId;
        this.keyword = keyword;
        this.time = time;
    }
}
