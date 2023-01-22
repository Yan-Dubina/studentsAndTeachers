package com.example.shop;

import com.example.shop.domain.Comment;
import com.example.shop.domain.Image;
import com.example.shop.domain.Product;
import com.example.shop.domain.Artist;
import com.example.shop.enums.Type;
import com.example.shop.repository.CommentRepository;
import com.example.shop.repository.ImageRepository;
import com.example.shop.repository.ProductRepository;
import com.example.shop.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.sql.Date;
import java.util.Collections;
import java.util.List;

@Component
public class TestData implements ApplicationRunner {

  private ProductRepository productRepository;
  private ArtistRepository artistRepository;
  private CommentRepository commentRepository;
  @Autowired
  private ImageRepository repository;

  public TestData(ProductRepository productRepository, ArtistRepository artistRepository, CommentRepository commentRepository) {
    this.productRepository = productRepository;
    this.artistRepository = artistRepository;
    this.commentRepository = commentRepository;
  }

  @Override
  public void run(ApplicationArguments args) {
    Artist artist = Artist.builder()
            .name("Andy")
            .build();
    artist = artistRepository.save(artist);


    Product testProduct = Product.builder()
            .type(Type.VINYL)
            .cost(100L)
            .artist(artist)
            .description("test")
            .comments(Collections.emptyList()).build();
    java.util.Date date = new java.util.Date();
    Comment comment1 = Comment.builder().description("description").date(new java.sql.Date(date.getTime()))
            .shortDescription("short").product(productRepository.save(testProduct)).rate(3).build();

    Comment comment = Comment.builder().description("description").date(new java.sql.Date(date.getTime()))
            .shortDescription("short").product(productRepository.save(testProduct)).rate(3).build();

    testProduct = Product.builder()
            .type(Type.CD)
            .cost(100L)
            .artist(artist)
            .description("test")
            .comments(List.of(commentRepository.save(comment))).build();
    productRepository.save(testProduct);
    artist = Artist.builder()
            .name("Madison")
            .products(productRepository.findAll())
            .build();
    artistRepository.save(artist);

    Image image = Image.builder().productId(1L).image(new File("src/main/resources/static/rammstein.jpeg")).build();
    repository.save(image);
    image = Image.builder().productId(2L).image(new File("src/main/resources/static/black.jpeg")).build();
    repository.save(image);
    artistRepository.save(artist);
  }
}

