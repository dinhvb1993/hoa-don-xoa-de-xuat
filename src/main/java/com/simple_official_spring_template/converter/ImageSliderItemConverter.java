package com.simple_official_spring_template.converter;

import com.simple_official_spring_template.dto.ImageSliderItemDTO;
import com.simple_official_spring_template.entity.ImageSliderItemEntity;
import com.simple_official_spring_template.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImageSliderItemConverter {
    @Autowired
    private IProductRepository productRepository;

    public ImageSliderItemEntity toEntity(ImageSliderItemDTO imageSliderItemDTO, ImageSliderItemEntity imageSliderItemEntity) {
        if (imageSliderItemDTO.getId() != null) {
            imageSliderItemEntity.setId(imageSliderItemDTO.getId());
        }
        if (imageSliderItemDTO.getUrl() != null) {
            imageSliderItemEntity.setUrl(imageSliderItemDTO.getUrl());
        }
        if (imageSliderItemDTO.getCaption() != null) {
            imageSliderItemEntity.setTitle(imageSliderItemDTO.getTitle());
        }
        if (imageSliderItemDTO.getLink() != null) {
            imageSliderItemEntity.setLink(imageSliderItemDTO.getLink());
        }
        if (imageSliderItemDTO.getCaption() != null) {
            imageSliderItemEntity.setCaption(imageSliderItemDTO.getCaption());
        }
        return imageSliderItemEntity;
    }

    public ImageSliderItemDTO toDTO(ImageSliderItemEntity imageSliderItemEntity) {
        return new ImageSliderItemDTO(
                imageSliderItemEntity.getId(),
                imageSliderItemEntity.getUrl(),
                imageSliderItemEntity.getTitle(),
                imageSliderItemEntity.getLink(),
                imageSliderItemEntity.getCaption()
        );
    }
}
