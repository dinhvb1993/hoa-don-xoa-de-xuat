package com.simple_official_spring_template.converter;

import com.simple_official_spring_template.dto.ImageSliderItemDTO;
import com.simple_official_spring_template.dto.ProductTypeDTO;
import com.simple_official_spring_template.entity.ImageSliderItemEntity;
import com.simple_official_spring_template.entity.ProductTypeEntity;
import com.simple_official_spring_template.repository.IProductTypeRepository;
import com.simple_official_spring_template.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductTypeConverter {
    @Autowired
    private IProductTypeRepository productTypeRepository;

    @Autowired
    private com.simple_official_spring_template.converter.ImageSliderItemConverter imageSliderItemConverter;

    public ProductTypeEntity toEntity(ProductTypeDTO productTypeDTO, ProductTypeEntity productTypeEntity) {
        if (productTypeDTO.getId() != null) {
            productTypeEntity.setId(productTypeDTO.getId());
        }
        if (productTypeDTO.getName() != null) {
            productTypeEntity.setName(StringUtil.removeSpace(productTypeDTO.getName()));
        }
        if (productTypeDTO.getSlug() != null) {
            productTypeEntity.setSlug(productTypeDTO.getSlug());
        }
        if (productTypeDTO.getOrderNumber() != null) {
            productTypeEntity.setOrderNumber(productTypeDTO.getOrderNumber());
        }
        if (productTypeDTO.getDescription() != null) {
            productTypeEntity.setDescription(productTypeDTO.getDescription());
        }
        if (productTypeDTO.getParentId() != null) {
            productTypeEntity.setParent(productTypeRepository.findOneById(productTypeDTO.getParentId()));
        }
        if (productTypeDTO.getShowHome() != null) {
            productTypeEntity.setShowHome(productTypeDTO.getShowHome());
        }
        if (productTypeDTO.getImageSliderAvatarItemDTO() != null){
            productTypeEntity.setImageSliderAvatarItem(imageSliderItemConverter.toEntity(productTypeDTO.getImageSliderAvatarItemDTO(), new ImageSliderItemEntity()));
        }
        if (productTypeDTO.getImageSliderItemDTOS() != null){
            List<ImageSliderItemEntity> imageSliderItemEntities = new ArrayList<>();
            for (ImageSliderItemDTO imageSliderItemDTO: productTypeDTO.getImageSliderItemDTOS()){
                imageSliderItemEntities.add(imageSliderItemConverter.toEntity(imageSliderItemDTO, new ImageSliderItemEntity()));
            }
            productTypeEntity.setImageSliderItems(imageSliderItemEntities);
        }
        return productTypeEntity;
    }

    public ProductTypeDTO toDTO(ProductTypeEntity productTypeEntity) {
        List<ImageSliderItemDTO> imageSliderItemDTOS = new ArrayList<>();
        if (productTypeEntity.getImageSliderItems() != null){
            for (ImageSliderItemEntity imageSliderItemEntity: productTypeEntity.getImageSliderItems()){
                imageSliderItemDTOS.add(imageSliderItemConverter.toDTO(imageSliderItemEntity));
            }
        }
        return new ProductTypeDTO(
                productTypeEntity.getId(),
                productTypeEntity.getName(),
                productTypeEntity.getSlug(),
                productTypeEntity.getOrderNumber(),
                productTypeEntity.getDescription(),
                null,
                productTypeEntity.getShowHome(),
                productTypeEntity.getCreationTime(),
                productTypeEntity.getUpdateTime(),
                0,
                productTypeEntity.getParent() != null ? productTypeEntity.getParent().getId(): null,
                productTypeEntity.getImageSliderAvatarItem() != null? imageSliderItemConverter.toDTO(productTypeEntity.getImageSliderAvatarItem()): null,
                imageSliderItemDTOS
        );
    }
}
