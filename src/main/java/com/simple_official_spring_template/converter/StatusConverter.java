package com.simple_official_spring_template.converter;

import com.simple_official_spring_template.dto.StatusDTO;
import com.simple_official_spring_template.entity.StatusEntity;
import org.springframework.stereotype.Component;

@Component
public class StatusConverter {
    public StatusEntity toEntity(StatusDTO statusDTO, StatusEntity statusEntity) {
        if (statusDTO.getId() != null) {
            statusEntity.setId(statusDTO.getId());
        }
        if (statusDTO.getName() != null) {
            statusEntity.setName(statusDTO.getName());
        }
        return statusEntity;
    }

    public StatusDTO toDTO(StatusEntity statusEntity) {
        return new StatusDTO(statusEntity.getId(), statusEntity.getName());
    }
}
