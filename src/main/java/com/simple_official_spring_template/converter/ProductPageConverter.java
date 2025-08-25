package com.simple_official_spring_template.converter;

import com.simple_official_spring_template.dto.ProductDTO;
import com.simple_official_spring_template.dto.support_dto.ProductPageDTO;
import com.simple_official_spring_template.entity.ProductEntity;
import com.simple_official_spring_template.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductPageConverter {

    @Autowired
    private ProductConverter productConverter;
    //



    public ProductPageDTO toDTO(Page<ProductEntity> productEntityPage){

        List<ProductDTO> productDTOList = new ArrayList<>();

        for (ProductEntity productEntity: productEntityPage.getContent()){
            productDTOList.add(productConverter.toDTO(productEntity));
        }




        return new ProductPageDTO(
                productDTOList,
                productEntityPage.getTotalElements(),
                productEntityPage.getTotalPages()
        );
    }
}
