package com.simple_official_spring_template.converter;

import com.simple_official_spring_template.dto.DistrictDTO;
import com.simple_official_spring_template.entity.DistrictEntity;
import com.simple_official_spring_template.repository.ICityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DistrictConverter {

    @Autowired
    private ICityRepository cityRepository;

    @Autowired
    private com.simple_official_spring_template.converter.CityConverter cityConverter;

    public DistrictEntity toEntity(DistrictDTO districtDTO, DistrictEntity districtEntity){
        if (districtDTO.getId() != null){
            districtEntity.setId(districtDTO.getId());
        }
        if (districtDTO.getName() != null){
            districtEntity.setName(districtDTO.getName());
        }
        if (districtDTO.getCityDTO() != null){
            districtEntity.setCity(cityRepository.findOneById(districtDTO.getCityDTO().getId()));
        }
        return districtEntity;
    }

    public DistrictDTO toDTO(DistrictEntity districtEntity){
        return new DistrictDTO(
                districtEntity.getId(),
                districtEntity.getName(),
                cityConverter.toDTO(cityRepository.findOneById(districtEntity.getCity().getId()))
        );
    }
}
