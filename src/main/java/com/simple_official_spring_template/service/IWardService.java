package com.simple_official_spring_template.service;

import com.simple_official_spring_template.dto.WardDTO;

import java.util.List;

public interface IWardService {
    WardDTO save(WardDTO wardDTO);
    WardDTO update(WardDTO wardDTO);
    WardDTO findOneById(Integer id);
    List<WardDTO> findAll();
    List<WardDTO> findByDistrictId(Integer districtId);
    void delete(WardDTO wardDTO);
}
