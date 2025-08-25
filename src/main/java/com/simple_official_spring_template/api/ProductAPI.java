package com.simple_official_spring_template.api;

import com.simple_official_spring_template.dto.ProductDTO;
import com.simple_official_spring_template.dto.others.AttributeCategoryFilterDTO;
import com.simple_official_spring_template.dto.support_dto.ProductPageDTO;
import com.simple_official_spring_template.service.IProductService;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RestController
public class ProductAPI {
    @Autowired
    private IProductService productService;

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping(value = "/api-product/{id}")
    public ProductDTO getProductById(@PathVariable(value = "id", required = true) Integer id){
        return productService.findOneById(id);
    }

    @GetMapping(value = "/api-product")
    public ProductPageDTO getProducts(
            @RequestParam(value = "category_id", required = false) Integer categoryId, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "order_by", required = false) String orderBy, @RequestParam(value = "order_type", required = false) String orderType,
            // TODO clone with new category
            @RequestParam(value = "product_type_id", required = false) Integer productTypeId,
            @RequestParam(value = "attribute_category_id", required = false) Integer attributeCategoryId
            // TODO end clone
    ){
        if (page == null || limit == null){
            page = 1;
            limit = Integer.MAX_VALUE;
        }

        Pageable pageable;
        Sort sort;
        if (orderBy == null || orderType == null){
            pageable = PageRequest.of(page-1, limit);
        }
        else {
            if (orderType.equals("desc")){
                sort = Sort.by(orderBy).descending();
            }
            else {
                sort = Sort.by(orderBy).ascending();
            }

            pageable = PageRequest.of(page-1, limit, sort);
        }


        if (categoryId != null) {
            return productService.findByCategoryId(categoryId, pageable);
        }

        // TODO clone with new category
//        if (productTypeId != null) {
//            return productService.findByProductTypeId(categoryId, pageable);
//        }
        // TODO end clone
        if (attributeCategoryId != null) {
            return productService.findByAttributeGroupId(categoryId, pageable);
        }

        return productService.findAll(pageable);
    }

    @GetMapping(value = "/api-product/get-by-inherit-category-id")
    public ProductPageDTO getByInheritCategoryId(
            @RequestParam(value = "category_id", required = false) Integer categoryId, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "order_by", required = false) String orderBy, @RequestParam(value = "order_type", required = false) String orderType,
            // TODO clone with new category
            @RequestParam(value = "product_type_id", required = false) Integer productTypeId,
            // TODO end clone
            @RequestParam(value = "attribute_category_id", required = false) Integer attributeCategoryId,
            @RequestParam(value = "is_attribute_filter", required = false) Integer isAttributeFilter
    ){
        if (page == null || limit == null){
            page = 1;
            limit = Integer.MAX_VALUE;
        }

        Pageable pageable;

        Sort sort;
        if (orderBy == null || orderType == null){
            pageable = PageRequest.of(page-1, limit);
        }
        else {

            if (categoryId == null){
                String tempOrderBy = "";
                int i = 0;
                while (i<orderBy.length()){
                    if (orderBy.charAt(i) == '_'){
                        tempOrderBy += StringUtil.toUpperCase(orderBy.charAt(i+1));
                        i++;
                    }
                    else {
                        tempOrderBy += orderBy.charAt(i);
                    }
                    i++;
                }

                orderBy = tempOrderBy;
            }


            if (orderType.equals("desc")){
                sort = Sort.by(orderBy).descending();
            }
            else {
                sort = Sort.by(orderBy).ascending();
            }

            pageable = PageRequest.of(page-1, limit, sort);
        }


        // TODO clone with new category
//        if (productTypeId != null){
//            return productService.findByInheritProductTypeId(productTypeId, pageable);
//        }
        // TODO end clone
        if (attributeCategoryId != null){
            return productService.findByInheritAttributeGroupId(attributeCategoryId, pageable);
        }

        if (categoryId != null){
            return productService.findByInheritCategoryId(categoryId, pageable);
        }

        return productService.findAll(pageable);
    }

//    @GetMapping(value = "/api-product/count-by-inherit-category-id")
//    public Integer countByInheritCategoryId(
//            @RequestParam(value = "category_id", required = false) Integer categoryId,
//            @RequestParam(value = "product_type_id", required = false) Integer productTypeId,
//            @RequestParam(value = "attribute_category_id", required = false) Integer attributeCategoryId
//    ){
//
////        if (productTypeId != null){
////            return productService.countByInheritCategoryId(productTypeId);
////        }
////        if (attributeCategoryId != null){
////            return productService.findByInheritAttributeGroupId(attributeCategoryId);
////        }
//
//        return productService.countByInheritCategoryId(categoryId);
//    }

    @PostMapping(value = "/api-product/filter-by-attribute-group")
    public ProductPageDTO filterByAttributeGroupIds(@RequestBody AttributeCategoryFilterDTO attributeCategoryFilterDTO){
        if (attributeCategoryFilterDTO.getPage() == null || attributeCategoryFilterDTO.getLimit() == null){
            attributeCategoryFilterDTO.setPage(1);;
            attributeCategoryFilterDTO.setLimit(Integer.MAX_VALUE);
        }

        Pageable pageable;

        Sort sort;
        if (attributeCategoryFilterDTO.getOrderBy() == null || attributeCategoryFilterDTO.getOrderType() == null){
            pageable = PageRequest.of(attributeCategoryFilterDTO.getPage()-1, attributeCategoryFilterDTO.getLimit());
        }
        else {
            if (attributeCategoryFilterDTO.getOrderType().equals("desc")){
                sort = Sort.by(attributeCategoryFilterDTO.getOrderBy()).descending();
            }
            else {
                sort = Sort.by(attributeCategoryFilterDTO.getOrderBy()).ascending();
            }

            pageable = PageRequest.of(attributeCategoryFilterDTO.getPage()-1, attributeCategoryFilterDTO.getLimit(), sort);
        }

        return productService.filterByAttributeGroupIds(entityManager, attributeCategoryFilterDTO.getAttributeCategoryIds(), attributeCategoryFilterDTO.getCategoryId(), pageable);
    }

//    @PostMapping(value = "/api-product/count-filter-by-attribute-group")
//    public Integer countFilterByAttributeGroupIds(@RequestBody AttributeCategoryFilterDTO attributeCategoryFilterDTO){
//        return productService.countFilterByAttributeGroupIds(entityManager, attributeCategoryFilterDTO.getAttributeCategoryIds(), attributeCategoryFilterDTO.getCategoryId());
//    }


    @GetMapping(value = "/api-product/find-general-products")
    public List<ProductDTO> findGeneralProducts(
            @RequestParam(value = "categoryId", required = false) Integer categoryId,
            @RequestParam(value = "name") String name
    ){

        return productService.findGeneralProducts(categoryId, name);
    }


    @PostMapping(value = "/api-product")
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO){
        return productService.save(productDTO);
    }

    @PutMapping(value = "/api-product")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO){
        return productService.update(productDTO);
    }

    @DeleteMapping(value = "/api-product")
    public void deleteProducts(@RequestBody ProductDTO productDTO){
        productService.delete(productDTO);
    }

}
