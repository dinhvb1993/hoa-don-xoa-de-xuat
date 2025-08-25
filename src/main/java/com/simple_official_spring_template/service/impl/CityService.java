package com.simple_official_spring_template.service.impl;

import com.simple_official_spring_template.converter.CityConverter;
import com.simple_official_spring_template.dto.CityDTO;
import com.simple_official_spring_template.entity.CityEntity;
import com.simple_official_spring_template.repository.ICityRepository;
import com.simple_official_spring_template.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class CityService implements ICityService {

    @Autowired
    private ICityRepository cityRepository;

    @Autowired
    private CityConverter cityConverter;


    @Override
    public CityDTO save(CityDTO cityDTO) {
        CityEntity cityEntity = cityConverter.toEntity(cityDTO, new CityEntity());
        return cityConverter.toDTO(cityRepository.save(cityEntity));
    }

    @Override
    public CityDTO update(CityDTO cityDTO) {
        CityEntity cityEntity = cityRepository.findOneById(cityDTO.getId());
        cityEntity = cityConverter.toEntity(cityDTO, cityEntity);
        return cityConverter.toDTO(cityRepository.save(cityEntity));
    }

    @Override
    public CityDTO findOneById(Integer id) {
        return cityConverter.toDTO(cityRepository.findOneById(id));
    }

    @Override
    public List<CityDTO> findAll() {
        List<CityDTO> cityDTOS = new ArrayList<>();
        for (CityEntity cityEntity: cityRepository.findAll()){
            cityDTOS.add(cityConverter.toDTO(cityEntity));
        }
        return cityDTOS;
    }

    @Override
    public void delete(CityDTO cityDTO) {
        cityRepository.deleteByIds(cityDTO.getIds());
    }


}
