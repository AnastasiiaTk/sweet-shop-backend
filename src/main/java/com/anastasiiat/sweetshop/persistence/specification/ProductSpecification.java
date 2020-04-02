package com.anastasiiat.sweetshop.persistence.specification;

import com.anastasiiat.sweetshop.persistence.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class ProductSpecification {

    public static Specification<Product> getProductsByNameSpec(String name) {
        return (Specification<Product>) (root, query, criteriaBuilder) -> {
            Predicate equalPredicate = criteriaBuilder.like(root.get("name"), name);
            return equalPredicate;
        };
    }

}
