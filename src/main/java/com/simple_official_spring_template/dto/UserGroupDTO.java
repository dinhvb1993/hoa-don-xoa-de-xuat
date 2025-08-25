package com.simple_official_spring_template.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class UserGroupDTO {
    private UserDTO user;
    private List<UserPermissionDTO> userPermissions;
}
