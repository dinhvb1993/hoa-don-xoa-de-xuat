package com.simple_official_spring_template.entity.embeddedid;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class UserPermissionId implements Serializable {
    private String userId;
    private Integer permissionId;

    public UserPermissionId() {

    }

    public UserPermissionId(String userId, Integer permissionId) {
        this.userId = userId;
        this.permissionId = permissionId;
    }
}
