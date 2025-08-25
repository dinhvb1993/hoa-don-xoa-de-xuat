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
@Table(name = "comment_status")
public class CommentStatusEntity extends GenericStatus {
    @ManyToOne
    @JoinColumn(name = "comment_id")
    private com.simple_official_spring_template.entity.CommentEntity comment;

}
