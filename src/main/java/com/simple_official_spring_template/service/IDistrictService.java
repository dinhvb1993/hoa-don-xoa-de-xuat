package com.simple_official_spring_template.service;

import com.simple_official_spring_template.dto.DistrictDTO;

import java.util.List;

public interface IDistrictService {
    DistrictDTO save(DistrictDTO districtDTO);
    DistrictDTO update(DistrictDTO districtDTO);
    DistrictDTO findOneById(Integer id);
    List<DistrictDTO> findAll();
    List<DistrictDTO> findByCityId(Integer cityId);
    void delete(DistrictDTO districtDTO);
}
