package com.simple_official_spring_template.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "payment")
public class PaymentEntity {
    @Id
    private Integer orderId;

    @Column(nullable = false)
    private String token;

    @Column
    private Boolean purchased;

    @Column
    @UpdateTimestamp
    private Timestamp time;
}
