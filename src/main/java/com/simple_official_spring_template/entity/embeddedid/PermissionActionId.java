package com.simple_official_spring_template.entity.embeddedid;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class PermissionActionId implements Serializable {
    private Integer permissionId;
    private Integer actionId;

    public PermissionActionId() {

    }

    public PermissionActionId(Integer permissionId, Integer actionId) {
        this.permissionId = permissionId;
        this.actionId = actionId;
    }
}
