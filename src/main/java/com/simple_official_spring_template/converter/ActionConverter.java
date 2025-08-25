package com.simple_official_spring_template.converter;

import com.simple_official_spring_template.authentication.utils.Hashing;
import com.simple_official_spring_template.dto.ActionDTO;
import com.simple_official_spring_template.entity.ActionEntity;
import org.springframework.stereotype.Component;

@Component
public class ActionConverter {

    public ActionDTO toDTO(ActionEntity actionEntity){
        return new ActionDTO(
                actionEntity.getId(), 
                actionEntity.getName()
        );
    }

    public ActionEntity toEntity(ActionDTO actionDTO, ActionEntity actionEntity){
        if (actionDTO.getId() != null){
            actionEntity.setId(actionDTO.getId());
        }
        if (actionDTO.getName() != null){
            actionEntity.setName(actionDTO.getName());
        }

        return actionEntity;
    }
}
