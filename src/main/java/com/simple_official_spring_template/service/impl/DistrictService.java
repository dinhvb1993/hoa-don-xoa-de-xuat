package com.simple_official_spring_template.service.impl;

import com.simple_official_spring_template.converter.DistrictConverter;
import com.simple_official_spring_template.dto.DistrictDTO;
import com.simple_official_spring_template.entity.DistrictEntity;
import com.simple_official_spring_template.repository.ICityRepository;
import com.simple_official_spring_template.repository.IDistrictRepository;
import com.simple_official_spring_template.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DistrictService implements IDistrictService {

    @Autowired
    private IDistrictRepository districtRepository;

    @Autowired
    private ICityRepository cityRepository;

    @Autowired
    private DistrictConverter districtConverter;

    @Override
    public DistrictDTO save(DistrictDTO districtDTO) {
        DistrictEntity districtEntity = districtConverter.toEntity(districtDTO, new DistrictEntity());
        return districtConverter.toDTO(districtRepository.save(districtEntity));
    }

    @Override
    public DistrictDTO update(DistrictDTO districtDTO) {
        DistrictEntity districtEntity = districtRepository.findOneById(districtDTO.getId());
        districtEntity = districtConverter.toEntity(districtDTO, districtEntity);
        return districtConverter.toDTO(districtRepository.save(districtEntity));
    }

    @Override
    public DistrictDTO findOneById(Integer id) {
        return districtConverter.toDTO(districtRepository.findOneById(id));
    }

    @Override
    public List<DistrictDTO> findAll() {
        List<DistrictDTO> districtDTOS = new ArrayList<>();
        for (DistrictEntity districtEntity: districtRepository.findAll()){
            districtDTOS.add(districtConverter.toDTO(districtEntity));
        }
        return districtDTOS;
    }

    @Override
    public List<DistrictDTO> findByCityId(Integer cityId) {
        List<DistrictDTO> districtDTOS = new ArrayList<>();
        for (DistrictEntity districtEntity: districtRepository.findByCity(cityRepository.findOneById(cityId))){
            districtDTOS.add(districtConverter.toDTO(districtEntity));
        }
        return districtDTOS;
    }

    @Override
    public void delete(DistrictDTO districtDTO) {
        districtRepository.deleteByIds(districtDTO.getIds());
    }
}
