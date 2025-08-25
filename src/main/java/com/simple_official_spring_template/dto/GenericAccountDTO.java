package com.simple_official_spring_template.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class GenericAccountDTO {
    private String id;
    private String password;
    private String fullName;
    private String phoneNumber;
    private String email;
//    private String avatarUrl;
    private Boolean isActive;
    private String activeCode;
    private String[] ids;
    private String token;
    private String facebookAccount;


    public GenericAccountDTO() {
    }

    public GenericAccountDTO(String id, String password, String fullName, String phoneNumber, String email, Boolean isActive, String activeCode, String facebookAccount) {
        this.id = id;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
//        if (avatarUrl != null){
//            this.avatarUrl = avatarUrl;
//        }
//        this.avatarUrl = avatarUrl;
        this.isActive = isActive;
        this.activeCode = activeCode;
        this.facebookAccount = facebookAccount;
    }
}
