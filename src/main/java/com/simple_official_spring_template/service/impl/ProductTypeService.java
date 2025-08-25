package com.simple_official_spring_template.service.impl;

import com.simple_official_spring_template.converter.ImageSliderItemConverter;
import com.simple_official_spring_template.converter.ProductTypeConverter;
import com.simple_official_spring_template.dto.ProductTypeDTO;
import com.simple_official_spring_template.dto.others.ProductTypeGroupDTO;
import com.simple_official_spring_template.entity.ImageSliderItemEntity;
import com.simple_official_spring_template.entity.ProductTypeEntity;
import com.simple_official_spring_template.repository.IImageSliderItemRepository;
import com.simple_official_spring_template.repository.IProductTypeRepository;
import com.simple_official_spring_template.service.IProductTypeService;
import com.simple_official_spring_template.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProductTypeService implements IProductTypeService {
    @Autowired
    private IProductTypeRepository productTypeRepository;

    @Autowired
    private IImageSliderItemRepository imageSliderItemRepository;

    @Autowired
    private ProductTypeConverter productTypeConverter;

    @Autowired
    private ImageSliderItemConverter imageSliderItemConverter;

    @Override
    public List<ProductTypeDTO> findAll() {
        List<ProductTypeDTO> productTypeDTOS = new ArrayList<>();

        List<ProductTypeEntity> productTypeEntities = productTypeRepository.findAll();
        for (ProductTypeEntity productTypeEntity: productTypeEntities) {
            productTypeDTOS.add(productTypeConverter.toDTO(productTypeEntity));
        }

        return productTypeDTOS;
    }

    @Override
    public ProductTypeDTO findOneById(Integer id) {
        ProductTypeEntity productTypeEntity = productTypeRepository.findOneById(id);
        return productTypeConverter.toDTO(productTypeEntity);
    }

    @Override
    public ProductTypeDTO save(ProductTypeDTO productTypeDTO) {
        if (productTypeDTO.getImageSliderAvatarItemDTO() != null){
            ImageSliderItemEntity imageSliderItemEntity = imageSliderItemRepository.save(imageSliderItemConverter.toEntity(productTypeDTO.getImageSliderAvatarItemDTO(), new ImageSliderItemEntity()));
            productTypeDTO.setImageSliderAvatarItemDTO(imageSliderItemConverter.toDTO(imageSliderItemEntity));
        }
        ProductTypeEntity productTypeEntity = productTypeConverter.toEntity(productTypeDTO, new ProductTypeEntity());

        if (productTypeDTO.getSlug() == null){
            String slug = StringUtil.unUnicodeAndRemoveSpace(productTypeDTO.getName(), "-", "-_");
            int counter = 1;
            String addition = "";
            while (productTypeRepository.findOneBySlug(slug + addition) != null){
                addition = "_" + counter++;
            }

            productTypeEntity.setSlug(slug + addition);
        }



        productTypeEntity = productTypeRepository.save(productTypeEntity);
        return productTypeConverter.toDTO(productTypeEntity);
    }

    @Override
    public ProductTypeDTO update(ProductTypeDTO productTypeDTO) {
        ProductTypeEntity productTypeEntity = productTypeRepository.findOneById(productTypeDTO.getId());
        // check slug
        String addition = "";
        if (productTypeDTO.getSlug() != null && !productTypeDTO.getSlug().equals(productTypeEntity.getSlug())){
            int counter = 1;
            while (productTypeRepository.findOneBySlug(productTypeDTO.getSlug() + addition) != null){
                addition = "_" + counter++;
            }
        }
        productTypeDTO.setSlug(productTypeDTO.getSlug() + addition);

        productTypeEntity = productTypeConverter.toEntity(productTypeDTO, productTypeEntity);
        productTypeEntity = productTypeRepository.save(productTypeEntity);
        return productTypeConverter.toDTO(productTypeEntity);
    }

    @Override
    public void delete(ProductTypeDTO productTypeDTO) {
        productTypeRepository.deleteByIds(productTypeDTO.getIds());
    }

    @Override
    public ProductTypeGroupDTO findInheritProductType(Integer productTypeId, String orderBy, String orderType){
        ProductTypeEntity oldProductTypeEntity = productTypeRepository.findOneById(productTypeId);
        ProductTypeDTO productTypeDTO;
        if (oldProductTypeEntity == null){
            productTypeDTO = new ProductTypeDTO();
        }
        else {
            productTypeDTO = productTypeConverter.toDTO(productTypeRepository.findOneById(productTypeId));
        }

        ProductTypeGroupDTO productTypeGroupDTO = new ProductTypeGroupDTO(productTypeDTO);
        productTypeGroupDTO.setCompareFeild(orderBy);
        List<Integer> tmpAllChildProductTypeIds = new ArrayList<>();
        loopCat(productTypeId, productTypeGroupDTO, tmpAllChildProductTypeIds);

        productTypeGroupDTO.setAllChildProductTypeIds(tmpAllChildProductTypeIds);

        readLoopCat(productTypeGroupDTO, orderType);


//        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, Sort.by("order_number").descending());
//        List<Integer> ids = new ArrayList<>();
//        ids.add(2);
//        ids.add(1);
//        List<ProductTypeEntity> productTypeEntities = productTypeRepository.findByProductTypeIds(ids, pageable).getContent();
//        for (ProductTypeEntity productTypeEntity: productTypeEntities){
//            System.out.println(productTypeEntity.getName());
//        }

        return productTypeGroupDTO;
    }

    public void loopCat(Integer parentProductTypeId, ProductTypeGroupDTO productTypeGroupDTO, List<Integer> tmpAllChildProductTypeIds){
        ProductTypeEntity oldProductType = productTypeRepository.findOneById(parentProductTypeId);
        productTypeGroupDTO.getProductTypeDTO().setCompareFeild(productTypeGroupDTO.getCompareFeild());


        List<ProductTypeEntity> productTypeEntities = productTypeRepository.findByParent(oldProductType);
        for(ProductTypeEntity productTypeEntity: productTypeEntities){
            productTypeGroupDTO.getChildProductTypeIds().add(productTypeEntity.getId());

            if (!tmpAllChildProductTypeIds.contains(productTypeEntity.getId())){
                tmpAllChildProductTypeIds.add(productTypeEntity.getId());
            }

            ProductTypeDTO newProductTypeDTO = productTypeConverter.toDTO(productTypeEntity);
//            newProductTypeDTO.setCompareFeild(productTypeGroupDTO.getCompareFeild());
            ProductTypeGroupDTO childProductTypeGroupDTO = new ProductTypeGroupDTO(newProductTypeDTO);
            childProductTypeGroupDTO.setCompareFeild(productTypeGroupDTO.getCompareFeild());


            loopCat(productTypeEntity.getId(), childProductTypeGroupDTO, tmpAllChildProductTypeIds);

            for (Integer catId: childProductTypeGroupDTO.getChildProductTypeIds()){
                if (!tmpAllChildProductTypeIds.contains(catId)){
                    tmpAllChildProductTypeIds.add(catId);
                }
            }

            productTypeGroupDTO.getChildProductTypeGroupDTO().add(childProductTypeGroupDTO);
        }
    }

    public void readLoopCat(ProductTypeGroupDTO productTypeGroupDTO, String orderType){
        if (orderType != null && orderType.equals("desc")){
            Collections.sort(productTypeGroupDTO.getChildProductTypeGroupDTO(), Collections.reverseOrder());
        }
        else {
            Collections.sort(productTypeGroupDTO.getChildProductTypeGroupDTO());
        }

        for (ProductTypeGroupDTO productTypeGroupDTO1: productTypeGroupDTO.getChildProductTypeGroupDTO()){
            readLoopCat(productTypeGroupDTO1, orderType);
        }
    }


}
