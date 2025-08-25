package com.simple_official_spring_template.entity;

import com.simple_official_spring_template.entity.embeddedid.UserPermissionId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "user_permission")
public class UserPermissionEntity {
    @EmbeddedId
    private UserPermissionId userPermissionId;

    @ManyToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "permissionId", insertable = false, updatable = false)
    private PermissionEntity permission;


    @Column
    private boolean licensed;
}
