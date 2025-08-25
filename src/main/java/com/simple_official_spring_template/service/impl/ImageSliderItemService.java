package com.simple_official_spring_template.service.impl;

import com.simple_official_spring_template.converter.ImageSliderItemConverter;
import com.simple_official_spring_template.dto.ImageSliderItemDTO;
import com.simple_official_spring_template.entity.ImageSliderItemEntity;
import com.simple_official_spring_template.repository.IImageSliderItemRepository;
import com.simple_official_spring_template.service.IImageSliderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageSliderItemService implements IImageSliderItemService {
    @Autowired
    private IImageSliderItemRepository imageSliderItemRepository;

    @Autowired
    private ImageSliderItemConverter imageSliderItemConverter;

    @Override
    public List<ImageSliderItemDTO> findAll() {
        List<ImageSliderItemDTO> imageSliderItemDTOS = new ArrayList<>();

        List<ImageSliderItemEntity> imageSliderItemEntities = imageSliderItemRepository.findAll();
        for (ImageSliderItemEntity imageSliderItemEntity : imageSliderItemEntities) {
            imageSliderItemDTOS.add(imageSliderItemConverter.toDTO(imageSliderItemEntity));
        }

        return imageSliderItemDTOS;
    }

    @Override
    public ImageSliderItemDTO findOneById(Integer id) {
        ImageSliderItemEntity imageSliderItemEntity = imageSliderItemRepository.findOneById(id);
        return imageSliderItemConverter.toDTO(imageSliderItemEntity);
    }

    @Override
    public ImageSliderItemDTO save(ImageSliderItemDTO imageSliderItemDTO) {
        ImageSliderItemEntity imageSliderItemEntity = imageSliderItemConverter.toEntity(imageSliderItemDTO, new ImageSliderItemEntity());
        imageSliderItemEntity = imageSliderItemRepository.save(imageSliderItemEntity);
        return imageSliderItemConverter.toDTO(imageSliderItemEntity);
    }

    @Override
    public ImageSliderItemDTO update(ImageSliderItemDTO imageSliderItemDTO) {
        ImageSliderItemEntity imageSliderItemEntity = imageSliderItemRepository.findOneById(imageSliderItemDTO.getId());
        imageSliderItemEntity = imageSliderItemConverter.toEntity(imageSliderItemDTO, imageSliderItemEntity);
        imageSliderItemEntity = imageSliderItemRepository.save(imageSliderItemEntity);
        return imageSliderItemConverter.toDTO(imageSliderItemEntity);
    }

    @Override
    public void delete(ImageSliderItemDTO imageSliderItemDTO) {
        imageSliderItemRepository.deleteByIds(imageSliderItemDTO.getIds());
    }
}
