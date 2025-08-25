package com.simple_official_spring_template.service;

import com.simple_official_spring_template.dto.StatusDTO;

import java.util.List;

public interface IStatusService {
    List<StatusDTO> findAll();
    StatusDTO findOneById(String id);
    StatusDTO findOneByName(String name);
    StatusDTO save(StatusDTO statusDTO);
    StatusDTO update(StatusDTO statusDTO);
    void delete(StatusDTO statusDTO);
}
