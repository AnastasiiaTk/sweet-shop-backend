package com.anastasiiat.sweetshop.service;

import com.anastasiiat.sweetshop.persistence.entity.Product;
import com.anastasiiat.sweetshop.persistence.repository.ProductPagingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductPagingService {

    @Autowired
    private ProductPagingRepository productPagingRepository;

    public Page<Product> findProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        return productPagingRepository.findAll(pageable);
    }

}
