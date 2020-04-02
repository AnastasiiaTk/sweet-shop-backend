package com.anastasiiat.sweetshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductPageDTO {

    private List<ProductDTO> productsDTO;

    private Integer totalAmount;
}
