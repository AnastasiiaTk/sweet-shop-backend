package com.anastasiiat.sweetshop.service;

import com.anastasiiat.sweetshop.SweetShopApplicationTests;
import com.anastasiiat.sweetshop.persistence.entity.Product;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class BasketServiceTest extends SweetShopApplicationTests {

    private final String SESSION_ID = "sessionId";
    @Autowired
    private BasketService basketService;

    @Test
    public void deleteProductFromBasket() {
        Product firstProduct = new Product();
        firstProduct.setProductId(1);
        firstProduct.setName("firstProduct");
        Product secondProduct = new Product();
        secondProduct.setProductId(2);
        secondProduct.setName("secondProduct");
        basketService.addToBasket(SESSION_ID, Arrays.asList(firstProduct, secondProduct));
        assertEquals(2, basketService.readBasketItems(SESSION_ID).size());
        basketService.deleteFromBasket(SESSION_ID, Arrays.asList(secondProduct));
        assertEquals(1, basketService.readBasketItems(SESSION_ID).size());
    }

    @Test
    public void expirationBasketTest() throws Exception {
        Product product = new Product();
        product.setName("test");
        basketService.addToBasket(SESSION_ID, Arrays.asList(product));
        assertEquals(1, basketService.readBasketItems(SESSION_ID).size());
        Thread.sleep(3000);
        assertEquals(0, basketService.readBasketItems(SESSION_ID).size());
    }


    @Override
    protected void setUp() throws Exception {

    }

    @Override
    protected void tearDown() throws Exception {
        basketService.invalidateCache();
    }
}
