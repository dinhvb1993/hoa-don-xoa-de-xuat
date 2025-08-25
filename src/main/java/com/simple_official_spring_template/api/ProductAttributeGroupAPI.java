package com.simple_official_spring_template.api;

import com.simple_official_spring_template.dto.ProductAttributeGroupDTO;
import com.simple_official_spring_template.entity.embeddedid.ProductAttributeGroupId;
import com.simple_official_spring_template.service.IProductAttributeGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductAttributeGroupAPI {
    @Autowired
    private IProductAttributeGroupService productAttributeGroupService;

    @GetMapping(value = "/api-product-attribute-group")
    public List<ProductAttributeGroupDTO> getProductAttributeGroups(
            @RequestParam(name = "productId", required = false) Integer productId,
            @RequestParam(name = "attributeGroupId", required = false) Integer attributeGroupId
    ) {
        if (productId != null || attributeGroupId != null) {
            return productAttributeGroupService.findByProductIdAndAttributeGroupId(productId, attributeGroupId);
        }
        else {
            return productAttributeGroupService.findAll();
        }
    }

    @GetMapping(value = "/api-product-attribute-group/{userId}/{productId}")
    public ProductAttributeGroupDTO getProductAttributeGroupById(
            @PathVariable(name = "productId", required = true) Integer productId, @PathVariable(name = "attributeGroupId", required = true) Integer attributeGroupId) {
        return productAttributeGroupService.findOneByProductAttributeGroupId(new ProductAttributeGroupId(productId, attributeGroupId));
    }

    @PostMapping(value = "/api-product-attribute-group")
    public ProductAttributeGroupDTO createProductAttributeGroup(@RequestBody ProductAttributeGroupDTO productAttributeGroupDTO) {
        return productAttributeGroupService.save(productAttributeGroupDTO);
    }

    @PutMapping(value = "/api-product-attribute-group")
    public ProductAttributeGroupDTO updateProductAttributeGroup(@RequestBody ProductAttributeGroupDTO productAttributeGroupDTO) {
        return productAttributeGroupService.update(productAttributeGroupDTO);
    }

    @DeleteMapping(value = "/api-product-attribute-group")
    public void deleteProductAttributeGroups(@RequestBody ProductAttributeGroupDTO productAttributeGroupDTO) {
        productAttributeGroupService.delete(productAttributeGroupDTO);
    }
}
