package com.example.studentandteacherapi.mappers;

import com.example.studentandteacherapi.domain.Vinyl;
import com.example.studentandteacherapi.domain.Artist;
import com.example.studentandteacherapi.dto.CreateArtistRequest;
import com.example.studentandteacherapi.dto.EditArtistRequest;
import com.example.studentandteacherapi.dto.ArtistDTO;
import com.example.studentandteacherapi.repository.VinylRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class ArtistMapper {

  private final VinylRepository vinylRepository;
  private final VinylMapper vinylMapper;

  public ArtistMapper(VinylRepository vinylRepository, @Lazy VinylMapper vinylMapper) {
    this.vinylRepository = vinylRepository;
    this.vinylMapper = vinylMapper;
  }

  public ArtistDTO domainToDTO(Artist artist) {
    return ArtistDTO.builder()
        .id(artist.getId())
        .name(artist.getName())
        .surname(artist.getSurname())
        .email(artist.getEmail())
        .age(artist.getAge())
        .vinyls(artist.getVinyls().stream().map(vinylMapper::domainToDTO).collect(Collectors.toList()))
        .subject(artist.getCourse())
        .build();
  }

  public Artist dtoToDomain(CreateArtistRequest teacher) {
    List<Vinyl> vinyls = (List<Vinyl>) vinylRepository.findAllById(teacher.getVinyls());
    return Artist.builder()
        .name(teacher.getName())
        .surname(teacher.getSurname())
        .email(teacher.getEmail())
        .age(teacher.getAge())
        .vinyls(vinyls)
        .course(teacher.getCourse())
        .build();
  }

  public Artist dtoToDomain(EditArtistRequest teacher) {
    List<Vinyl> vinyls = (List<Vinyl>) vinylRepository.findAllById(teacher.getStudents());
    return Artist.builder()
        .id(teacher.getId())
        .name(teacher.getName())
        .surname(teacher.getSurname())
        .email(teacher.getEmail())
        .age(teacher.getAge())
        .vinyls(vinyls)
        .course(teacher.getCourse())
        .build();
  }

}
