package com.anastasiiat.sweetshop.service;

import com.anastasiiat.sweetshop.SweetShopApplicationTests;
import com.anastasiiat.sweetshop.persistence.entity.Product;
import org.assertj.core.util.IterableUtil;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class ProductPagingServiceTest extends SweetShopApplicationTests {

    @Autowired
    private ProductPagingService productPagingService;

    @Test
    public void findFirstProductListTest() {
        Iterable<Product> result = productPagingService.findProducts(0, 3);
        assertNotNull(result);
        assertTrue(IterableUtil.sizeOf(result) == 3);
        List<Product> productList = Lists.newArrayList(result);
        assertEquals("Apple Boom", productList.get(0).getName());
        assertEquals("Banana and Happiness", productList.get(1).getName());
        assertEquals("Cherry and Happiness", productList.get(2).getName());
    }

    @Test
    public void findSecondProductListTest() {
        Iterable<Product> result = productPagingService.findProducts(1, 3);
        assertNotNull(result);
        assertTrue(IterableUtil.sizeOf(result) == 3);
        List<Product> productList = Lists.newArrayList(result);
        assertEquals("Fat and Happy", productList.get(0).getName());
        assertEquals("Fat and Sweet", productList.get(1).getName());
        assertEquals("Fat bear", productList.get(2).getName());
    }

    @Override
    protected void setUp() throws Exception {

    }

    @Override
    protected void tearDown() throws Exception {

    }
}
