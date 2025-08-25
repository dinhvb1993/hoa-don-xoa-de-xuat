package com.simple_official_spring_template.api;

import com.simple_official_spring_template.dto.ProductRateDTO;
import com.simple_official_spring_template.entity.embeddedid.ProductRateId;
import com.simple_official_spring_template.service.IProductRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductRateAPI {
    @Autowired
    private IProductRateService productRateService;

    @GetMapping(value = "/api-product-rate")
    public List<ProductRateDTO> getProductRates(
            @RequestParam(name = "userId", required = false) String userId,
            @RequestParam(name = "productId", required = false) String productId
    ) {
        if (userId != null && productId != null) {
            return productRateService.findByUserIdAndProductId(userId, productId);
        }
        else {
            return productRateService.findAll();
        }
    }

    @GetMapping(value = "/api-product-rate/{userId}/{productId}/{productRate}")
    public ProductRateDTO getProductRateById(@PathVariable(name = "userId", required = true) String userId, @PathVariable(name = "productId", required = true) Integer productId, @PathVariable(name = "productRate", required = true) Float productRate) {
        return productRateService.findOneByProductRateId(new ProductRateId(userId, productId));
    }

    @PostMapping(value = "/api-product-rate")
    public ProductRateDTO createProductRate(@RequestBody ProductRateDTO productRateDTO) {
        return productRateService.save(productRateDTO);
    }

    @PutMapping(value = "/api-product-rate")
    public ProductRateDTO updateProductRate(@RequestBody ProductRateDTO productRateDTO) {
        return productRateService.update(productRateDTO);
    }

    @DeleteMapping(value = "/api-product-rate")
    public void deleteProductRates(@RequestBody ProductRateDTO productRateDTO) {
        productRateService.delete(productRateDTO);
    }
}
