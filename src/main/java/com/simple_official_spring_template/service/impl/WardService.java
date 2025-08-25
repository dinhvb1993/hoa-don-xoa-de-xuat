package com.simple_official_spring_template.service.impl;

import com.simple_official_spring_template.converter.WardConverter;
import com.simple_official_spring_template.dto.WardDTO;
import com.simple_official_spring_template.entity.WardEntity;
import com.simple_official_spring_template.repository.IDistrictRepository;
import com.simple_official_spring_template.repository.IWardRepository;
import com.simple_official_spring_template.service.IWardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WardService implements IWardService {

    @Autowired
    private IWardRepository wardRepository;

    @Autowired
    private IDistrictRepository districtRepository;

    @Autowired
    private WardConverter wardConverter;

    @Override
    public WardDTO save(WardDTO wardDTO) {
        WardEntity wardEntity = wardConverter.toEntity(wardDTO, new WardEntity());
        return wardConverter.toDTO(wardRepository.save(wardEntity));
    }

    @Override
    public WardDTO update(WardDTO wardDTO) {
        WardEntity wardEntity = wardRepository.findOneById(wardDTO.getId());
        wardEntity = wardConverter.toEntity(wardDTO, wardEntity);
        return wardConverter.toDTO(wardRepository.save(wardEntity));
    }

    @Override
    public WardDTO findOneById(Integer id) {
        return wardConverter.toDTO(wardRepository.findOneById(id));
    }

    @Override
    public List<WardDTO> findAll() {
        List<WardDTO> wardDTOS = new ArrayList<>();
        for (WardEntity wardEntity: wardRepository.findAll()){
            wardDTOS.add(wardConverter.toDTO(wardEntity));
        }
        return wardDTOS;
    }

    @Override
    public List<WardDTO> findByDistrictId(Integer districtId) {
        List<WardDTO> wardDTOS = new ArrayList<>();
        for (WardEntity wardEntity: wardRepository.findByDistrict(districtRepository.findOneById(districtId))){
            wardDTOS.add(wardConverter.toDTO(wardEntity));
        }
        return wardDTOS;
    }

    @Override
    public void delete(WardDTO wardDTO) {
        wardRepository.deleteByIds(wardDTO.getIds());
    }
}
