package com.simple_official_spring_template.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "ward")
public class WardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private com.simple_official_spring_template.entity.DistrictEntity district;

    @OneToMany(mappedBy = "ward")
    private Set<com.simple_official_spring_template.entity.GenericAddress> genericAddresses;

//    @OneToMany(mappedBy = "ward")
//    private Set<UserEntity> customerEntities;

    @OneToMany(mappedBy = "ward")
    private Set<com.simple_official_spring_template.entity.GenericAddress> wards;

}
