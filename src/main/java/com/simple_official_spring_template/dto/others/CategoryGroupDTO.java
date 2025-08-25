package com.simple_official_spring_template.dto.others;

import com.simple_official_spring_template.dto.CategoryDTO;
import com.simple_official_spring_template.repository.ICategoryRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CategoryGroupDTO implements Comparable<CategoryGroupDTO>{
//    private Integer categoryId;
//    private Integer orderNumber;
private CategoryDTO categoryDTO;
    private int degree;
    private List<Integer> childCategoryIds;
    private List<Integer> allChildCategoryIds;
    private List<CategoryGroupDTO> childCategoryGroupDTO;
    private List<CategoryDTO> allChildCategoryDTO;
    private String compareFeild;

    @Autowired
    private ICategoryRepository categoryRepository;

    public CategoryGroupDTO() {
    }

    public CategoryGroupDTO(CategoryDTO categoryDTO) {
        this.categoryDTO = categoryDTO;
        this.childCategoryIds = new ArrayList<>();
        this.allChildCategoryIds = new ArrayList<>();
        this.childCategoryGroupDTO = new ArrayList<>();
    }

    @Override
    public int compareTo(CategoryGroupDTO o) {
//        return this.categoryDTO.compareTo(o.categoryDTO);
        return this.categoryDTO.compareTo(o.getCategoryDTO());
    }
}
