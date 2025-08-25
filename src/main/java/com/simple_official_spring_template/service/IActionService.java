package com.simple_official_spring_template.service;

import com.simple_official_spring_template.dto.ActionDTO;

import java.util.List;

public interface IActionService {
    ActionDTO save(ActionDTO actionDTO);
    ActionDTO update(ActionDTO actionDTO);
    ActionDTO findOneById(Integer id);
    ActionDTO findOneByName(String name);
    List<ActionDTO> findAll();
    void deleteByIdIn(Integer[] ids);
}
