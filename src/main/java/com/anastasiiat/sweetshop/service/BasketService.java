package com.anastasiiat.sweetshop.service;

import com.anastasiiat.sweetshop.cache.config.BasketCacheConfig;
import com.anastasiiat.sweetshop.cache.data.BasketData;
import com.anastasiiat.sweetshop.persistence.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class BasketService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BasketCacheConfig basketCacheConfig;

    public void addToBasket(String sessionId, List<Product> product) {
        try {
            BasketData data = basketCacheConfig.getCache().get(sessionId);
            data.addProducts(product);
        } catch (ExecutionException e) {
            logger.error("ERROR during adding products ", e);
        }
    }

    public void deleteFromBasket(String sessionId, List<Product> product) {
        try {
            BasketData data = basketCacheConfig.getCache().get(sessionId);
            data.removeProducts(product);
        } catch (ExecutionException e) {
            logger.error("ERROR during deleting products ", e);
        }
    }

    public List<Product> readBasketItems(String basketUUID) {
        try {
            return basketCacheConfig.getCache().get(basketUUID).readBasketItems();
        } catch (Exception e) {
            logger.error("ERROR during reading basket data: ", e);
            return null;
        }
    }

    public void invalidateCache() {
        basketCacheConfig.getCache().invalidateAll();
    }

    public BasketData getBasketData(String sessionId){
        return basketCacheConfig.getCache().getUnchecked(sessionId);
    }

}
