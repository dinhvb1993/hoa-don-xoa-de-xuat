package com.simple_official_spring_template.api;

import com.simple_official_spring_template.dto.CategoryDTO;
import com.simple_official_spring_template.dto.others.CategoryGroupDTO;
import com.simple_official_spring_template.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryAPI {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping(value = "/api-category")
    public List<CategoryDTO> getCategories() {
        return categoryService.findAll();
    }

    @GetMapping(value = "/api-category/{id}")
    public CategoryDTO getCategoryById(@PathVariable(value = "id", required = true) Integer id) {
        return categoryService.findOneById(id);
    }

    @GetMapping(value = "/api-category/get-by-inherit-category-id")
    public CategoryGroupDTO getInheritCategory(
            @RequestParam(value = "categoryId", required = false) Integer categoryId, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "order_by", required = false) String orderBy, @RequestParam(value = "order_type", required = false) String orderType
    ){
        return categoryService.findInheritCategory(categoryId, orderBy, orderType);
    }

    @PostMapping(value = "/api-category")
    public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.save(categoryDTO);
    }

    @PutMapping(value = "/api-category")
    public CategoryDTO updateCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.update(categoryDTO);
    }

    @DeleteMapping(value = "api-category")
    public void deleteCategories(@RequestBody CategoryDTO categoryDTO) {
        categoryService.delete(categoryDTO);
    }
}
