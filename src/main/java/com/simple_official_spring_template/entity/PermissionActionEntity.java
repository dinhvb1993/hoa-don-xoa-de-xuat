package com.simple_official_spring_template.entity;

import com.simple_official_spring_template.entity.embeddedid.PermissionActionId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "permission_action")
public class PermissionActionEntity {
    @EmbeddedId
    private PermissionActionId permissionActionId;


    @ManyToOne
    @JoinColumn(name = "permissionId", insertable = false, updatable = false)
    private PermissionEntity permission;

    @ManyToOne
    @JoinColumn(name = "actionId", insertable = false, updatable = false)
    private ActionEntity action;


    @Column
    private boolean licensed;
}
