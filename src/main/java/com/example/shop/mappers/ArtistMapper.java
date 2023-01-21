package com.example.shop.mappers;

import com.example.shop.domain.Artist;
import com.example.shop.domain.Product;
import com.example.shop.dto.ArtistDTO;
import com.example.shop.dto.CreateArtistRequest;
import com.example.shop.dto.EditArtistRequest;
import com.example.shop.repository.ProductRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class ArtistMapper {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  public ArtistMapper(ProductRepository productRepository, @Lazy ProductMapper productMapper) {
    this.productRepository = productRepository;
    this.productMapper = productMapper;
  }

  public ArtistDTO domainToDTO(Artist artist) {
    return ArtistDTO.builder()
        .id(artist.getId())
        .name(artist.getName())
        .surname(artist.getSurname())
        .email(artist.getEmail())
        .age(artist.getAge())
        .vinyls(artist.getProducts().stream().map(productMapper::domainToDTO).collect(Collectors.toList()))
        .subject(artist.getCourse())
        .build();
  }

  public Artist dtoToDomain(CreateArtistRequest teacher) {
    List<Product> products = (List<Product>) productRepository.findAllById(teacher.getProducts());
    return Artist.builder()
        .name(teacher.getName())
        .surname(teacher.getSurname())
        .email(teacher.getEmail())
        .age(teacher.getAge())
        .products(products)
        .build();
  }

  public Artist dtoToDomain(EditArtistRequest teacher) {
    List<Product> vinyls = (List<Product>) productRepository.findAllById(teacher.getProducts());
    return Artist.builder()
        .id(teacher.getId())
        .name(teacher.getName())
        .age(teacher.getAge())
        .products(vinyls)
        .build();
  }

}
