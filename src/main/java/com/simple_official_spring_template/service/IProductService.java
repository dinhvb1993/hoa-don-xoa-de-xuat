package com.simple_official_spring_template.service;

import com.simple_official_spring_template.dto.ProductDTO;
import com.simple_official_spring_template.dto.support_dto.ProductPageDTO;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

public interface IProductService {
    ProductDTO save(ProductDTO productDTO);
    ProductDTO update(ProductDTO productDTO);
    ProductDTO findOneById(Integer id);
    List<ProductDTO> findAll();

    ProductPageDTO findAll(Pageable pageable);

    ProductPageDTO findByCategoryId(Integer categoryId, Pageable pageable);
    ProductPageDTO findByInheritCategoryId(Integer categoryId, Pageable pageable);

    Integer countByInheritCategoryId(Integer categoryId);

//    // TODO clone with new category
//    ProductPageDTO findByProductTypeId(Integer productTypeId, Pageable pageable);
//    ProductPageDTO findByInheritProductTypeId(Integer productTypeId, Pageable pageable);
//    // TODO end clone

    // attribute group
    ProductPageDTO findByAttributeGroupId(Integer attributeGroupId, Pageable pageable);
    ProductPageDTO findByInheritAttributeGroupId(Integer attributeGroupId, Pageable pageable);
    ProductPageDTO filterByAttributeGroupIds(EntityManager entityManager, List<Integer> attributeGroupIds, Integer categoryId, Pageable pageable);

    List<ProductDTO> findGeneralProducts(Integer categoryId, String name);


//    Integer countFilterByAttributeGroupIds(EntityManager entityManager, List<Integer> attributeGroupIds, Integer categoryId);


    void delete(ProductDTO productDTO);
}
