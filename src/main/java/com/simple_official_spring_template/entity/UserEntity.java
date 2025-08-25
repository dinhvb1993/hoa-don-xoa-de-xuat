package com.simple_official_spring_template.entity;

import com.simple_official_spring_template.utils.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user_account")
public class UserEntity extends GenericAccount {
    @Column
    private Boolean isFemale;

    @Column
    private Date birthDate;

    @Column
    private boolean emailVerifier;

//    @Column
//    private UserRole role;

    @Column
    private boolean master;

    @OneToMany(mappedBy = "user")
    private Set<UserPermissionEntity> userPermissionSet;




    @OneToMany(mappedBy = "user")
    private Set<UserAddressEntity> userAddresses;

    @OneToMany(mappedBy = "user")
    private Set<ProductRateEntity> productRateEntities;

    @OneToMany(mappedBy = "user")
    private Set<CommentEntity> comments;

    @OneToMany(mappedBy = "user")
    private Set<OrderEntity> orders;

    @OneToMany(mappedBy = "user")
    private Set<UserSearchHistoryEntity> userSearchHistories;


}
