package com.simple_official_spring_template.dto;

public class AdminDTO extends EmployeeDTO {

    public AdminDTO() {
    }

    public AdminDTO(String id, String password, String fullName, String phoneNumber, String email, Boolean isActive, String activeCode, String facebookAccount, String type) {
        super(id, password, fullName, phoneNumber, email, isActive, activeCode, facebookAccount, type);
    }

}
