package com.anastasiiat.sweetshop.translator;

import com.anastasiiat.sweetshop.dto.OrderDTO;
import com.anastasiiat.sweetshop.dto.ProductDTO;
import com.anastasiiat.sweetshop.persistence.entity.SweetOrder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderTranslator {

    public OrderDTO sweetOrderWithItemsToDto(SweetOrder sweetOrder, List<ProductDTO> products) {
        OrderDTO destination = new OrderDTO();
        destination.setOrderId(sweetOrder.getSweetOrderId());
        destination.setDate(sweetOrder.getCreatedDate());
        destination.getProductsDTO().addAll(products);
        return destination;

    }

}
