package com.example.shop.mappers;

import com.example.shop.domain.Product;
import com.example.shop.domain.Artist;
import com.example.shop.dto.*;
import com.example.shop.enums.Type;
import com.example.shop.repository.ArtistRepository;
import com.example.shop.services.ImageService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Scope("singleton")
@Component
public class ProductMapper {

  private final ArtistRepository artistRepository;
  private final ArtistMapper artistMapper;
  @Autowired
  ImageService imageService;
  @Autowired
  CommentMapper commentMapper;

  public ProductMapper(ArtistRepository artistRepository, ArtistMapper artistMapper) {
    this.artistRepository = artistRepository;
    this.artistMapper = artistMapper;
  }

  public ProductDTO domainToDTO(Product product) {
    try {
      return ProductDTO.builder()
              .artists(artistMapper.domainToDTO(product.getArtist()))
              .comments(product.getComments().stream().map(comment -> commentMapper.domainToDTO(comment)).collect(Collectors.toList()))
              .cost(product.getCost())
              .id(product.getId())
              .image(FileUtils.readFileToByteArray(imageService.getImageForProduct(product.getId())))
              .type(product.getType())
              .build();
    } catch (Exception ignored) {
      return ProductDTO.builder()
              .artists(artistMapper.domainToDTO(product.getArtist()))
              .comments(product.getComments().stream().map(comment -> commentMapper.domainToDTO(comment)).collect(Collectors.toList()))
              .cost(product.getCost())
              .id(product.getId())
              .type(product.getType())
              .build();
    }
  }


  public Product dtoToDomain(EditVinylRequest student) {
    List<Artist> artists = (List<Artist>) artistRepository.findAllById(student.getArtists());
    return Product.builder()
        .build();
  }
}
