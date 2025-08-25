package com.simple_official_spring_template.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "parent_id")
    private CommentEntity parentComment;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

//    @ManyToOne
//    @JoinColumn(name = "user_supporter_id")
//    private CustomerSupporterEntity userSupporter;

    @Column
    private Double rate;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column
    private Boolean isPurchased;

    @OneToMany(mappedBy = "comment")
    private Set<CommentStatusEntity> commentStatuses;
}
