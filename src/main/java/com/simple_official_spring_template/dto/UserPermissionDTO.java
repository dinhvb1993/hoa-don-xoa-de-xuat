package com.simple_official_spring_template.dto;

import com.simple_official_spring_template.entity.PermissionEntity;
import com.simple_official_spring_template.entity.UserEntity;
import com.simple_official_spring_template.entity.embeddedid.UserPermissionId;
import com.simple_official_spring_template.entity.embeddedid.UserPermissionId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
public class UserPermissionDTO {
    private UserPermissionId userPermissionId;
    private UserDTO userDTO;
    private PermissionDTO permissionDTO;
    private boolean licensed;
    private UserPermissionId[] userPermissionIds;

    public UserPermissionDTO(UserPermissionId userPermissionId, UserDTO userDTO, PermissionDTO permissionDTO, boolean licensed) {
        this.userPermissionId = userPermissionId;
        this.permissionDTO = permissionDTO;
        this.userDTO = userDTO;
        this.licensed = licensed;
    }
}
