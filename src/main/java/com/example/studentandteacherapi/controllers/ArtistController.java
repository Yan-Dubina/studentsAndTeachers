package com.example.studentandteacherapi.controllers;


import com.example.studentandteacherapi.domain.Artist;
import com.example.studentandteacherapi.dto.CreateArtistRequest;
import com.example.studentandteacherapi.dto.EditArtistRequest;
import com.example.studentandteacherapi.dto.IdsDTO;
import com.example.studentandteacherapi.dto.ArtistDTO;
import com.example.studentandteacherapi.mappers.VinylMapper;
import com.example.studentandteacherapi.mappers.ArtistMapper;
import com.example.studentandteacherapi.services.ArtistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/artist")
public class ArtistController {

  private final ArtistService artistService;
  private final ArtistMapper artistMapper;

  public ArtistController(ArtistService artistService, ArtistMapper artistMapper, VinylMapper vinylMapper) {
    this.artistService = artistService;
    this.artistMapper = artistMapper;
  }

  @GetMapping()
  public ResponseEntity<ArtistDTO> getArtistById(@RequestParam Long id) {
    return artistService.getArtist(id).map(artistMapper::domainToDTO)
        .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteArtistById(@RequestParam Long id) {
    return artistService.deleteArtistById(id).map(teacher ->
        ResponseEntity.ok().<Void>build()).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("/find")
  public ResponseEntity<?> getArtistsPage(@RequestParam Optional<String> sortBy,
                                           @RequestParam int page,
                                           @RequestParam int numberOfElements) {
    try {
      List<ArtistDTO> teachers = artistService.getAllArtist(page, numberOfElements, sortBy).stream()
          .map(artistMapper::domainToDTO).collect(Collectors.toList());
      return ResponseEntity.ok(teachers);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/search")
  public ResponseEntity<List<ArtistDTO>> getArtistsByNameAndSurname(@RequestParam Optional<String> name,
                                                                     @RequestParam Optional<String> surname,
                                                                     @RequestParam String type) {
    if (type.equals("OR")) {
      List<ArtistDTO> students = artistService.findByNameOrSurname(name, surname).stream()
          .map(artistMapper::domainToDTO).collect(Collectors.toList());
      return ResponseEntity.ok(students);
    } else {
      List<ArtistDTO> students = artistService.findByNameAndSurname(name, surname).stream()
          .map(artistMapper::domainToDTO).collect(Collectors.toList());
      return ResponseEntity.ok(students);
    }
  }

  @PostMapping
  public ResponseEntity<Void> createNewArtist(@Valid @RequestBody CreateArtistRequest body) {
    Artist artist = artistService.createArtist(artistMapper.dtoToDomain(body));
    return ResponseEntity.created(URI.create("/teacher?id=" + artist.getId())).build();
  }

  @PutMapping
  public ResponseEntity<ArtistDTO> updateArtist(@Valid @RequestBody EditArtistRequest body) {
    return artistService.updateArtist(artistMapper.dtoToDomain(body))
        .map(artistMapper::domainToDTO)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PutMapping("/students")
  public ResponseEntity<?> updateVinylList(@RequestParam Long id, @RequestBody IdsDTO body) {
    try {
      return artistService
          .updateVinylListForArtist(id, body.getIds())
          .map(artistMapper::domainToDTO).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/vinyls")
  public ResponseEntity<?> getVinylListForArtist(@RequestParam Long id) {
    return artistService.getArtist(id)
        .map(artistMapper::domainToDTO)
        .map(artistDTO -> artistDTO.getVinyls().stream())
        .map(ResponseEntity::ok).orElse(ResponseEntity.ok().build());
  }
}

