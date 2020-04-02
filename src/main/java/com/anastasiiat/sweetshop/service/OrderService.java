package com.anastasiiat.sweetshop.service;

import com.anastasiiat.sweetshop.cache.data.BasketData;
import com.anastasiiat.sweetshop.dto.OrderDTO;
import com.anastasiiat.sweetshop.dto.ProductDTO;
import com.anastasiiat.sweetshop.persistence.entity.OrderItem;
import com.anastasiiat.sweetshop.persistence.entity.SweetOrder;
import com.anastasiiat.sweetshop.persistence.entity.User;
import com.anastasiiat.sweetshop.persistence.repository.SweetOrderRepository;
import com.anastasiiat.sweetshop.translator.BasketDataTranslator;
import com.anastasiiat.sweetshop.translator.OrderTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private SweetOrderRepository orderRepository;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    private BasketDataTranslator basketDataTranslator;

    @Autowired
    private OrderTranslator orderTranslator;

    @Autowired
    private ProductService productService;

    @Transactional
    public void saveOrderFromBasket(BasketData basketData, User user) {
        SweetOrder sweetOrder = basketDataTranslator.userBasketDataToOrder(basketData, user);
        orderRepository.save(sweetOrder);
        orderItemService.saveOrderItems(sweetOrder.getOrderItems());
        basketData.clear();
    }

    public Iterable<OrderDTO> findOrdersWithItemsByUser(User user) {
        List<OrderDTO> result = new ArrayList<>();
        Iterable<SweetOrder> sweetOrders = orderRepository.findAllByUserOrderByCreatedDate(user);
        for(SweetOrder order : sweetOrders) {
            Iterable<OrderItem> orderItems = orderItemService.getOrderItems(order);
            List<ProductDTO> orderProducts = fillProductDTOS(orderItems);
            result.add(orderTranslator.sweetOrderWithItemsToDto(order, orderProducts));
        }
        return result;
    }

    private List<ProductDTO> fillProductDTOS(Iterable<OrderItem> orderItems) {
        List<ProductDTO> result = new ArrayList<>();
        for(OrderItem item : orderItems) {
            byte[] imageContent = productService.getProductImageContent(item.getProduct());
            result.add(new ProductDTO(item.getProduct(), imageContent));
        }
        return result;
    }

}
