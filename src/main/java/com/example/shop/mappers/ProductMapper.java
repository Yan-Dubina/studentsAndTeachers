package com.example.shop.mappers;

import com.example.shop.domain.Product;
import com.example.shop.domain.Artist;
import com.example.shop.dto.*;
import com.example.shop.repository.ArtistRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;


@Scope("singleton")
@Component
public class ProductMapper {

  private final ArtistRepository artistRepository;
  private final ArtistMapper artistMapper;

  public ProductMapper(ArtistRepository artistRepository, ArtistMapper artistMapper) {
    this.artistRepository = artistRepository;
    this.artistMapper = artistMapper;
  }

  public ProductDTO domainToDTO(Product product) {
    return ProductDTO.builder()
        .build();
  }


  public Product dtoToDomain(EditVinylRequest student) {
    List<Artist> artists = (List<Artist>) artistRepository.findAllById(student.getArtists());
    return Product.builder()
        .build();
  }
}
