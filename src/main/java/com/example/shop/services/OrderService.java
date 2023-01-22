package com.example.shop.services;

import com.example.shop.domain.ProductsOrder;
import com.example.shop.domain.Product;
import com.example.shop.domain.ShopUser;

import java.util.List;

public interface OrderService {

    ProductsOrder getOrderForUser(ShopUser shopUser);

    ProductsOrder getOrderByToken(String token);

    Long addProductsToOrder(List<Product> products, Long orderId);

    Long createOrder(List<Product> products, ShopUser shopUser);

    Long createOrder(List<Product> products, String token);
}
