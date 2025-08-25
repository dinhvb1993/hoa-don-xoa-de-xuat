package com.simple_official_spring_template.repository;

import com.simple_official_spring_template.entity.ImageSliderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IImageSliderItemRepository extends JpaRepository<ImageSliderItemEntity, Integer> {
    ImageSliderItemEntity findOneById(Integer id);

    @Query(nativeQuery =true, value = "DELETE FROM image_slider_item as t WHERE t.id IN :ids")
    void deleteByIds(@Param("ids") Integer[] ids);
}
