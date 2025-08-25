package com.simple_official_spring_template.dto;

import com.simple_official_spring_template.entity.ActionEntity;
import com.simple_official_spring_template.entity.PermissionEntity;
import com.simple_official_spring_template.entity.embeddedid.PermissionActionId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
public class PermissionActionDTO {
    private PermissionActionId permissionActionId;
    private PermissionDTO permissionDTO;
    private ActionDTO actionDTO;
    private boolean licensed;
    private PermissionActionId[] permissionActionIds;

    public PermissionActionDTO() {
    }

    public PermissionActionDTO(PermissionActionId permissionActionId, PermissionDTO permissionDTO, ActionDTO actionDTO, boolean licensed) {
        this.permissionActionId = permissionActionId;
        this.permissionDTO = permissionDTO;
        this.actionDTO = actionDTO;
        this.licensed = licensed;
    }
}
