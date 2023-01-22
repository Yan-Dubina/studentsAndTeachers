package com.example.shop.services.impl;

import com.example.shop.domain.Product;
import com.example.shop.enums.Type;
import com.example.shop.repository.ProductRepository;
import com.example.shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAll(Optional<Type> type, Optional<String> field) {
        if (type.isPresent()) {
           return productRepository.findProductsByType(type.get());
        } else {
            return productRepository.findAll();
        }
    }

    @Override
    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getByIds(List<Long> ids) {
        return productRepository.findAllByIdIn(ids);
    }
}
