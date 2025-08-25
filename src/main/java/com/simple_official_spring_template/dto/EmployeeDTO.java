package com.simple_official_spring_template.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class EmployeeDTO extends GenericAccountDTO {
    private String type;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String id, String password, String fullName, String phoneNumber, String email, Boolean isActive, String activeCode, String facebookAccount, String type) {
        super(id, password, fullName, phoneNumber, email, isActive, activeCode, facebookAccount);
        this.type = type;
    }
}
