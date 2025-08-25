package com.simple_official_spring_template.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class ActionGroupDTO {
    private ActionDTO action;
    private List<PermissionActionDTO> permissionActions;
}
