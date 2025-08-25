package com.simple_official_spring_template.service;

import com.simple_official_spring_template.dto.CityDTO;

import java.util.List;

public interface ICityService {
    CityDTO save(CityDTO cityDTO);
    CityDTO update(CityDTO cityDTO);
    CityDTO findOneById(Integer id);
    List<CityDTO> findAll();
    void delete(CityDTO cityDTO);
}
