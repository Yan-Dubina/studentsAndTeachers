package com.example.shop.services;

import com.example.shop.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAll(int page, int number, Optional<String> field);
}
