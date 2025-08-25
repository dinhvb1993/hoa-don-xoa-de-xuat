package com.simple_official_spring_template.converter;

import com.simple_official_spring_template.dto.CategoryDTO;
import com.simple_official_spring_template.dto.ImageSliderItemDTO;
import com.simple_official_spring_template.entity.CategoryEntity;
import com.simple_official_spring_template.entity.ImageSliderItemEntity;
import com.simple_official_spring_template.repository.ICategoryRepository;
import com.simple_official_spring_template.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryConverter {
    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private ImageSliderItemConverter imageSliderItemConverter;

    public CategoryEntity toEntity(CategoryDTO categoryDTO, CategoryEntity categoryEntity) {
        if (categoryDTO.getId() != null) {
            categoryEntity.setId(categoryDTO.getId());
        }
        if (categoryDTO.getName() != null) {
            categoryEntity.setName(StringUtil.removeSpace(categoryDTO.getName()));
        }
        if (categoryDTO.getSlug() != null) {
            categoryEntity.setSlug(categoryDTO.getSlug());
        }
        if (categoryDTO.getOrderNumber() != null) {
            categoryEntity.setOrderNumber(categoryDTO.getOrderNumber());
        }
        if (categoryDTO.getDescription() != null) {
            categoryEntity.setDescription(categoryDTO.getDescription());
        }
        if (categoryDTO.getParentCategoryDTO() != null) {
            categoryEntity.setParent(categoryRepository.findOneById(categoryDTO.getParentCategoryDTO().getId()));
        }
        if (categoryDTO.getShowHome() != null) {
            categoryEntity.setShowHome(categoryDTO.getShowHome());
        }
        if (categoryDTO.getImageSliderAvatarItemDTO() != null){
            categoryEntity.setImageSliderAvatarItem(imageSliderItemConverter.toEntity(categoryDTO.getImageSliderAvatarItemDTO(), new ImageSliderItemEntity()));
        }
        if (categoryDTO.getImageSliderItemDTOS() != null){
            List<ImageSliderItemEntity> imageSliderItemEntities = new ArrayList<>();
            for (ImageSliderItemDTO imageSliderItemDTO: categoryDTO.getImageSliderItemDTOS()){
                imageSliderItemEntities.add(imageSliderItemConverter.toEntity(imageSliderItemDTO, new ImageSliderItemEntity()));
            }
            categoryEntity.setImageSliderItems(imageSliderItemEntities);
        }

        if (categoryDTO.getExceptionNote() != null){
            if (categoryDTO.getExceptionNote().equals("set_null_parent_category")){
                categoryEntity.setParent(null);
            }
        }

        return categoryEntity;
    }

    public CategoryDTO toDTO(CategoryEntity categoryEntity) {

        List<ImageSliderItemDTO> imageSliderItemDTOS = new ArrayList<>();
        if (categoryEntity.getImageSliderItems() != null){
            for (ImageSliderItemEntity imageSliderItemEntity: categoryEntity.getImageSliderItems()){
                imageSliderItemDTOS.add(imageSliderItemConverter.toDTO(imageSliderItemEntity));
            }
        }
        return new CategoryDTO(
                categoryEntity.getId(),
                categoryEntity.getName(),
                categoryEntity.getSlug(),
                categoryEntity.getOrderNumber(),
                categoryEntity.getDescription(),
                null,
                categoryEntity.getShowHome(),
                categoryEntity.getCreationTime(),
                categoryEntity.getUpdateTime(),
                0,
                categoryEntity.getParent() != null ? this.toDTO(categoryEntity.getParent()): null,
//                categoryEntity.getParent() != null ? categoryEntity.getParent().getName(): null,
                categoryEntity.getImageSliderAvatarItem() != null? imageSliderItemConverter.toDTO(categoryEntity.getImageSliderAvatarItem()): null,
                imageSliderItemDTOS
        );
    }
}
