package com.simple_official_spring_template.api;

import com.simple_official_spring_template.dto.AttributeGroupDTO;
import com.simple_official_spring_template.dto.others.AttributeGroupGroupDTO;
import com.simple_official_spring_template.service.IAttributeGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AttributeGroupAPI {
    @Autowired
    private IAttributeGroupService attributeGroupService;

    @GetMapping(value = "/api-attribute-group")
    public List<AttributeGroupDTO> getCategories() {
        return attributeGroupService.findAll();
    }

    @GetMapping(value = "/api-attribute-group/{id}")
    public AttributeGroupDTO getAttributeGroupById(@PathVariable(value = "id", required = true) Integer id) {
        return attributeGroupService.findOneById(id);
    }

    @GetMapping(value = "/api-attribute-group/get-by-inherit-attribute-group-id")
    public AttributeGroupGroupDTO getInheritAttributeGroup(
            @RequestParam(value = "attributeGroupId", required = false) Integer attributeGroupId, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "order_by", required = false) String orderBy, @RequestParam(value = "order_type", required = false) String orderType
    ){
        return attributeGroupService.findInheritAttributeGroup(attributeGroupId, orderBy, orderType);
    }

    @PostMapping(value = "/api-attribute-group")
    public AttributeGroupDTO createAttributeGroup(@RequestBody AttributeGroupDTO attributeGroupDTO) {
        return attributeGroupService.save(attributeGroupDTO);
    }

    @PutMapping(value = "/api-attribute-group")
    public AttributeGroupDTO updateAttributeGroup(@RequestBody AttributeGroupDTO attributeGroupDTO) {
        return attributeGroupService.update(attributeGroupDTO);
    }

    @DeleteMapping(value = "api-attribute-group")
    public void deleteCategories(@RequestBody AttributeGroupDTO attributeGroupDTO) {
        attributeGroupService.delete(attributeGroupDTO);
    }
}
