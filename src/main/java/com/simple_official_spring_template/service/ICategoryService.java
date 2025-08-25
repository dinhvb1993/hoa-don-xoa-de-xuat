package com.simple_official_spring_template.service;

import com.simple_official_spring_template.dto.CategoryDTO;
import com.simple_official_spring_template.dto.others.CategoryGroupDTO;

import java.util.List;

public interface ICategoryService {
    List<CategoryDTO> findAll();
    CategoryDTO findOneById(Integer id);
    CategoryDTO save(CategoryDTO categoryDTO);
    CategoryDTO update(CategoryDTO categoryDTO);
    void delete(CategoryDTO categoryDTO);
    CategoryGroupDTO findInheritCategory(Integer parentCategoryId, String orderBy, String orderType);
}
