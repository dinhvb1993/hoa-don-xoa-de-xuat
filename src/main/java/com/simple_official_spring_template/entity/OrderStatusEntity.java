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
@Table(name = "order_status")
public class OrderStatusEntity extends GenericStatus {
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;
}
