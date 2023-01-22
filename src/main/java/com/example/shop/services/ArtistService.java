package com.example.shop.services;

import com.example.shop.domain.Artist;

import java.util.List;
import java.util.Optional;

public interface ArtistService {
  Optional<Artist> getArtist(Long id);

  Optional<Artist> deleteArtistById(Long id);

  List<Artist> getAllArtist(int pageNumber, int numberOfElements, Optional<String> stringOptionalField);

  Artist createArtist(Artist artist);

  Optional<Artist> updateArtist(Artist artist);

}
