package com.example.shop.services;

import com.example.shop.domain.Product;
import com.example.shop.enums.Type;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAll(Optional<Type> type, Optional<String> field);

    Optional<Product> getById(Long id);

    List<Product> getByIds(List<Long> ids);
}
