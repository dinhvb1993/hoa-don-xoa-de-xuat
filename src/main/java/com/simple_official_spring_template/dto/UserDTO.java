package com.simple_official_spring_template.dto;

import com.simple_official_spring_template.entity.UserPermissionEntity;
import com.simple_official_spring_template.utils.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

public class UserDTO extends GenericAccountDTO {
    private Boolean isFemale;
    private Date birthDate;
    private boolean emailVerifier;
    private boolean master;
    private boolean exception;


    public UserDTO(String id, String password, String fullName, String phoneNumber, String email, Boolean isActive, String activeCode, Boolean isFemale, Date birthDate, boolean emailVerifier, boolean master, String facebookAccount) {
        super(id, password, fullName, phoneNumber, email, isActive, activeCode, facebookAccount);
        this.isFemale = isFemale;
        this.birthDate = birthDate;
        this.emailVerifier = emailVerifier;
        this.master = master;
    }
}
