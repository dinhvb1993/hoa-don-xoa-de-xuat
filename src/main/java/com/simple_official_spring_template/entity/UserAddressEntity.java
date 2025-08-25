package com.simple_official_spring_template.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "user_address")
public class UserAddressEntity extends GenericAddress {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
