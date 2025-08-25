package com.simple_official_spring_template.api;

import com.simple_official_spring_template.dto.ImageSliderItemDTO;
import com.simple_official_spring_template.service.IImageSliderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ImageSliderItemAPI {
    @Autowired
    private IImageSliderItemService imageSliderItemService;

    @GetMapping(value = "/api-image-slider-item")
    public List<ImageSliderItemDTO> getImageSliderItems() {
        return imageSliderItemService.findAll();
    }

    @GetMapping(value = "/api-image-slider-item/{id}")
    public ImageSliderItemDTO getImageSliderItemById(@PathVariable(name = "id", required = true) Integer id) {
        return imageSliderItemService.findOneById(id);
    }

    @PostMapping(value = "/api-image-slider-item")
    public ImageSliderItemDTO createImageSliderItem(@RequestBody ImageSliderItemDTO imageSliderItemDTO) {
        return imageSliderItemService.save(imageSliderItemDTO);
    }

    @PutMapping(value = "/api-image-slider-item")
    public ImageSliderItemDTO updateImageSliderItem(@RequestBody ImageSliderItemDTO imageSliderItemDTO) {
        return imageSliderItemService.update(imageSliderItemDTO);
    }

    @DeleteMapping(value = "/api-image-slider-item")
    public void deleteImageSliderItems(@RequestBody ImageSliderItemDTO imageSliderItemDTO) {
        imageSliderItemService.delete(imageSliderItemDTO);
    }
}
