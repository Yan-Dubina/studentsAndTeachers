package com.example.studentandteacherapi.services.impl;

import com.example.studentandteacherapi.domain.Vinyl;
import com.example.studentandteacherapi.domain.Artist;
import com.example.studentandteacherapi.repository.VinylRepository;
import com.example.studentandteacherapi.repository.ArtistRepository;
import com.example.studentandteacherapi.services.VinylService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class VinylServiceImpl implements VinylService {

  private final VinylRepository vinylRepository;
  private final ArtistRepository artistRepository;

  public VinylServiceImpl(VinylRepository vinylRepository, ArtistRepository artistRepository) {
    this.vinylRepository = vinylRepository;
    this.artistRepository = artistRepository;
  }

  @Override
  public Optional<Vinyl> getVinyl(Long id) {
    return vinylRepository.findById(id);
  }

  @Override
  public Optional<Vinyl> deleteVinylById(Long id) {
    return getVinyl(id).map(student -> {
      vinylRepository.deleteById(student.getId());
      return student;
    });
  }

  @Override
  public List<Vinyl> getAllVinyl(int pageNumber, int numberOfElements, Optional<String> sortBy) {
    return vinylRepository
        .findAll(PageRequest.of(pageNumber, numberOfElements, Sort.by(sortBy.orElse("name"))))
        .toList();
  }

  @Override
  public Vinyl createVinyl(Vinyl vinyl) {
    return vinylRepository.save(vinyl);
  }

  @Override
  public Optional<Vinyl> updateVinyl(Vinyl vinyl) {
    return getVinyl(vinyl.getId()).map(t -> vinylRepository.save(vinyl));
  }

  @Override
  public Optional<Vinyl> updateArtistListForVinyl(Long id, List<Long> artistsIds) {
    List<Artist> artists = (List<Artist>) artistRepository.findAllById(artistsIds);
    Assert.isTrue(artists.size() == artistsIds.size(), "Invalid teacherIds");
    return getVinyl(id).map(student -> {
      student.setArtists(artists);
      return vinylRepository.save(student);
    });
  }

  @Override
  public List<Vinyl> findByNameAndSurname(Optional<String> name, Optional<String> surname) {
    return vinylRepository.findVinylsByNameLikeAndSurnameLike(name,surname);
  }

  @Override
  public List<Vinyl> findByNameOrSurname(Optional<String> name, Optional<String> surname) {
    return vinylRepository.findVinylsByNameLikeOrSurnameLike(name,surname);
  }

}
