package com.example.shop.controllers;

import com.example.shop.domain.Artist;
import com.example.shop.domain.Comment;
import com.example.shop.domain.Product;
import com.example.shop.enums.Type;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController()
@RequestMapping("/products")
public class ProductController {


    /*
        @GetMapping("/all")
        public List<Product> getAllProducts(@RequestParam int page,
                                            @RequestParam int numberOfElements,
                                            @RequestParam Optional<String> sortBy){
            //return productService.getAll(page, numberOfElements, sortBy);
        }*/
    @GetMapping("/all")
    public List<Product> getAllProducts() {
        Artist artist = Artist.builder().id(13L)
                .name("Andy")
                .surname("Smith")
                .email("java@mail.ua")
                .age(20)
                .course("IT")
                .build();
        Comment comment = Comment.builder().id(10L).date(new Date(1)).rate(3).id(100L).description("test").shortDescription("test").build();
        Product testProduct = Product.builder().id(100L).artists(List.of(artist))
                .comments(List.of(comment))
                .cost(1000L)
                .description("Test description")
                .type(Type.VINYL)
                .build();
        return List.of(testProduct, testProduct, testProduct, testProduct, testProduct, testProduct);
    }

}
