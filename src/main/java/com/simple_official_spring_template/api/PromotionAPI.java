package com.simple_official_spring_template.api;

import com.simple_official_spring_template.dto.PromotionDTO;
import com.simple_official_spring_template.service.IPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PromotionAPI {
    @Autowired
    private IPromotionService promotionService;

    @GetMapping(value = "/api-promotion/{id}")
    public PromotionDTO getPromotionById(@PathVariable(value = "id", required = true) Integer id){
        return promotionService.findOneById(id);
    }

    @GetMapping(value = "/api-promotion")
    public List<PromotionDTO> getPromotions(@RequestParam(value = "isValid", required = false) Boolean isValid){
        if (isValid != null){
            return promotionService.findByValidityPeriodAfter();
        }
        else {
            return null;
        }
    }


    @PostMapping(value = "/api-promotion")
    public PromotionDTO createPromotion(@RequestBody PromotionDTO promotionDTO){
        return promotionService.save(promotionDTO);
    }

    @PutMapping(value = "/api-promotion")
    public PromotionDTO updatePromotion(@RequestBody PromotionDTO promotionDTO){
        return promotionService.update(promotionDTO);
    }

    @DeleteMapping(value = "/api-promotion")
    public void deletePromotions(@RequestBody PromotionDTO promotionDTO){
        promotionService.delete(promotionDTO);
    }
}
