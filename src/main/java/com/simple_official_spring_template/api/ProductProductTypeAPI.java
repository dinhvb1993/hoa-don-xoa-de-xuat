package com.simple_official_spring_template.api;

import com.simple_official_spring_template.dto.ProductProductTypeDTO;
import com.simple_official_spring_template.entity.embeddedid.ProductProductTypeId;
import com.simple_official_spring_template.service.IProductProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductProductTypeAPI {
    @Autowired
    private IProductProductTypeService productProductTypeService;

    @GetMapping(value = "/api-product-product-type")
    public List<ProductProductTypeDTO> getProductProductTypes(
            @RequestParam(name = "productId", required = false) Integer productId,
            @RequestParam(name = "productTypeId", required = false) Integer productTypeId
    ) {
        if (productId != null && productTypeId != null) {
            return productProductTypeService.findByProductIdAndProductTypeId(productId, productTypeId);
        }
        else {
            return productProductTypeService.findAll();
        }
    }

    @GetMapping(value = "/api-product-product-type/{userId}/{productId}")
    public ProductProductTypeDTO getProductProductTypeById(
            @PathVariable(name = "productId", required = true) Integer productId, @PathVariable(name = "productTypeId", required = true) Integer productTypeId) {
        return productProductTypeService.findOneByProductProductTypeId(new ProductProductTypeId(productId, productTypeId));
    }

    @PostMapping(value = "/api-product-product-type")
    public ProductProductTypeDTO createProductProductType(@RequestBody ProductProductTypeDTO productProductTypeDTO) {
        return productProductTypeService.save(productProductTypeDTO);
    }

    @PutMapping(value = "/api-product-product-type")
    public ProductProductTypeDTO updateProductProductType(@RequestBody ProductProductTypeDTO productProductTypeDTO) {
        return productProductTypeService.update(productProductTypeDTO);
    }

    @DeleteMapping(value = "/api-product-product-type")
    public void deleteProductProductTypes(@RequestBody ProductProductTypeDTO productProductTypeDTO) {
        productProductTypeService.delete(productProductTypeDTO);
    }
}
