package com.anastasiiat.sweetshop.service;

import com.anastasiiat.sweetshop.SweetShopApplicationTests;
import com.anastasiiat.sweetshop.dto.OrderDTO;
import com.anastasiiat.sweetshop.persistence.entity.Product;
import com.anastasiiat.sweetshop.persistence.entity.User;
import org.assertj.core.util.IterableUtil;
import org.assertj.core.util.Lists;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class OrderServiceTest extends SweetShopApplicationTests {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private BasketService basketService;

    private final String SESSION_ID = "sessionId";

    @Ignore
    @Test
    public void saveOrderFromBasketTest() {
        User user = userService.findByUsername("testuser");
        orderService.saveOrderFromBasket(basketService.getBasketData(SESSION_ID), user);
        assertTrue(basketService.getBasketData(SESSION_ID).readBasketItems().size() == 0);
        Iterable<OrderDTO> resultOrders = orderService.findOrdersWithItemsByUser(user);
        assertNotNull(resultOrders);
        assertTrue(IterableUtil.sizeOf(resultOrders) > 2);
        List<OrderDTO> resultOrdersList = Lists.newArrayList(resultOrders);
        OrderDTO resultOrder = resultOrdersList.get(2);
        assertNotNull(resultOrder.getProductsDTO());

    }

    @Ignore
    @Test
    public void findOrdersWithItemsByUserTest() {
        User user = userService.findByUsername("testuser");
        Iterable<OrderDTO> resultOrders = orderService.findOrdersWithItemsByUser(user);
        assertNotNull(resultOrders);
        assertTrue(IterableUtil.sizeOf(resultOrders) == 2);
    }

    @Override
    protected void setUp() throws Exception {
        List<Product> products = Lists.newArrayList(productService.getAllProducts());
        basketService.addToBasket(SESSION_ID, products);
    }

    @Override
    protected void tearDown() throws Exception {

    }
}
