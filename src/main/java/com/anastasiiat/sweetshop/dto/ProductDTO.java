package com.anastasiiat.sweetshop.dto;

import com.anastasiiat.sweetshop.persistence.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDTO {

    private Product product;

    private byte[] imageContent;

}
