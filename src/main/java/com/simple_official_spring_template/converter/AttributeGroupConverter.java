package com.simple_official_spring_template.converter;

import com.simple_official_spring_template.dto.AttributeGroupDTO;
import com.simple_official_spring_template.dto.AttributeGroupDTO;
import com.simple_official_spring_template.dto.ImageSliderItemDTO;
import com.simple_official_spring_template.entity.AttributeGroupEntity;
import com.simple_official_spring_template.entity.AttributeGroupEntity;
import com.simple_official_spring_template.entity.ImageSliderItemEntity;
import com.simple_official_spring_template.repository.IAttributeGroupRepository;
import com.simple_official_spring_template.repository.IAttributeGroupRepository;
import com.simple_official_spring_template.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AttributeGroupConverter {
    @Autowired
    private IAttributeGroupRepository attributeGroupRepository;

    @Autowired
    private ImageSliderItemConverter imageSliderItemConverter;

    public AttributeGroupEntity toEntity(AttributeGroupDTO attributeGroupDTO, AttributeGroupEntity attributeGroupEntity) {
        if (attributeGroupDTO.getId() != null) {
            attributeGroupEntity.setId(attributeGroupDTO.getId());
        }
        if (attributeGroupDTO.getName() != null) {
            attributeGroupEntity.setName(StringUtil.removeSpace(attributeGroupDTO.getName()));
        }
        if (attributeGroupDTO.getSlug() != null) {
            attributeGroupEntity.setSlug(attributeGroupDTO.getSlug());
        }
        if (attributeGroupDTO.getOrderNumber() != null) {
            attributeGroupEntity.setOrderNumber(attributeGroupDTO.getOrderNumber());
        }
        if (attributeGroupDTO.getDescription() != null) {
            attributeGroupEntity.setDescription(attributeGroupDTO.getDescription());
        }
        if (attributeGroupDTO.getParentAttributeGroupDTO() != null) {
            attributeGroupEntity.setParent(attributeGroupRepository.findOneById(attributeGroupDTO.getParentAttributeGroupDTO().getId()));
        }
        if (attributeGroupDTO.getShowHome() != null) {
            attributeGroupEntity.setShowHome(attributeGroupDTO.getShowHome());
        }
        if (attributeGroupDTO.getImageSliderAvatarItemDTO() != null){
            attributeGroupEntity.setImageSliderAvatarItem(imageSliderItemConverter.toEntity(attributeGroupDTO.getImageSliderAvatarItemDTO(), new ImageSliderItemEntity()));
        }
        if (attributeGroupDTO.getImageSliderItemDTOS() != null){
            List<ImageSliderItemEntity> imageSliderItemEntities = new ArrayList<>();
            for (ImageSliderItemDTO imageSliderItemDTO: attributeGroupDTO.getImageSliderItemDTOS()){
                imageSliderItemEntities.add(imageSliderItemConverter.toEntity(imageSliderItemDTO, new ImageSliderItemEntity()));
            }
            attributeGroupEntity.setImageSliderItems(imageSliderItemEntities);
        }

        if (attributeGroupDTO.getExceptionNote() != null){
            if (attributeGroupDTO.getExceptionNote().equals("set_null_parent_attributeGroup")){
                attributeGroupEntity.setParent(null);
            }
        }

        return attributeGroupEntity;
    }

    public AttributeGroupDTO toDTO(AttributeGroupEntity attributeGroupEntity) {

        List<ImageSliderItemDTO> imageSliderItemDTOS = new ArrayList<>();
        if (attributeGroupEntity.getImageSliderItems() != null){
            for (ImageSliderItemEntity imageSliderItemEntity: attributeGroupEntity.getImageSliderItems()){
                imageSliderItemDTOS.add(imageSliderItemConverter.toDTO(imageSliderItemEntity));
            }
        }
        return new AttributeGroupDTO(
                attributeGroupEntity.getId(),
                attributeGroupEntity.getName(),
                attributeGroupEntity.getSlug(),
                attributeGroupEntity.getOrderNumber(),
                attributeGroupEntity.getDescription(),
                null,
                attributeGroupEntity.getShowHome(),
                attributeGroupEntity.getCreationTime(),
                attributeGroupEntity.getUpdateTime(),
                0,
                attributeGroupEntity.getParent() != null ? this.toDTO(attributeGroupEntity.getParent()): null,
//                attributeGroupEntity.getParent() != null ? attributeGroupEntity.getParent().getName(): null,
                attributeGroupEntity.getImageSliderAvatarItem() != null? imageSliderItemConverter.toDTO(attributeGroupEntity.getImageSliderAvatarItem()): null,
                imageSliderItemDTOS
        );
    }
}
