package com.example.shop;

import com.example.shop.domain.Comment;
import com.example.shop.domain.Product;
import com.example.shop.domain.Artist;
import com.example.shop.enums.Type;
import com.example.shop.repository.CommentRepository;
import com.example.shop.repository.ProductRepository;
import com.example.shop.repository.ArtistRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

@Component
public class TestData implements ApplicationRunner {

  private ProductRepository productRepository;
  private ArtistRepository artistRepository;
  private CommentRepository commentRepository;

  public TestData(ProductRepository productRepository, ArtistRepository artistRepository, CommentRepository commentRepository) {
    this.productRepository = productRepository;
    this.artistRepository = artistRepository;
    this.commentRepository = commentRepository;
  }

  @Override
  public void run(ApplicationArguments args) {
    Artist artist = Artist.builder()
            .name("Andy")
            .surname("Smith")
            .email("java@mail.ua")
            .age(20)
            .course("IT")
            .build();
    artistRepository.save(artist);


    Product testProduct = Product.builder()
            .type(Type.VINYL)
            .cost(100L)
            .artists(List.of(artist))
            .description("test")
            .comments(Collections.emptyList()).build();

    Comment comment = Comment.builder().date(new Date(2600000L)).description("description")
            .shortDescription("short").product(productRepository.save(testProduct)).rate(3).build();
    commentRepository.save(comment);

    testProduct = Product.builder()
            .type(Type.CD)
            .cost(100L)
            .artists(List.of(artist))
            .description("test")
            .comments(List.of(commentRepository.save(comment))).build();
    productRepository.save(testProduct);
    artist = Artist.builder()
        .name("Madison")
        .surname("Jones")
        .email("java@gmail.com")
        .age(20)
        .course("IT")
        .build();
    artistRepository.save(artist);

    artist = Artist.builder()
        .name("George")
        .surname("Davies")
        .email("spring@mail.com")
        .age(20)
        .course("IT")
        .build();

    artistRepository.save(artist);
  }
}

