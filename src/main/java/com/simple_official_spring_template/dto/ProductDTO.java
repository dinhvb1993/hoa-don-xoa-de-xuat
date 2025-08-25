package com.simple_official_spring_template.dto;

import com.simple_official_spring_template.dto.support_dto.NumberBooleanDTO;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class ProductDTO extends GenericPostDTO{
    private List<CategoryDTO> categoryDTOS;
    private List<com.simple_official_spring_template.dto.ProductTypeDTO> productTypeDTOS;
    private List<com.simple_official_spring_template.dto.AttributeGroupDTO> attributeGroupDTOS;
    private Double price;
    private Double originalPrice;
    private Integer quantity;
    private Integer warrantyPeriod;
    private Double weight;
    private Integer rateNumber;
    private Double averageRate;
    private PromotionDTO promotionDTO;
    private String questionAndAnswer;
    private ImageSliderItemDTO imageSliderAvatarItemDTO;
    private List<ImageSliderItemDTO> imageSliderItemDTOS;

    private List<NumberBooleanDTO> changedCatList;
    private List<NumberBooleanDTO> changedAttributeGroupList;


    public ProductDTO() {
    }

    public ProductDTO(
            Integer id, String name, String slug, String description, String content, Integer orderNumber, Timestamp creationTime, Timestamp updateTime,
            Boolean isHidden, List<CategoryDTO> categoryDTOS, List<com.simple_official_spring_template.dto.ProductTypeDTO> productTypeDTOS, List<AttributeGroupDTO> attributeGroupDTOS, Double price, Double originalPrice, Integer quantity,
            Integer warrantyPeriod, Double weight, Integer rateNumber, Double averageRate, PromotionDTO promotionDTO, String questionAndAnswer,
            ImageSliderItemDTO imageSliderAvatarItemDTO, List<ImageSliderItemDTO> imageSliderItemDTOS) {
        super(id, name, slug, description, content, orderNumber, creationTime, updateTime, isHidden);
        this.categoryDTOS = categoryDTOS;
        // TODO clone with new category
        this.productTypeDTOS = productTypeDTOS;
        // TODO end clone
        this.attributeGroupDTOS = attributeGroupDTOS;
        //
        this.price = price;
        this.originalPrice = originalPrice;
        this.quantity = quantity;
        this.warrantyPeriod = warrantyPeriod;
        this.weight = weight;
        this.rateNumber = rateNumber;
        this.averageRate = averageRate;
        this.promotionDTO = promotionDTO;
        this.questionAndAnswer = questionAndAnswer;
        this.imageSliderAvatarItemDTO = imageSliderAvatarItemDTO;
        this.imageSliderItemDTOS = imageSliderItemDTOS;
    }
}
