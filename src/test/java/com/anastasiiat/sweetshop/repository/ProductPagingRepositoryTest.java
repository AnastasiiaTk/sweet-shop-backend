package com.anastasiiat.sweetshop.repository;

import com.anastasiiat.sweetshop.SweetShopApplicationTests;
import com.anastasiiat.sweetshop.persistence.entity.Product;
import com.anastasiiat.sweetshop.persistence.repository.ProductPagingRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class ProductPagingRepositoryTest extends SweetShopApplicationTests {

    @Autowired
    private ProductPagingRepository productPagingRepository;

    @Test
    public void findAllTest() {
        Pageable pageable = PageRequest.of(0,2);
        Page<Product> products = productPagingRepository.findAll(pageable);
        List<Product> result = products.getContent();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Milka", result.get(0).getName());
        assertEquals("White Milka", result.get(1).getName());
    }

    @Override
    protected void setUp() throws Exception {

    }

    @Override
    protected void tearDown() throws Exception {

    }
}
