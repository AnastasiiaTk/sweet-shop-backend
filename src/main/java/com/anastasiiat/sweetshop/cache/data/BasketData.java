package com.anastasiiat.sweetshop.cache.data;

import com.anastasiiat.sweetshop.persistence.entity.Product;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class BasketData {

    private List<Product> basketItems = new ArrayList<Product>();

    public ImmutableList<Product> readBasketItems() {
        return ImmutableList.copyOf(basketItems);
    }

    public void addProducts(List<Product> products) {
        basketItems.addAll(products);
    }

    public void removeProducts(List<Product> products) {
        for (Product product : products) {
            basketItems.remove(product);
        }
    }

    public void clear() {
        basketItems.clear();
    }
}
