package com.anastasiiat.sweetshop.api;

import com.anastasiiat.sweetshop.cache.data.BasketData;
import com.anastasiiat.sweetshop.dto.OrderDTO;
import com.anastasiiat.sweetshop.persistence.entity.User;
import com.anastasiiat.sweetshop.service.BasketService;
import com.anastasiiat.sweetshop.service.OrderService;
import com.anastasiiat.sweetshop.service.UserService;
import com.anastasiiat.sweetshop.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BasketService basketService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/order/save")
    public void saveOrder(@RequestHeader("Authorization") String userToken, @RequestParam String basketUUID) {
        String username = (String) TokenUtils.parseTokenToMap(userToken).get("user_name");
        User user = userService.findByUsername(username);
        BasketData basketData = basketService.getBasketData(basketUUID);
        orderService.saveOrderFromBasket(basketData, user);
    }

    @GetMapping(value = "/order/getOrders", produces = "application/json")
    public Iterable<OrderDTO> getOrders(@RequestHeader("Authorization") String userToken) {
        String username = (String) TokenUtils.parseTokenToMap(userToken).get("user_name");
        User user = userService.findByUsername(username);
        return orderService.findOrdersWithItemsByUser(user);
    }

}
