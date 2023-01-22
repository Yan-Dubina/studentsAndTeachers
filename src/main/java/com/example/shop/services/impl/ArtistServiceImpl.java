package com.example.shop.services.impl;

import com.example.shop.domain.Artist;
import com.example.shop.repository.ArtistRepository;
import com.example.shop.repository.ProductRepository;
import com.example.shop.services.ArtistService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService {

  private final ArtistRepository artistRepository;
  private final ProductRepository productRepository;

  public ArtistServiceImpl(ArtistRepository artistRepository, ProductRepository productRepository) {
    this.artistRepository = artistRepository;
    this.productRepository = productRepository;
  }

  @Override
  public Optional<Artist> getArtist(Long id) {
    return artistRepository.findById(id);
  }

  @Override
  public Optional<Artist> deleteArtistById(Long id) {
    return getArtist(id).map(teacher -> {
      artistRepository.deleteById(teacher.getId());
      return teacher;
    });
  }

  @Override
  public List<Artist> getAllArtist(int pageNumber, int numberOfElements, Optional<String> sortBy) {
    return artistRepository
        .findAll(PageRequest.of(pageNumber, numberOfElements, Sort.by(sortBy.orElse("name"))))
        .toList();
  }

  @Override
  public Artist createArtist(Artist artist) {
    return artistRepository.save(artist);
  }

  @Override
  public Optional<Artist> updateArtist(Artist artist) {
    return getArtist(artist.getId()).map(t -> artistRepository.save(artist));
  }

}
