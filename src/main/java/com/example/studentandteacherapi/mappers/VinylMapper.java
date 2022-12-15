package com.example.studentandteacherapi.mappers;

import com.example.studentandteacherapi.domain.Vinyl;
import com.example.studentandteacherapi.domain.Artist;
import com.example.studentandteacherapi.dto.*;
import com.example.studentandteacherapi.repository.ArtistRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Scope("singleton")
@Component
public class VinylMapper {

  private final ArtistRepository artistRepository;
  private final ArtistMapper artistMapper;

  public VinylMapper(ArtistRepository artistRepository, ArtistMapper artistMapper) {
    this.artistRepository = artistRepository;
    this.artistMapper = artistMapper;
  }

  public VinylDTO domainToDTO(Vinyl vinyl) {
    return VinylDTO.builder()
        .id(vinyl.getId())
        .name(vinyl.getName())
        .surname(vinyl.getSurname())
        .email(vinyl.getEmail())
        .age(vinyl.getAge())
        .artists(vinyl.getArtists().stream().map(artistMapper::domainToDTO).collect(Collectors.toList()))
        .course(vinyl.getCourse())
        .build();
  }

  public Vinyl dtoToDomain(CreateVinylRequest student) {
    List<Artist> artists = (List<Artist>) artistRepository.findAllById(student.getArtists());
    return Vinyl.builder()
        .name(student.getName())
        .surname(student.getSurname())
        .email(student.getEmail())
        .age(student.getAge())
        .artists(artists)
        .course(student.getCourse())
        .build();
  }

  public Vinyl dtoToDomain(EditVinylRequest student) {
    List<Artist> artists = (List<Artist>) artistRepository.findAllById(student.getArtists());
    return Vinyl.builder()
        .id(student.getId())
        .name(student.getName())
        .surname(student.getSurname())
        .email(student.getEmail())
        .age(student.getAge())
        .artists(artists)
        .course(student.getCourse())
        .build();
  }
}
