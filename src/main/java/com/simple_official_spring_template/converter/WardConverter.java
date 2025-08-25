package com.simple_official_spring_template.converter;

import com.simple_official_spring_template.dto.WardDTO;
import com.simple_official_spring_template.entity.WardEntity;
import com.simple_official_spring_template.repository.IDistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WardConverter {

    @Autowired
    private IDistrictRepository districtRepository;

    @Autowired
    private com.simple_official_spring_template.converter.DistrictConverter districtConverter;

    public WardEntity toEntity(WardDTO wardDTO, WardEntity wardEntity){
        if (wardDTO.getId() != null){
            wardEntity.setId(wardDTO.getId());
        }
        if (wardDTO.getName() != null){
            wardEntity.setName(wardDTO.getName());
        }
        if (wardDTO.getDistrictDTO() != null){
            wardEntity.setDistrict(districtRepository.findOneById(wardDTO.getDistrictDTO().getId()));
        }
        return wardEntity;
    }

    public WardDTO toDTO(WardEntity wardEntity){
        return new WardDTO(
                wardEntity.getId(),
                wardEntity.getName(),
                districtConverter.toDTO(districtRepository.findOneById(wardEntity.getDistrict().getId()))
        );
    }
}
