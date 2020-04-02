package com.anastasiiat.sweetshop.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @Column(nullable = false)
    private String name;

    @Column(name = "photo_path")
    private String photoPath;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "description")
    private String description;

    @Column(name = "is_available", nullable = false, columnDefinition = "boolean default true")
    private Boolean available = true;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "category_id")
    private Category category;

    public Integer getCategoryId() {
        return this.category == null ? null : this.category.getCategoryId();
    }

    public void setCategoryId(Integer categoryId) {
        if (this.category == null) {
            this.setCategory(new Category());
        }
        this.getCategory().setCategoryId(categoryId);
    }

    public Integer getParentCategoryId() {
        return getCategory() == null ? null : getCategory().getParentCategoryId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Product product = (Product) obj;
        return Objects.equals(productId, product.getProductId());
    }
}
