package com.simple_official_spring_template.service.impl;

import com.simple_official_spring_template.converter.AttributeGroupConverter;
import com.simple_official_spring_template.converter.ImageSliderItemConverter;
import com.simple_official_spring_template.dto.AttributeGroupDTO;
import com.simple_official_spring_template.dto.others.AttributeGroupGroupDTO;
import com.simple_official_spring_template.entity.AttributeGroupEntity;
import com.simple_official_spring_template.entity.ImageSliderItemEntity;
import com.simple_official_spring_template.repository.IAttributeGroupRepository;
import com.simple_official_spring_template.repository.IImageSliderItemRepository;
import com.simple_official_spring_template.service.IAttributeGroupService;
import com.simple_official_spring_template.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AttributeGroupService implements IAttributeGroupService {
    @Autowired
    private IAttributeGroupRepository attributeGroupRepository;

    @Autowired
    private IImageSliderItemRepository imageSliderItemRepository;

    @Autowired
    private AttributeGroupConverter attributeGroupConverter;

    @Autowired
    private ImageSliderItemConverter imageSliderItemConverter;

    @Override
    public List<AttributeGroupDTO> findAll() {
        List<AttributeGroupDTO> attributeGroupDTOS = new ArrayList<>();

        List<AttributeGroupEntity> attributeGroupEntities = attributeGroupRepository.findAll();
        for (AttributeGroupEntity attributeGroupEntity: attributeGroupEntities) {
            attributeGroupDTOS.add(attributeGroupConverter.toDTO(attributeGroupEntity));
        }

        return attributeGroupDTOS;
    }

    @Override
    public AttributeGroupDTO findOneById(Integer id) {
        AttributeGroupEntity attributeGroupEntity = attributeGroupRepository.findOneById(id);
        return attributeGroupConverter.toDTO(attributeGroupEntity);
    }

    @Override
    public AttributeGroupDTO save(AttributeGroupDTO attributeGroupDTO) {
        if (attributeGroupDTO.getImageSliderAvatarItemDTO() != null){
            ImageSliderItemEntity imageSliderItemEntity = imageSliderItemRepository.save(imageSliderItemConverter.toEntity(attributeGroupDTO.getImageSliderAvatarItemDTO(), new ImageSliderItemEntity()));
            attributeGroupDTO.setImageSliderAvatarItemDTO(imageSliderItemConverter.toDTO(imageSliderItemEntity));
        }
        AttributeGroupEntity attributeGroupEntity = attributeGroupConverter.toEntity(attributeGroupDTO, new AttributeGroupEntity());

        if (attributeGroupDTO.getSlug() == null){
            String slug = StringUtil.unUnicodeAndRemoveSpace(attributeGroupDTO.getName(), "-", "-_");
            int counter = 1;
            String addition = "";
            while (attributeGroupRepository.findOneBySlug(slug + addition) != null){
                addition = "_" + counter++;
            }

            attributeGroupEntity.setSlug(slug + addition);
        }



        attributeGroupEntity = attributeGroupRepository.save(attributeGroupEntity);
        return attributeGroupConverter.toDTO(attributeGroupEntity);
    }

    @Override
    public AttributeGroupDTO update(AttributeGroupDTO attributeGroupDTO) {
        AttributeGroupEntity attributeGroupEntity = attributeGroupRepository.findOneById(attributeGroupDTO.getId());
        // check slug
        String addition = "";
        if (attributeGroupDTO.getSlug() != null && !attributeGroupDTO.getSlug().equals(attributeGroupEntity.getSlug())){
            int counter = 1;
            while (attributeGroupRepository.findOneBySlug(attributeGroupDTO.getSlug() + addition) != null){
                addition = "_" + counter++;
            }
        }
        attributeGroupDTO.setSlug(attributeGroupDTO.getSlug() + addition);

        attributeGroupEntity = attributeGroupConverter.toEntity(attributeGroupDTO, attributeGroupEntity);
        attributeGroupEntity = attributeGroupRepository.save(attributeGroupEntity);
        return attributeGroupConverter.toDTO(attributeGroupEntity);
    }

    @Override
    public void delete(AttributeGroupDTO attributeGroupDTO) {
        for (Integer id: attributeGroupDTO.getIds()) {
            attributeGroupRepository.deleteById(id);
        }
    }

    @Override
    public AttributeGroupGroupDTO findInheritAttributeGroup(Integer attributeGroupId, String orderBy, String orderType){
        AttributeGroupEntity oldAttributeGroupEntity = attributeGroupRepository.findOneById(attributeGroupId);
        AttributeGroupDTO attributeGroupDTO;
        if (oldAttributeGroupEntity == null){
            attributeGroupDTO = new AttributeGroupDTO();
        }
        else {
            attributeGroupDTO = attributeGroupConverter.toDTO(attributeGroupRepository.findOneById(attributeGroupId));
        }

        AttributeGroupGroupDTO attributeGroupGroupDTO = new AttributeGroupGroupDTO(attributeGroupDTO);
        attributeGroupGroupDTO.setCompareFeild(orderBy);
        List<Integer> tmpAllChildAttributeGroupIds = new ArrayList<>();
        loopCat(attributeGroupId, attributeGroupGroupDTO, tmpAllChildAttributeGroupIds);

        attributeGroupGroupDTO.setAllChildAttributeGroupIds(tmpAllChildAttributeGroupIds);

        readLoopCat(attributeGroupGroupDTO, orderType);


//        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, Sort.by("order_number").descending());
//        List<Integer> ids = new ArrayList<>();
//        ids.add(2);
//        ids.add(1);
//        List<AttributeGroupEntity> attributeGroupEntities = attributeGroupRepository.findByAttributeGroupIds(ids, pageable).getContent();
//        for (AttributeGroupEntity attributeGroupEntity: attributeGroupEntities){
//            System.out.println(attributeGroupEntity.getName());
//        }

        return attributeGroupGroupDTO;
    }

    public void loopCat(Integer parentAttributeGroupId, AttributeGroupGroupDTO attributeGroupGroupDTO, List<Integer> tmpAllChildAttributeGroupIds){
        AttributeGroupEntity oldAttributeGroup = attributeGroupRepository.findOneById(parentAttributeGroupId);
        attributeGroupGroupDTO.getAttributeGroupDTO().setCompareFeild(attributeGroupGroupDTO.getCompareFeild());


        List<AttributeGroupEntity> attributeGroupEntities = attributeGroupRepository.findByParent(oldAttributeGroup);
        for(AttributeGroupEntity attributeGroupEntity: attributeGroupEntities){
            attributeGroupGroupDTO.getChildAttributeGroupIds().add(attributeGroupEntity.getId());

            if (!tmpAllChildAttributeGroupIds.contains(attributeGroupEntity.getId())){
                tmpAllChildAttributeGroupIds.add(attributeGroupEntity.getId());
            }

            AttributeGroupDTO newAttributeGroupDTO = attributeGroupConverter.toDTO(attributeGroupEntity);
//            newAttributeGroupDTO.setCompareFeild(attributeGroupGroupDTO.getCompareFeild());
            AttributeGroupGroupDTO childAttributeGroupGroupDTO = new AttributeGroupGroupDTO(newAttributeGroupDTO);
            childAttributeGroupGroupDTO.setCompareFeild(attributeGroupGroupDTO.getCompareFeild());


            loopCat(attributeGroupEntity.getId(), childAttributeGroupGroupDTO, tmpAllChildAttributeGroupIds);

            for (Integer catId: childAttributeGroupGroupDTO.getChildAttributeGroupIds()){
                if (!tmpAllChildAttributeGroupIds.contains(catId)){
                    tmpAllChildAttributeGroupIds.add(catId);
                }
            }

            attributeGroupGroupDTO.getChildAttributeGroupGroupDTO().add(childAttributeGroupGroupDTO);
        }
    }

    public void readLoopCat(AttributeGroupGroupDTO attributeGroupGroupDTO, String orderType){
        if (orderType != null && orderType.equals("desc")){
            Collections.sort(attributeGroupGroupDTO.getChildAttributeGroupGroupDTO(), Collections.reverseOrder());
        }
        else {
            Collections.sort(attributeGroupGroupDTO.getChildAttributeGroupGroupDTO());
        }

        for (AttributeGroupGroupDTO attributeGroupGroupDTO1: attributeGroupGroupDTO.getChildAttributeGroupGroupDTO()){
            readLoopCat(attributeGroupGroupDTO1, orderType);
        }
    }


}
