package com.simple_official_spring_template.converter;

import com.simple_official_spring_template.dto.CityDTO;
import com.simple_official_spring_template.entity.CityEntity;
import org.springframework.stereotype.Component;

@Component
public class CityConverter {

    public CityEntity toEntity(CityDTO cityDTO, CityEntity cityEntity){
        if (cityDTO.getId() != null){
            cityEntity.setId(cityDTO.getId());
        }
        if (cityDTO.getName() != null){
            cityEntity.setName(cityDTO.getName());
        }
        return cityEntity;
    }

    public CityDTO toDTO(CityEntity cityEntity){
        return new CityDTO(
                cityEntity.getId(),
                cityEntity.getName()
        );
    }
}
