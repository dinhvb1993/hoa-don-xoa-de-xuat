package com.simple_official_spring_template.service;

import com.simple_official_spring_template.dto.PromotionDTO;

import java.util.List;

public interface IPromotionService {
    PromotionDTO save(PromotionDTO promotionDTO);
    PromotionDTO update(PromotionDTO promotionDTO);
    PromotionDTO findOneById(Integer id);
    List<PromotionDTO> findAll();
    List<PromotionDTO> findByValidityPeriodAfter();
    void delete(PromotionDTO promotionDTO);
}
