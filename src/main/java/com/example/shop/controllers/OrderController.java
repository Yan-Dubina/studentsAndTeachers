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

    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //thread
    private static final String JSESSIONID = "JSESSIONID";
    private static final String NOT_LOGGED_TOKEN = "NOT_LOGGED_TOKEN";

    @PostMapping("")
    public ResponseEntity<Long> createOrder(@RequestHeader (name=JSESSIONID) Optional<String> token,
                            @RequestBody List<Long> ids) {

        if(token.isPresent()){
            return ResponseEntity.ok().body(orderService.createOrder(productService.getByIds(ids), userService.getUser(token.get())));
        } else {
            String newToken = generateNewToken();
            Long id = orderService.createOrder(productService.getByIds(ids), newToken);
            return ResponseEntity.ok().header(NOT_LOGGED_TOKEN, newToken).body(id);
        }
    }

    @GetMapping("")
    public OrderDTO getOrderById(@RequestHeader (name=JSESSIONID) Optional<String> token,
                                 @RequestHeader (name=NOT_LOGGED_TOKEN) Optional<String> notLoggedToken) {
        if(token.isPresent()){
            ShopUser shopUser = userService.getUser(token.get());
            return new OrderDTO(orderService
                    .getOrderForUser(shopUser)
                    .products
                    .stream()
                    .map(productMapper::domainToDTO)
                    .collect(Collectors.toList()));
        } else return notLoggedToken.map(s -> new OrderDTO(orderService.getOrderByToken(s)
                .products
                .stream().map(productMapper::domainToDTO).collect(Collectors.toList()))).orElse(null);
    }

    public static String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }
}
