package com.simple_official_spring_template.entity;

import com.simple_official_spring_template.utils.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "permission")
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @OneToMany(mappedBy = "permission")
    private Set<UserPermissionEntity> userPermissionSet;

    @OneToMany(mappedBy = "permission")
    private Set<PermissionActionEntity> permissionActionSet;
}
