package com.simple_official_spring_template.dto.others;

import com.simple_official_spring_template.dto.AttributeGroupDTO;
import com.simple_official_spring_template.repository.IAttributeGroupRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AttributeGroupGroupDTO implements Comparable<AttributeGroupGroupDTO>{
//    private Integer attributeGroupId;
//    private Integer orderNumber;
    private AttributeGroupDTO attributeGroupDTO;
    private List<Integer> childAttributeGroupIds;
    private List<Integer> allChildAttributeGroupIds;
    private List<AttributeGroupGroupDTO> childAttributeGroupGroupDTO;
    private String compareFeild;

    @Autowired
    private IAttributeGroupRepository attributeGroupRepository;

    public AttributeGroupGroupDTO() {
    }

    public AttributeGroupGroupDTO(AttributeGroupDTO attributeGroupDTO) {
        this.attributeGroupDTO = attributeGroupDTO;
        this.childAttributeGroupIds = new ArrayList<>();
        this.allChildAttributeGroupIds = new ArrayList<>();
        this.childAttributeGroupGroupDTO = new ArrayList<>();
    }

    @Override
    public int compareTo(AttributeGroupGroupDTO o) {
//        return this.attributeGroupDTO.compareTo(o.attributeGroupDTO);
        return this.attributeGroupDTO.compareTo(o.getAttributeGroupDTO());
    }
}
