package com.simple_official_spring_template.dto.others;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AttributeCategoryFilterDTO {
    Integer limit;
    Integer page;
    String orderBy;
    String orderType;
    List<Integer> attributeCategoryIds;
    Integer categoryId;
}
