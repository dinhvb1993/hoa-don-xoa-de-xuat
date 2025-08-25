package com.simple_official_spring_template.converter;

import com.simple_official_spring_template.dto.*;
import com.simple_official_spring_template.entity.*;
import com.simple_official_spring_template.repository.*;
import com.simple_official_spring_template.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductConverter {

    @Autowired
    private ImageSliderItemConverter imageSliderItemConverter;

    @Autowired
    private IPromotionRepository promotionRepository;

    @Autowired
    private IProductCategoryRepository productCategoryRepository;

    @Autowired
    private IProductProductTypeRepository productProductTypeRepository;

    @Autowired
    private IProductAttributeGroupRepository productAttributeGroupRepository;

    @Autowired
    private PromotionConverter promotionConverter;

    @Autowired
    private CategoryConverter categoryConverter;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private ProductTypeConverter productTypeConverter;

    @Autowired
    private AttributeGroupConverter attributeGroupConverter;

    @Autowired
    private IAttributeGroupRepository attributeGroupRepository;
    //

    public ProductEntity toEntity(ProductDTO productDTO, ProductEntity productEntity){
        if (productDTO.getId() != null){
            productEntity.setId(productDTO.getId());
        }
        if (productDTO.getName() != null){
            productEntity.setName(productDTO.getName());
            // set naked name
            productEntity.setNakedName(StringUtil.unUnicodeAndRemoveSpace(productDTO.getName()," ", null));
        }
        if (productDTO.getSlug() != null){
            productEntity.setSlug(productDTO.getSlug());
        }
        if (productDTO.getIsHidden() != null){
            productEntity.setIsHidden(productDTO.getIsHidden());
        }
        if (productDTO.getPrice() != null){
            productEntity.setPrice(productDTO.getPrice());
        }
        if (productDTO.getOriginalPrice() != null){
            productEntity.setOriginalPrice(productDTO.getOriginalPrice());
        }
        if (productDTO.getQuantity() != null){
            productEntity.setQuantity(productDTO.getQuantity());
        }
        if (productDTO.getWarrantyPeriod() != null){
            productEntity.setWarrantyPeriod(productDTO.getWarrantyPeriod());
        }
        if (productDTO.getWeight() != null){
            productEntity.setWeight(productDTO.getWeight());
        }
        if (productDTO.getOrderNumber() != null){
            productEntity.setOrderNumber(productDTO.getOrderNumber());
        }
        if (productDTO.getRateNumber() != null){
            productEntity.setRateNumber(productDTO.getRateNumber());
        }
        if (productDTO.getAverageRate() != null){
            productEntity.setAverageRate(productDTO.getAverageRate());
        }
        if (productDTO.getPromotionDTO() != null){
            productEntity.setPromotion(promotionRepository.findOneById(productDTO.getPromotionDTO().getId()));
        }
        if (productDTO.getQuestionAndAnswer() != null){
            productEntity.setQuestionAndAnswer(productDTO.getQuestionAndAnswer());
        }
        if (productDTO.getDescription() != null){
            productEntity.setDescription(productDTO.getDescription());
        }
        if (productDTO.getContent() != null){
            productEntity.setContent(productDTO.getContent());
        }

        return productEntity;
    }

    public ProductDTO toDTO(ProductEntity productEntity){
        List<ImageSliderItemDTO> imageSliderItemDTOS = new ArrayList<>();
        if (productEntity.getImageSliderItems().size() > 0){
            for (ImageSliderItemEntity imageSliderItemEntity: productEntity.getImageSliderItems()){
                imageSliderItemDTOS.add(imageSliderItemConverter.toDTO(imageSliderItemEntity));
            }
        }


        // catgories
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for (ProductCategoryEntity productCategoryEntity: productCategoryRepository.findByProductId(productEntity.getId())){
            categoryDTOS.add(categoryConverter.toDTO(categoryRepository.findOneById(productCategoryEntity.getProductCategoryId().getCategoryId())));
        }

        // product types
        List<ProductTypeDTO> productTypeDTOS = new ArrayList<>();
        for (ProductProductTypeEntity productProductTypeEntity: productProductTypeRepository.findByProductId(productEntity.getId())){
            productTypeDTOS.add(productTypeConverter.toDTO(productProductTypeEntity.getProductType()));
        }

        // attribute groups
        // product types
        List<AttributeGroupDTO> attributeGroupDTOS = new ArrayList<>();
        for (ProductAttributeGroupEntity productAttributeGroupEntity: productAttributeGroupRepository.findByProductId(productEntity.getId())){
            attributeGroupDTOS.add(attributeGroupConverter.toDTO(attributeGroupRepository.findOneById(productAttributeGroupEntity.getProductAttributeGroupId().getAttributeGroupId())));
        }

        return new ProductDTO(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getSlug(),
                productEntity.getDescription(),
                productEntity.getContent(),
                productEntity.getOrderNumber(),
                productEntity.getCreationTime(),
                productEntity.getUpdateTime(),
                productEntity.getIsHidden(),
                categoryDTOS,
                // TODO clone with new category
                productTypeDTOS,
                // TODO end clone
                attributeGroupDTOS,
                productEntity.getPrice(),
                productEntity.getOriginalPrice(),
                productEntity.getQuantity(),
                productEntity.getWarrantyPeriod(),
                productEntity.getWeight(),
                productEntity.getRateNumber(),
                productEntity.getAverageRate(),
                productEntity.getPromotion() != null? promotionConverter.toDTO(productEntity.getPromotion()): null,
                productEntity.getQuestionAndAnswer(),
                productEntity.getImageSliderAvatarItem() != null? imageSliderItemConverter.toDTO(productEntity.getImageSliderAvatarItem()): null,
                imageSliderItemDTOS
        );
    }
}
