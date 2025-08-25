package com.simple_official_spring_template.service;

import com.simple_official_spring_template.dto.ImageSliderItemDTO;

import java.util.List;

public interface IImageSliderItemService {
    List<ImageSliderItemDTO> findAll();
    ImageSliderItemDTO findOneById(Integer id);
    ImageSliderItemDTO save(ImageSliderItemDTO productOfImageSliderItemDTO);
    ImageSliderItemDTO update(ImageSliderItemDTO productOfImageSliderItemDTO);
    void delete(ImageSliderItemDTO imageSliderItemDTO);
}
