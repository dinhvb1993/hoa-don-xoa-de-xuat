package com.simple_official_spring_template.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {
    private Integer id;
//    private String parentId;
    private String userId;
    private Integer productId;
    private Double rate;
    private String title;
    private String content;
    private Boolean isPurchased;
    private Integer[] ids;

    public CommentDTO() {
    }

    public CommentDTO(Integer id, String userId, Integer productId, Double rate, String title, String content, Boolean isPurchased) {
        this.id = id;
//        this.parentId = parentId;
        this.userId = userId;
        this.productId = productId;
        this.rate = rate;
        this.title = title;
        this.content = content;
        this.isPurchased = isPurchased;
    }
}
