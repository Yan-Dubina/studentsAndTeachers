package com.example.shop.controllers;

import com.example.shop.domain.Artist;
import com.example.shop.domain.Comment;
import com.example.shop.domain.Product;
import com.example.shop.dto.ProductDTO;
import com.example.shop.enums.Type;
import com.example.shop.mappers.ProductMapper;
import com.example.shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductService productService;

    @GetMapping("/all")
    public List<ProductDTO> getAllProducts(@RequestParam Optional<Type> type,
                                           @RequestParam Optional<String> sortBy) {
        return productService.getAll(type, sortBy).stream().map(productMapper::domainToDTO).collect(Collectors.toList());
    }

    @GetMapping("")
    public ResponseEntity<?> getProductById(@RequestParam Long id) {
        return productService.getById(id).map(productMapper::domainToDTO)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
