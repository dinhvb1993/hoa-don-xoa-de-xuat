package com.simple_official_spring_template.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class GenericAccount {
    @Id
    private String id;

    @Column(nullable = false)
    private String password;

    @Column
    private String fullName;

    @Column
    private String phoneNumber;

    @Column
    private String email;

//    @Column(columnDefinition = "TEXT")
//    private String avatarUrl;

    @Column
    private Boolean isActive;

    @Column
    private String activeCode;

    @Column
    private String facebookAccount;

    public GenericAccount() {
    }

    public GenericAccount(String id, String password, String fullName, String phoneNumber, String email, Boolean isActive, Integer currentIndex) {
        this.id = id;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
//        this.avatarUrl = avatarUrl;
        this.isActive = isActive;
    }

}
