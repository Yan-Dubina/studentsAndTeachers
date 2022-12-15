package com.example.studentandteacherapi.services.impl;

import com.example.studentandteacherapi.domain.Vinyl;
import com.example.studentandteacherapi.domain.Artist;
import com.example.studentandteacherapi.repository.VinylRepository;
import com.example.studentandteacherapi.repository.ArtistRepository;
import com.example.studentandteacherapi.services.ArtistService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService {

  private final ArtistRepository artistRepository;
  private final VinylRepository vinylRepository;

  public ArtistServiceImpl(ArtistRepository artistRepository, VinylRepository vinylRepository) {
    this.artistRepository = artistRepository;
    this.vinylRepository = vinylRepository;
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

  @Override
  public Optional<Artist> updateVinylListForArtist(Long id, List<Long> studentIds) {
    List<Vinyl> vinyls = (List<Vinyl>) vinylRepository.findAllById(studentIds);
    Assert.isTrue(vinyls.size() == studentIds.size(), "Invalid studentIds");
    return getArtist(id).map(teacher -> {
      teacher.setVinyls(vinyls);
      return artistRepository.save(teacher);
    });
  }

  @Override
  public List<Artist> findByNameAndSurname(Optional<String> name, Optional<String> surname) {
    return artistRepository.findArtistsByNameLikeAndSurnameLike(name, surname);
  }

  @Override
  public List<Artist> findByNameOrSurname(Optional<String> name, Optional<String> surname) {
    return artistRepository.findArtistsByNameLikeOrSurnameLike(name, surname);
  }

}
