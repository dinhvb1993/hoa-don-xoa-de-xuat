package com.simple_official_spring_template.service;

import com.simple_official_spring_template.dto.AttributeGroupDTO;
import com.simple_official_spring_template.dto.others.AttributeGroupGroupDTO;

import java.util.List;

public interface IAttributeGroupService {
    List<AttributeGroupDTO> findAll();
    AttributeGroupDTO findOneById(Integer id);
    AttributeGroupDTO save(AttributeGroupDTO attributeGroupDTO);
    AttributeGroupDTO update(AttributeGroupDTO attributeGroupDTO);
    void delete(AttributeGroupDTO attributeGroupDTO);
    AttributeGroupGroupDTO findInheritAttributeGroup(Integer parentAttributeGroupId, String orderBy, String orderType);
}
