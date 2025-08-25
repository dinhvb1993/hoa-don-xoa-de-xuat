package com.simple_official_spring_template.api;

import com.simple_official_spring_template.dto.ProductCategoryDTO;
import com.simple_official_spring_template.entity.embeddedid.ProductCategoryId;
import com.simple_official_spring_template.service.IProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductCategoryAPI {
    @Autowired
    private IProductCategoryService productCategoryService;

    @GetMapping(value = "/api-product-category")
    public List<ProductCategoryDTO> getProductCategorys(
            @RequestParam(name = "productId", required = false) Integer productId,
            @RequestParam(name = "categoryId", required = false) Integer categoryId
    ) {
        if (productId != null || categoryId != null) {
            return productCategoryService.findByProductIdAndCategoryId(productId, categoryId);
        }
        else {
            return productCategoryService.findAll();
        }
    }

    @GetMapping(value = "/api-product-category/{userId}/{productId}")
    public ProductCategoryDTO getProductCategoryById(
            @PathVariable(name = "productId", required = true) Integer productId, @PathVariable(name = "categoryId", required = true) Integer categoryId) {
        return productCategoryService.findOneByProductCategoryId(new ProductCategoryId(productId, categoryId));
    }

    @PostMapping(value = "/api-product-category")
    public ProductCategoryDTO createProductCategory(@RequestBody ProductCategoryDTO productCategoryDTO) {
        return productCategoryService.save(productCategoryDTO);
    }

    @PutMapping(value = "/api-product-category")
    public ProductCategoryDTO updateProductCategory(@RequestBody ProductCategoryDTO productCategoryDTO) {
        return productCategoryService.update(productCategoryDTO);
    }

    @DeleteMapping(value = "/api-product-category")
    public void deleteProductCategorys(@RequestBody ProductCategoryDTO productCategoryDTO) {
        productCategoryService.delete(productCategoryDTO);
    }
}
