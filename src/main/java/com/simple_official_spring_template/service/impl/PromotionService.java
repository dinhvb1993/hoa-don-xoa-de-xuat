package com.simple_official_spring_template.service.impl;

import com.simple_official_spring_template.converter.PromotionConverter;
import com.simple_official_spring_template.dto.PromotionDTO;
import com.simple_official_spring_template.entity.ProductEntity;
import com.simple_official_spring_template.entity.PromotionEntity;
import com.simple_official_spring_template.repository.IProductRepository;
import com.simple_official_spring_template.repository.IPromotionRepository;
import com.simple_official_spring_template.service.IPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PromotionService implements IPromotionService {

    @Autowired
    private IPromotionRepository promotionRepository;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private PromotionConverter promotionConverter;


    @Override
    public PromotionDTO save(PromotionDTO promotionDTO) {
        PromotionEntity promotionEntity = promotionConverter.toEntity(promotionDTO, new PromotionEntity());
        return promotionConverter.toDTO(promotionRepository.save(promotionEntity));
    }

    @Override
    public PromotionDTO update(PromotionDTO promotionDTO) {
        PromotionEntity promotionEntity = promotionRepository.findOneById(promotionDTO.getId());
        promotionEntity = promotionConverter.toEntity(promotionDTO, promotionEntity);
        return promotionConverter.toDTO(promotionRepository.save(promotionEntity));
    }

    @Override
    public PromotionDTO findOneById(Integer id) {
        return promotionConverter.toDTO(promotionRepository.findOneById(id));
    }

    @Override
    public List<PromotionDTO> findAll() {
        List<PromotionDTO> promotionDTOS = new ArrayList<>();
        for (PromotionEntity promotionEntity : promotionRepository.findAll()){
            promotionDTOS.add(promotionConverter.toDTO(promotionEntity));
        }
        return promotionDTOS;
    }

    @Override
    public List<PromotionDTO> findByValidityPeriodAfter() {
        List<PromotionDTO> promotionDTOS = new ArrayList<>();
        for (PromotionEntity promotionEntity : promotionRepository.findByValidityPeriodAfter(new Timestamp(System.currentTimeMillis()))){
            promotionDTOS.add(promotionConverter.toDTO(promotionEntity));
        }
        return promotionDTOS;
    }

    @Override
    public void delete(PromotionDTO promotionDTO) {
        for (Integer id: promotionDTO.getIds()){
            PromotionEntity promotionEntity = promotionRepository.findOneById(id);
            for (ProductEntity productEntity : promotionEntity.getProducts()) {
                productEntity.setPromotion(null);
                productRepository.save(productEntity);
            }

        }
        promotionRepository.deleteByIds(promotionDTO.getIds());
    }


}
