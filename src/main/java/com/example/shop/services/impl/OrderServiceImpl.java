package com.example.shop.services.impl;

import com.example.shop.domain.ProductsOrder;
import com.example.shop.domain.Product;
import com.example.shop.domain.ShopUser;
import com.example.shop.repository.OrderRepository;
import com.example.shop.services.OrderService;
import com.example.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserService userService;

    @Override
    public ProductsOrder getOrderForUser(ShopUser shopUser) {
        return orderRepository.findById(shopUser.productsOrder.id).orElse(null);
    }

    @Override
    public ProductsOrder getOrderByToken(String token) {
        Optional<Long> id = userService.getOrderIdByToken(token);
        return id.map(aLong -> orderRepository.findById(aLong).get()).orElse(null);
    }

    @Override
    public Long addProductsToOrder(List<Product> products, Long orderId) {
        ProductsOrder productsOrder = orderRepository.getById(orderId);
        productsOrder.products.addAll(products);
        return orderRepository.save(productsOrder).id;
    }

    public Long addProductsToOrder(List<Product> products, String token) {
        ProductsOrder productsOrder = getOrderByToken(token);
        productsOrder.products.addAll(products);
        return orderRepository.save(productsOrder).id;
    }


    @Override
    public Long createOrder(List<Product> products, ShopUser shopUser) {
        if (!Objects.isNull(getOrderForUser(shopUser))) {
           return addProductsToOrder(products, shopUser.productsOrder.id);
        } else {
            ProductsOrder productsOrder = new ProductsOrder();
            productsOrder.setProducts(products);
            shopUser.setProductsOrder(productsOrder);
            productsOrder = orderRepository.save(productsOrder);
            userService.updateOrderForUser(shopUser);
            return productsOrder.id;
        }
    }

    @Override
    public Long createOrder(List<Product> products, String token) {
      if (!Objects.isNull(getOrderByToken(token))) {
            return addProductsToOrder(products, token);
        } else {
            ProductsOrder productsOrder = new ProductsOrder();
            productsOrder.setProducts(products);
            ShopUser shopUser = new ShopUser();
            shopUser.setToken(token);
            productsOrder = orderRepository.save(productsOrder);
            shopUser.setProductsOrder(productsOrder);
            userService.save(shopUser);
            return productsOrder.id;
        }
    }
}
