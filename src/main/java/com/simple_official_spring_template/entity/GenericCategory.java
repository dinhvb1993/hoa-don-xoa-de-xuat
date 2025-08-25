package com.simple_official_spring_template.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class GenericCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column
    private String slug;

    @Column Integer orderNumber;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private Boolean showHome;

    @CreationTimestamp
    private Timestamp creationTime;

    @UpdateTimestamp
    private Timestamp updateTime;

    public GenericCategory() {
    }

    public GenericCategory(Integer id, String name, String slug, Integer orderNumber, String description, Timestamp creationTime, Timestamp updateTime) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.orderNumber = orderNumber;
        this.description = description;
        this.creationTime = creationTime;
        this.updateTime = updateTime;
    }
}
