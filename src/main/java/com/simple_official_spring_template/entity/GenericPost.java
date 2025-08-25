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
public abstract class GenericPost {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(columnDefinition = "TEXT")
    private String nakedName;

    @Column
    private String slug;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column
    private Integer orderNumber;

    @CreationTimestamp
    private Timestamp creationTime;

    @UpdateTimestamp
    private Timestamp updateTime;

    @Column
    private Boolean isHidden;

    public GenericPost() {
    }

    public GenericPost(Integer id, String name, String slug, String description, String content, Integer orderNumber, Timestamp creationTime, Timestamp updateTime, Boolean isHidden) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.content = content;
        this.orderNumber = orderNumber;
        this.creationTime = creationTime;
        this.updateTime = updateTime;
        this.isHidden = isHidden;
    }
}
