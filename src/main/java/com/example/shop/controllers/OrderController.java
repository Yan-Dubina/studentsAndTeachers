package com.example.shop.controllers;

import com.example.shop.domain.ShopUser;
import com.example.shop.dto.OrderDTO;
import com.example.shop.mappers.ProductMapper;
import com.example.shop.services.OrderService;
import com.example.shop.services.ProductService;
import com.example.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    ProductMapper productMapper;
    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @PostMapping("")
    public ResponseEntity<Long> createOrder(@RequestBody List<Long> ids) {
        return ResponseEntity.ok().body(orderService.createOrder(productService.getByIds(ids), ""));
    }

    @GetMapping("")
    public ResponseEntity<?> getOrderById(@RequestParam Long id) {
        return orderService
                .getOrderById(id).map(order ->
                       ResponseEntity.ok().body(order.products.stream()
                        .map(productMapper::domainToDTO)
                        .collect(Collectors.toList()))).orElse(ResponseEntity.badRequest().build());
}

}
