package com.simple_official_spring_template.service.impl;

import com.simple_official_spring_template.converter.CategoryConverter;
import com.simple_official_spring_template.converter.ImageSliderItemConverter;
import com.simple_official_spring_template.dto.CategoryDTO;
import com.simple_official_spring_template.dto.others.CategoryGroupDTO;
import com.simple_official_spring_template.entity.CategoryEntity;
import com.simple_official_spring_template.entity.ImageSliderItemEntity;
import com.simple_official_spring_template.repository.ICategoryRepository;
import com.simple_official_spring_template.repository.IImageSliderItemRepository;
import com.simple_official_spring_template.service.ICategoryService;
import com.simple_official_spring_template.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private IImageSliderItemRepository imageSliderItemRepository;

    @Autowired
    private CategoryConverter categoryConverter;

    @Autowired
    private ImageSliderItemConverter imageSliderItemConverter;

    @Override
    public List<CategoryDTO> findAll() {
        List<CategoryDTO> categoryDTOS = new ArrayList<>();

        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        for (CategoryEntity categoryEntity: categoryEntities) {
            categoryDTOS.add(categoryConverter.toDTO(categoryEntity));
        }

        return categoryDTOS;
    }

    @Override
    public CategoryDTO findOneById(Integer id) {
        CategoryEntity categoryEntity = categoryRepository.findOneById(id);
        return categoryConverter.toDTO(categoryEntity);
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        if (categoryDTO.getImageSliderAvatarItemDTO() != null){
            ImageSliderItemEntity imageSliderItemEntity = imageSliderItemRepository.save(imageSliderItemConverter.toEntity(categoryDTO.getImageSliderAvatarItemDTO(), new ImageSliderItemEntity()));
            categoryDTO.setImageSliderAvatarItemDTO(imageSliderItemConverter.toDTO(imageSliderItemEntity));
        }
        CategoryEntity categoryEntity = categoryConverter.toEntity(categoryDTO, new CategoryEntity());

        if (categoryDTO.getSlug() == null){
            String slug = StringUtil.unUnicodeAndRemoveSpace(categoryDTO.getName(), "-", "-_");
            int counter = 1;
            String addition = "";
            while (categoryRepository.findOneBySlug(slug + addition) != null){
                addition = "_" + counter++;
            }

            categoryEntity.setSlug(slug + addition);
        }



        categoryEntity = categoryRepository.save(categoryEntity);
        return categoryConverter.toDTO(categoryEntity);
    }

    @Override
    public CategoryDTO update(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = categoryRepository.findOneById(categoryDTO.getId());
        // check slug
        String addition = "";
        if (categoryDTO.getSlug() != null && !categoryDTO.getSlug().equals(categoryEntity.getSlug())){
            int counter = 1;
            while (categoryRepository.findOneBySlug(categoryDTO.getSlug() + addition) != null){
                addition = "_" + counter++;
            }
        }
        categoryDTO.setSlug(categoryDTO.getSlug() + addition);

        // update parent category
        if (categoryDTO.getParentCategoryDTO() != null && categoryDTO.getParentCategoryDTO().getExceptionNote() == null){


            if (categoryEntity.getParent() == null
                    || !categoryDTO.getParentCategoryDTO().getId().equals(categoryEntity.getParent().getId())
            ){
                // check if new parent category is child of current category

                CategoryEntity newParentCategoryEntity = categoryRepository.findOneById(categoryDTO.getParentCategoryDTO().getId());

                while (newParentCategoryEntity.getParent() != null){
                    if (newParentCategoryEntity.getParent().getId().equals(categoryDTO.getId())){
                        return null;
                    }
                    newParentCategoryEntity = categoryRepository.findOneById(newParentCategoryEntity.getParent().getId());
                }
            }



        }

        categoryEntity = categoryConverter.toEntity(categoryDTO, categoryEntity);


        // update null for parent category


        categoryEntity = categoryRepository.save(categoryEntity);

        return categoryConverter.toDTO(categoryEntity);
    }

    @Override
    public void delete(CategoryDTO categoryDTO) {
        categoryRepository.deleteByIds(categoryDTO.getIds());
    }

    @Override
    public CategoryGroupDTO findInheritCategory(Integer categoryId, String orderBy, String orderType){
        CategoryEntity oldCategoryEntity = categoryRepository.findOneById(categoryId);
        CategoryDTO categoryDTO;
        if (oldCategoryEntity == null){
            categoryDTO = new CategoryDTO();
        }
        else {
            categoryDTO = categoryConverter.toDTO(categoryRepository.findOneById(categoryId));
        }


        CategoryGroupDTO categoryGroupDTO = new CategoryGroupDTO(categoryDTO);
        categoryGroupDTO.setCompareFeild(orderBy);

        categoryGroupDTO.setDegree(0);

        List<Integer> tmpAllChildCategoryIds = new ArrayList<>();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        loopCat(categoryId, categoryGroupDTO, tmpAllChildCategoryIds, categoryDTOList);

        categoryGroupDTO.setAllChildCategoryIds(tmpAllChildCategoryIds);
        categoryGroupDTO.setAllChildCategoryDTO(categoryDTOList);

        readLoopCat(categoryGroupDTO, orderType);


//        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, Sort.by("order_number").descending());
//        List<Integer> ids = new ArrayList<>();
//        ids.add(2);
//        ids.add(1);
//        List<CategoryEntity> categoryEntities = categoryRepository.findByCategoryIds(ids, pageable).getContent();
//        for (CategoryEntity categoryEntity: categoryEntities){
//            System.out.println(categoryEntity.getName());
//        }

        return categoryGroupDTO;
    }

    public void loopCat(Integer parentCategoryId, CategoryGroupDTO categoryGroupDTO, List<Integer> tmpAllChildCategoryIds, List<CategoryDTO> categoryDTOList){
        CategoryEntity oldCategory = categoryRepository.findOneById(parentCategoryId);
        categoryGroupDTO.getCategoryDTO().setCompareFeild(categoryGroupDTO.getCompareFeild());


        List<CategoryEntity> categoryEntities = categoryRepository.findByParent(oldCategory);
        for(CategoryEntity categoryEntity: categoryEntities){
            categoryGroupDTO.getChildCategoryIds().add(categoryEntity.getId());



            CategoryDTO newCategoryDTO = categoryConverter.toDTO(categoryEntity);
//            newCategoryDTO.setCompareFeild(categoryGroupDTO.getCompareFeild());
            CategoryGroupDTO childCategoryGroupDTO = new CategoryGroupDTO(newCategoryDTO);
            childCategoryGroupDTO.setCompareFeild(categoryGroupDTO.getCompareFeild());

            childCategoryGroupDTO.setDegree(categoryGroupDTO.getDegree() + 1);

            newCategoryDTO.setDegree(categoryGroupDTO.getDegree() + 1);

            if (!tmpAllChildCategoryIds.contains(categoryEntity.getId())){
                tmpAllChildCategoryIds.add(newCategoryDTO.getId());
                categoryDTOList.add(newCategoryDTO);
            }


            loopCat(categoryEntity.getId(), childCategoryGroupDTO, tmpAllChildCategoryIds, categoryDTOList);

            for (Integer catId: childCategoryGroupDTO.getChildCategoryIds()){
                if (!tmpAllChildCategoryIds.contains(catId)){
                    tmpAllChildCategoryIds.add(catId);
                }
            }

            categoryGroupDTO.getChildCategoryGroupDTO().add(childCategoryGroupDTO);
        }
    }

    public void readLoopCat(CategoryGroupDTO categoryGroupDTO, String orderType){
        if (orderType != null && orderType.equals("desc")){
            Collections.sort(categoryGroupDTO.getChildCategoryGroupDTO(), Collections.reverseOrder());
        }
        else {
            Collections.sort(categoryGroupDTO.getChildCategoryGroupDTO());
        }

        for (CategoryGroupDTO categoryGroupDTO1: categoryGroupDTO.getChildCategoryGroupDTO()){
            readLoopCat(categoryGroupDTO1, orderType);
        }
    }


}
