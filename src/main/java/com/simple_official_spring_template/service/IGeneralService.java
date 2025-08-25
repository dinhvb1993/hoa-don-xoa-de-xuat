package com.simple_official_spring_template.service;

import com.simple_official_spring_template.dto.ActionGroupDTO;
import com.simple_official_spring_template.dto.UserDTO;
import com.simple_official_spring_template.dto.UserGroupDTO;

import javax.persistence.EntityManager;
import java.util.List;

public interface IGeneralService {

    List<UserGroupDTO> findAllUserGroupDTO();
    List<ActionGroupDTO> findAllActionGroupDTO();

    void deleteImageSliderItem(String tableName, String columnCatIdName, Integer cateId, Integer imageSliderItemId);
}
