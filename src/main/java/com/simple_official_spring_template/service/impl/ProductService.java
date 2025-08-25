package com.simple_official_spring_template.service.impl;

import com.simple_official_spring_template.converter.ImageSliderItemConverter;
import com.simple_official_spring_template.converter.ProductConverter;
import com.simple_official_spring_template.converter.ProductPageConverter;
import com.simple_official_spring_template.dto.*;
import com.simple_official_spring_template.dto.support_dto.NumberBooleanDTO;
import com.simple_official_spring_template.dto.support_dto.ProductPageDTO;
import com.simple_official_spring_template.entity.*;
import com.simple_official_spring_template.entity.embeddedid.ProductAttributeGroupId;
import com.simple_official_spring_template.entity.embeddedid.ProductCategoryId;
import com.simple_official_spring_template.entity.embeddedid.ProductProductTypeId;
import com.simple_official_spring_template.repository.*;
import com.simple_official_spring_template.service.IProductService;
import com.simple_official_spring_template.utils.StringUtil;
import com.simple_official_spring_template.utils.nativequery.GeneralNativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IImageSliderItemRepository imageSliderItemRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private IAttributeGroupRepository attributeGroupRepository;

    @Autowired
    private IProductTypeRepository productTypeRepository;

    @Autowired 
    private IProductCategoryRepository productCategoryRepository;
    
    @Autowired
    private IProductProductTypeRepository productProductTypeRepository;

    @Autowired
    private IProductAttributeGroupRepository productAttributeGroupRepository;

    



    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private ImageSliderItemConverter imageSliderItemConverter;


    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private ProductTypeService productTypeService;
    
    @Autowired
    private AttributeGroupService attributeGroupService;

    @Autowired
    private ProductPageConverter productPageConverter;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private GeneralNativeQuery generalNativeQuery;
    



    @Override
    public ProductDTO save(ProductDTO productDTO) {

        ProductEntity productEntity = productConverter.toEntity(productDTO, new ProductEntity());

        // set defalt value
        if (productDTO.getPrice() == null){
            productDTO.setPrice(0.0);
        }
        if (productDTO.getQuantity() == null){
            productDTO.setQuantity(1000);
        }
        if (productDTO.getWeight() == null){
            productDTO.setWeight(0.0);
        }

        if (productDTO.getSlug() == null){
            String slug = StringUtil.unUnicodeAndRemoveSpace(productDTO.getName(), "-", "-_");
            int counter = 1;
            String addition = "";
            while (productRepository.findOneBySlug(slug + addition) != null){
                addition = "_" + counter++;
            }

            productEntity.setSlug(slug + addition);
        }

        // set Avatar
        if (productDTO.getImageSliderAvatarItemDTO() != null){
            ImageSliderItemEntity imageSliderItemEntity = imageSliderItemRepository.findOneById(productDTO.getImageSliderAvatarItemDTO().getId());
            if (imageSliderItemEntity == null){
                imageSliderItemEntity = imageSliderItemRepository.save(imageSliderItemConverter.toEntity(productDTO.getImageSliderAvatarItemDTO(), new ImageSliderItemEntity()));
                imageSliderItemRepository.save(imageSliderItemEntity);
            }
            productEntity.setImageSliderAvatarItem(imageSliderItemEntity);
        }

        // set image slider items
        if (productDTO.getImageSliderItemDTOS() != null){
            List<ImageSliderItemEntity> imageSliderItemEntities = new ArrayList<>();
            for (ImageSliderItemDTO imageSliderItemDTO: productDTO.getImageSliderItemDTOS()){
                ImageSliderItemEntity imageSliderItemEntity = imageSliderItemRepository.findOneById(imageSliderItemDTO.getId());
                if (imageSliderItemEntity == null){
                    imageSliderItemEntity = imageSliderItemRepository.save(imageSliderItemConverter.toEntity(productDTO.getImageSliderAvatarItemDTO(), new ImageSliderItemEntity()));
                    imageSliderItemRepository.save(imageSliderItemEntity);
                }

                imageSliderItemEntities.add(imageSliderItemEntity);
            }
            productEntity.setImageSliderItems(imageSliderItemEntities);
        }

        productRepository.save(productEntity);

        


        // set categories
        if (productDTO.getChangedCatList() != null){
            for (NumberBooleanDTO numberBooleanDTO: productDTO.getChangedCatList()){
                Integer categoryId = numberBooleanDTO.getId();
                boolean checked = numberBooleanDTO.getBooleanValue();
                ProductCategoryEntity productCategoryEntity = productCategoryRepository.findOneByProductCategoryId(new ProductCategoryId(productEntity.getId(), categoryId));
                if (productCategoryEntity != null){
                    if (!checked){
                        productCategoryRepository.delete(productCategoryEntity);
                    }
                }
                else if (checked){
                    productCategoryEntity = new ProductCategoryEntity();
                    productCategoryEntity.setProductCategoryId(new ProductCategoryId(productEntity.getId(), categoryId));
                    productCategoryRepository.save(productCategoryEntity);
                }
            }
        }



//        // set product types
//
//        if (productDTO.getProductTypeDTOS() != null){
//            List<ProductTypeEntity> productTypeEntities = new ArrayList<>();
//            for (ProductTypeDTO productTypeDTO: productDTO.getProductTypeDTOS()){
//                productTypeEntities.add(productTypeRepository.findOneById(productTypeDTO.getId()));
//
//                ProductProductTypeEntity productProductTypeEntity = new ProductProductTypeEntity();
//                productProductTypeEntity.setProductProductTypeId(new ProductProductTypeId(productEntity.getId(), productTypeDTO.getId()));
//                productProductTypeEntity.setProduct(productEntity);
//                productProductTypeEntity.setProductType(productTypeRepository.findOneById(productTypeDTO.getId()));
//                productProductTypeRepository.save(productProductTypeEntity);
//            }
//        }



        // set attribute groups
        if (productDTO.getChangedAttributeGroupList() != null){
            for (NumberBooleanDTO numberBooleanDTO: productDTO.getChangedAttributeGroupList()){
                Integer attributeGroupId = numberBooleanDTO.getId();
                boolean checked = numberBooleanDTO.getBooleanValue();
                ProductAttributeGroupEntity productAttributeGroupEntity = productAttributeGroupRepository.findOneByProductAttributeGroupId(new ProductAttributeGroupId(productEntity.getId(), attributeGroupId));
                if (productAttributeGroupEntity != null){
                    if (!checked){
                        productAttributeGroupRepository.delete(productAttributeGroupEntity);
                    }
                }
                else if (checked){
                    productAttributeGroupEntity = new ProductAttributeGroupEntity();
                    productAttributeGroupEntity.setProductAttributeGroupId(new ProductAttributeGroupId(productEntity.getId(), attributeGroupId));
                    productAttributeGroupRepository.save(productAttributeGroupEntity);
                }
            }
        }



        return productConverter.toDTO(productEntity);
    }

    @Override
    public ProductDTO update(ProductDTO productDTO) {
        ProductEntity oldProductEntity = productRepository.findOneById(productDTO.getId());

        // check slug
        String addition = "";
        if (productDTO.getSlug() != null && !productDTO.getSlug().equals(oldProductEntity.getSlug())){
            int counter = 1;
            while (productRepository.findOneBySlug(productDTO.getSlug() + addition) != null){
                addition = "_" + counter++;
            }
        }
        productDTO.setSlug(productDTO.getSlug() + addition);

        ProductEntity productEntity = productConverter.toEntity(productDTO, oldProductEntity);

        // set Avatar
        if (productDTO.getImageSliderAvatarItemDTO() != null){
            ImageSliderItemEntity imageSliderItemEntity = imageSliderItemRepository.findOneById(productDTO.getImageSliderAvatarItemDTO().getId());
            if (imageSliderItemEntity == null){
                imageSliderItemEntity = imageSliderItemRepository.save(imageSliderItemConverter.toEntity(productDTO.getImageSliderAvatarItemDTO(), new ImageSliderItemEntity()));
                imageSliderItemRepository.save(imageSliderItemEntity);
            }
            productEntity.setImageSliderAvatarItem(imageSliderItemEntity);
        }

        // set image slider items
        if (productDTO.getImageSliderItemDTOS() != null){
            List<ImageSliderItemEntity> imageSliderItemEntities = new ArrayList<>();
            for (ImageSliderItemDTO imageSliderItemDTO: productDTO.getImageSliderItemDTOS()){
                ImageSliderItemEntity imageSliderItemEntity = imageSliderItemRepository.findOneById(imageSliderItemDTO.getId());
                if (imageSliderItemEntity == null){
                    imageSliderItemEntity = imageSliderItemRepository.save(imageSliderItemConverter.toEntity(productDTO.getImageSliderAvatarItemDTO(), new ImageSliderItemEntity()));
                    imageSliderItemRepository.save(imageSliderItemEntity);
                }

                imageSliderItemEntities.add(imageSliderItemEntity);
            }
            productEntity.setImageSliderItems(imageSliderItemEntities);
        }



        // change categories
        if (productDTO.getChangedCatList() != null){
            for (NumberBooleanDTO numberBooleanDTO: productDTO.getChangedCatList()){
                Integer categoryId = numberBooleanDTO.getId();
                boolean checked = numberBooleanDTO.getBooleanValue();
                ProductCategoryEntity productCategoryEntity = productCategoryRepository.findOneByProductCategoryId(new ProductCategoryId(productEntity.getId(), categoryId));
                if (productCategoryEntity != null){
                    if (!checked){
                        productCategoryRepository.delete(productCategoryEntity);
                    }
                }
                else if (checked){
                    productCategoryEntity = new ProductCategoryEntity();
                    productCategoryEntity.setProductCategoryId(new ProductCategoryId(productEntity.getId(), categoryId));
                    productCategoryRepository.save(productCategoryEntity);
                }
            }
        }


        // set attribute groups
        if (productDTO.getChangedAttributeGroupList() != null){
            for (NumberBooleanDTO numberBooleanDTO: productDTO.getChangedAttributeGroupList()){
                Integer attributeGroupId = numberBooleanDTO.getId();
                boolean checked = numberBooleanDTO.getBooleanValue();
                ProductAttributeGroupEntity productAttributeGroupEntity = productAttributeGroupRepository.findOneByProductAttributeGroupId(new ProductAttributeGroupId(productEntity.getId(), attributeGroupId));
                if (productAttributeGroupEntity != null){
                    if (!checked){
                        productAttributeGroupRepository.delete(productAttributeGroupEntity);
                    }
                }
                else if (checked){
                    productAttributeGroupEntity = new ProductAttributeGroupEntity();
                    productAttributeGroupEntity.setProductAttributeGroupId(new ProductAttributeGroupId(productEntity.getId(), attributeGroupId));
                    productAttributeGroupRepository.save(productAttributeGroupEntity);
                }
            }
        }


        productEntity = productRepository.save(productEntity);
        return productConverter.toDTO(productEntity);
    }

    @Override
    public ProductDTO findOneById(Integer id) {
        return productConverter.toDTO(productRepository.findOneById(id));
    }

    @Override
    public List<ProductDTO> findAll() {
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (ProductEntity productEntity : productRepository.findAll()){
            productDTOS.add(productConverter.toDTO(productEntity));
        }
        return productDTOS;
    }

    @Override
    public ProductPageDTO findAll(Pageable pageable) {
        Page<ProductEntity> productEntityPage = productRepository.findAll(pageable);
        return productPageConverter.toDTO(productEntityPage);

    }


    // CATEGORY

    @Override
    public ProductPageDTO findByCategoryId(Integer categoryId, Pageable pageable) {

        ProductPageDTO productDTOPage = new ProductPageDTO();

        Page<ProductCategoryEntity> productCategoryEntityPage = productCategoryRepository.findByCategoryId(categoryId, pageable);

        List<ProductDTO> productDTOList = new ArrayList<>();
        for (ProductCategoryEntity productCategoryEntity: productCategoryEntityPage.getContent()){
            productDTOList.add(productConverter.toDTO(productCategoryEntity.getProduct()));
        }

        productDTOPage.setProductDTOList(productDTOList);
        productDTOPage.setTotalElements(productCategoryEntityPage.getTotalElements());
        productDTOPage.setTotalPages(productCategoryEntityPage.getTotalPages());

        return productDTOPage;

    }

    @Override
    public ProductPageDTO findByInheritCategoryId(Integer categoryId, Pageable pageable) {

        Page<ProductEntity> productEntityPage = null;

        List<ProductDTO> productDTOS = new ArrayList<>();
        List<Integer> categoryIds = categoryService.findInheritCategory(categoryId, null, null).getAllChildCategoryIds();
        categoryIds.add(categoryId);
//        List<ProductEntity> productEntities;
        if (categoryId != null){
            List<Integer> ids = productCategoryRepository.findByCategoryIds(categoryIds);
//            productEntities = productRepository.findByIds(ids, pageable).getContent();

            productEntityPage = productRepository.findByIds(ids, pageable);
        }
        else {
//            productEntities = productRepository.findAll(pageable).getContent();

            productEntityPage = productRepository.findAll(pageable);
        }
//        for (ProductEntity productEntity : productEntities) {
//            productDTOS.add(productConverter.toDTO(productEntity));
//        }
//        return productDTOS;


        return productPageConverter.toDTO(productEntityPage);
    }

    @Override
    public Integer countByInheritCategoryId(Integer categoryId) {
        List<Integer> categoryIds = categoryService.findInheritCategory(categoryId, null, null).getAllChildCategoryIds();
        categoryIds.add(categoryId);
        return productCategoryRepository.countByCategoryIds(categoryIds);
    }


    // PRODUCT TYPES
//    @Override
//    public ProductPageDTO findByProductTypeId(Integer productTypeId, Pageable pageable) {
//
//
//        Page<ProductEntity> productEntityPage = productRepository.findByProductTypeId(productTypeId, pageable);
//        return productPageConverter.toDTO(productEntityPage);
//
////        List<ProductDTO> productDTOS = new ArrayList<>();
////        List<ProductProductTypeEntity> productProductTypeEntities = productProductTypeRepository.findByProductTypeId(productTypeId);
////        for (ProductProductTypeEntity productProductTypeEntity : productProductTypeEntities) {
////            productDTOS.add(productConverter.toDTO(productProductTypeEntity.getProduct()));
////        }
////        return productDTOS;
//    }

//    @Override
//    public List<ProductDTO> findByInheritProductTypeId(Integer productTypeId, Pageable pageable) {
//        List<ProductDTO> productDTOS = new ArrayList<>();
//        List<Integer> productTypeIds = productTypeService.findInheritProductType(productTypeId, null, null).getAllChildProductTypeIds();
//        productTypeIds.add(productTypeId);
//        List<ProductEntity> productEntities;
//        if (productTypeId != null){
//            List<Integer> ids = productProductTypeRepository.findByProductTypeIds(productTypeIds);
//            productEntities = productRepository.findByIds(ids, pageable).getContent();
//        }
//        else {
//            productEntities = productRepository.findAll(pageable).getContent();
//        }
//        for (ProductEntity productEntity : productEntities) {
//            productDTOS.add(productConverter.toDTO(productEntity));
//        }
//        return productDTOS;
//    }



    // ATTRIBUTE GROUPS
    @Override
    public ProductPageDTO findByAttributeGroupId(Integer attributeGroupId, Pageable pageable) {

        ProductPageDTO productDTOPage = new ProductPageDTO();

        Page<ProductAttributeGroupEntity> productAttributeGroupEntityPage = productAttributeGroupRepository.findByAttributeGroupId(attributeGroupId, pageable);

        List<ProductDTO> productDTOList = new ArrayList<>();
        for (ProductAttributeGroupEntity productAttributeGroupEntity: productAttributeGroupEntityPage.getContent()){
            productDTOList.add(productConverter.toDTO(productAttributeGroupEntity.getProduct()));
        }

        productDTOPage.setProductDTOList(productDTOList);
        productDTOPage.setTotalElements(productAttributeGroupEntityPage.getTotalElements());
        productDTOPage.setTotalPages(productAttributeGroupEntityPage.getTotalPages());

        return productDTOPage;


//        List<ProductDTO> productDTOS = new ArrayList<>();
//        List<ProductAttributeGroupEntity> productAttributeGroupEntities = productAttributeGroupRepository.findByAttributeGroupId(attributeGroupId);
//        for (ProductAttributeGroupEntity productAttributeGroupEntity : productAttributeGroupEntities) {
//            productDTOS.add(productConverter.toDTO(productAttributeGroupEntity.getProduct()));
//        }
//        return productDTOS;
    }

    @Override
    public ProductPageDTO findByInheritAttributeGroupId(Integer attributeGroupId, Pageable pageable) {

        Page<ProductEntity> productEntityPage = null;

        List<ProductDTO> productDTOS = new ArrayList<>();
        List<Integer> categoryIds = attributeGroupService.findInheritAttributeGroup(attributeGroupId, null, null).getAllChildAttributeGroupIds();
        categoryIds.add(attributeGroupId);
//        List<ProductEntity> productEntities;
        if (attributeGroupId != null){
            List<Integer> ids = productAttributeGroupRepository.findByAttributeGroupIds(categoryIds);
//            productEntities = productRepository.findByIds(ids, pageable).getContent();

            productEntityPage = productRepository.findByIds(ids, pageable);
        }
        else {
//            productEntities = productRepository.findAll(pageable).getContent();

            productEntityPage = productRepository.findAll(pageable);
        }
//        for (ProductEntity productEntity : productEntities) {
//            productDTOS.add(productConverter.toDTO(productEntity));
//        }
//        return productDTOS;


        return productPageConverter.toDTO(productEntityPage);



//        List<ProductDTO> productDTOS = new ArrayList<>();
//        List<Integer> attributeGroupIds = attributeGroupService.findInheritAttributeGroup(attributeGroupId, null, null).getAllChildAttributeGroupIds();
//        attributeGroupIds.add(attributeGroupId);
//        List<ProductEntity> productEntities = new ArrayList<>();
//        if (attributeGroupId != null){
//            List<Integer> ids = productAttributeGroupRepository.findByAttributeGroupIds(attributeGroupIds);
//            productEntities = productRepository.findByIds(ids, pageable).getContent();
//        }
//        else {
//            productEntities = productRepository.findAll(pageable).getContent();
//        }
//        for (ProductEntity productEntity : productEntities) {
//            productDTOS.add(productConverter.toDTO(productEntity));
//        }
//        return productDTOS;
    }

    @Override
    public ProductPageDTO filterByAttributeGroupIds(EntityManager entityManager, List<Integer> attributeGroupIds, Integer categoryId, Pageable pageable) {

        List<Integer> categoryIds = null;

        if (categoryId != null){
            categoryIds = categoryService.findInheritCategory(categoryId, null, null).getAllChildCategoryIds();
            categoryIds.add(categoryId);
        }

//        List<Integer> totalAttributeGroupIds = new ArrayList<>();
//        for (Integer attributeGroupId: attributeGroupIds){
//            List<Integer> attIds = attributeGroupService.findInheritAttributeGroup(attributeGroupId, null, null).getAllChildAttributeGroupIds();
//            attIds.add(attributeGroupId);
//
//            for (Integer attId: attIds){
//                if (totalAttributeGroupIds.contains(attId) == false){
//                    totalAttributeGroupIds.add(attId);
//                }
//            }
//        }




        List<ProductDTO> productDTOS = new ArrayList<>();

        List<Integer> ids = GeneralNativeQuery.filterProductIdsByAttributeGroupIds(entityManager, attributeGroupIds, categoryIds, pageable.getPageSize(), pageable.getPageSize()*pageable.getPageNumber());


        Page<ProductEntity> productEntityPage = productRepository.findByIds(ids, pageable);

        return productPageConverter.toDTO(productEntityPage);
    }

    @Override
    public List<ProductDTO> findGeneralProducts(Integer categoryId, String name) {
        List<ProductDTO> productDTOList = new ArrayList<>();

        List<Object[]> result = generalNativeQuery.findGeneralProducts(this.entityManager, categoryId, name);

        for (Object[] o: result){
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId((Integer) o[0]);
            productDTO.setName((String) o[1]);

            productDTOList.add(productDTO);
        }

        return productDTOList;
    }

//    @Override
//    public Integer countFilterByAttributeGroupIds(EntityManager entityManager, List<Integer> attributeGroupIds, Integer categoryId) {
//        List<Integer> categoryIds = null;
//
//        if (categoryId != null){
//            categoryIds = categoryService.findInheritCategory(categoryId, null, null).getAllChildCategoryIds();
//            categoryIds.add(categoryId);
//        }
//
//        List<Integer> ids = GeneralNativeQuery.filterProductIdsByAttributeGroupIds(entityManager, attributeGroupIds, categoryIds, Integer.MAX_VALUE, 0);
//        return ids.size();
//    }


    @Override
    public void delete(ProductDTO productDTO) {
        productRepository.deleteByIds(productDTO.getIds());
    }

}
