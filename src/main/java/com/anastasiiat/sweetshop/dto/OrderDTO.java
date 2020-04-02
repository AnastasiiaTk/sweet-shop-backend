package com.anastasiiat.sweetshop.dto;

import com.anastasiiat.sweetshop.persistence.entity.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {

    private Integer orderId;

    private Date date;

    private User user;

    private List<ProductDTO> productsDTO = new ArrayList<>();
}
