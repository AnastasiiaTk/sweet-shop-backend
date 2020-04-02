package com.anastasiiat.sweetshop.api;

import com.anastasiiat.sweetshop.persistence.entity.Product;
import com.anastasiiat.sweetshop.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BasketController {

    @Autowired
    private BasketService basketService;

    @PostMapping(value = "/basket/addToBasket", consumes = "application/json")
    public void addToBasket(@RequestBody List<Product> products, @RequestParam String basketUUID) {
        basketService.addToBasket(basketUUID, products);
    }

    @PostMapping(value = "/basket/deleteProduct", consumes = "application/json")
    public void deleteProductFromBasket(@RequestBody List<Product> products, @RequestParam String basketUUID) {
        basketService.deleteFromBasket(basketUUID, products);
    }

    @GetMapping("/basket/getBasketItems")
    public @ResponseBody
    Iterable<Product> getBasketItems(@RequestParam String basketUUID) {
        return basketService.readBasketItems(basketUUID);
    }
}
