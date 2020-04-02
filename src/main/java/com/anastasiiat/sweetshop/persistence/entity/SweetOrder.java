package com.anastasiiat.sweetshop.persistence.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sweet_order")
public class SweetOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sweet_order_id")
    private Integer sweetOrderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "created_date", nullable = false, columnDefinition = "date default current_date")
    private Date createdDate = new Date();

    @Transient
    private Iterable<OrderItem> orderItems;

    public Integer getSweetOrderId() {
        return sweetOrderId;
    }

    public void setSweetOrderId(Integer sweetOrderId) {
        this.sweetOrderId = sweetOrderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Iterable<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Iterable<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
