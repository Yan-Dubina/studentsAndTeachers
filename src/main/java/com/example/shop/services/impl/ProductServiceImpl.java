package com.example.shop.services.impl;

import com.example.shop.domain.Product;
import com.example.shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductService productService;

    @Override
    public List<Product> getAll(int page, int number, Optional<String> field) {
        return productService.getAll(page, number, field);
    }
}
