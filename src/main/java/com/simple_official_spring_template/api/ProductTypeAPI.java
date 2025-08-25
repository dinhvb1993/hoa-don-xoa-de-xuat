package com.simple_official_spring_template.api;

import com.simple_official_spring_template.dto.ProductTypeDTO;
import com.simple_official_spring_template.dto.others.ProductTypeGroupDTO;
import com.simple_official_spring_template.service.IProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductTypeAPI {
    @Autowired
    private IProductTypeService productTypeService;

    @GetMapping(value = "/api-product-type")
    public List<ProductTypeDTO> getCategories() {
        return productTypeService.findAll();
    }

    @GetMapping(value = "/api-product-type/{id}")
    public ProductTypeDTO getProductTypeById(@PathVariable(value = "id", required = true) Integer id) {
        return productTypeService.findOneById(id);
    }

    @GetMapping(value = "/api-product-type/get-by-inherit-product-type-id")
    public ProductTypeGroupDTO getInheritProductType(
            @RequestParam(value = "productTypeId", required = false) Integer productTypeId, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "order_by", required = false) String orderBy, @RequestParam(value = "order_type", required = false) String orderType
    ){
        return productTypeService.findInheritProductType(productTypeId, orderBy, orderType);
    }

    @PostMapping(value = "/api-product-type")
    public ProductTypeDTO createProductType(@RequestBody ProductTypeDTO productTypeDTO) {
        return productTypeService.save(productTypeDTO);
    }

    @PutMapping(value = "/api-product-type")
    public ProductTypeDTO updateProductType(@RequestBody ProductTypeDTO productTypeDTO) {
        return productTypeService.save(productTypeDTO);
    }

    @DeleteMapping(value = "api-product-type")
    public void deleteCategories(@RequestBody ProductTypeDTO productTypeDTO) {
        productTypeService.delete(productTypeDTO);
    }
}
