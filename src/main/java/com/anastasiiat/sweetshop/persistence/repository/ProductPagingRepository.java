package com.anastasiiat.sweetshop.persistence.repository;

import com.anastasiiat.sweetshop.persistence.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface ProductPagingRepository extends PagingAndSortingRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

    Page<Product> findAll(Pageable pageable);

}
