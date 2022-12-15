package com.example.studentandteacherapi.services;

import com.example.studentandteacherapi.domain.Artist;

import java.util.List;
import java.util.Optional;

public interface ArtistService {
  Optional<Artist> getArtist(Long id);

  Optional<Artist> deleteArtistById(Long id);

  List<Artist> getAllArtist(int pageNumber, int numberOfElements, Optional<String> stringOptionalField);

  Artist createArtist(Artist artist);

  Optional<Artist> updateArtist(Artist artist);

  Optional<Artist> updateVinylListForArtist(Long id, List<Long> studentIds);

  List<Artist> findByNameAndSurname(Optional<String> name, Optional<String> surname);

  List<Artist> findByNameOrSurname(Optional<String> name, Optional<String> surname);

}
