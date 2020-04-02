package com.anastasiiat.sweetshop.translator;

import com.anastasiiat.sweetshop.cache.data.BasketData;
import com.anastasiiat.sweetshop.persistence.entity.OrderItem;
import com.anastasiiat.sweetshop.persistence.entity.Product;
import com.anastasiiat.sweetshop.persistence.entity.SweetOrder;
import com.anastasiiat.sweetshop.persistence.entity.User;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BasketDataTranslator {

    public SweetOrder userBasketDataToOrder(BasketData basketData, User user) {
        SweetOrder sweetOrder = new SweetOrder();
        sweetOrder.setUser(user);
        List<Product> products = basketData.readBasketItems();
        List<OrderItem> orderItems = new ArrayList<>();
        for (Product product : products) {
            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setSweetOrder(sweetOrder);
            orderItems.add(item);
        }
        sweetOrder.setOrderItems(orderItems);
        return sweetOrder;
    }

}
