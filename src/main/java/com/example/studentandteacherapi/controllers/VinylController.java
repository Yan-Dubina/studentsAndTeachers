package com.example.studentandteacherapi.controllers;

import com.example.studentandteacherapi.domain.Vinyl;
import com.example.studentandteacherapi.dto.CreateVinylRequest;
import com.example.studentandteacherapi.dto.EditVinylRequest;
import com.example.studentandteacherapi.dto.IdsDTO;
import com.example.studentandteacherapi.dto.VinylDTO;
import com.example.studentandteacherapi.mappers.VinylMapper;
import com.example.studentandteacherapi.services.VinylService;
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
@RequestMapping("/student")
public class VinylController {

  private final VinylMapper vinylMapper;
  private final VinylService vinylService;

  public VinylController(VinylService vinylService, VinylMapper vinylMapper) {
    this.vinylService = vinylService;
    this.vinylMapper = vinylMapper;
  }

  @GetMapping()
  public ResponseEntity<VinylDTO> getArtistById(@RequestParam Long id) {
    Optional<VinylDTO> student = vinylService.getVinyl(id).map(vinylMapper::domainToDTO);
    return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteVinylById(@RequestParam Long id) {
    return vinylService.deleteVinylById(id).map(teacher ->
        ResponseEntity.ok().<Void>build()).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("/find")
  public ResponseEntity<List<VinylDTO>> getVinylPage(@RequestParam int page,
                                                       @RequestParam int numberOfElements,
                                                       @RequestParam Optional<String> sortBy) {
    List<VinylDTO> students = vinylService.getAllVinyl(page, numberOfElements, sortBy).stream()
        .map(vinylMapper::domainToDTO).collect(Collectors.toList());
    return ResponseEntity.ok(students);
  }

  @GetMapping("/search")
  public ResponseEntity<List<VinylDTO>> getVinylsByNameOrSurname(@RequestParam Optional<String> name,
                                                                   @RequestParam Optional<String> surname,
                                                                   @RequestParam String type) {
    List<VinylDTO> students;
    if (type.equals("OR")) {
      students = vinylService.findByNameOrSurname(name, surname).stream()
              .map(vinylMapper::domainToDTO).collect(Collectors.toList());
    } else {
      students = vinylService.findByNameAndSurname(name, surname).stream()
              .map(vinylMapper::domainToDTO).collect(Collectors.toList());
    }
    return ResponseEntity.ok(students);
  }

  @PostMapping
  public ResponseEntity<Void> createNewVinyl(@Valid @RequestBody CreateVinylRequest body) {
    Vinyl teacher = vinylService.createVinyl(vinylMapper.dtoToDomain(body));
    return ResponseEntity.created(URI.create("/student?id=" + teacher.getId())).build();
  }

  @PutMapping
  public ResponseEntity<Vinyl> updateVinyl(@Valid @RequestBody EditVinylRequest body) {
    return vinylService.updateVinyl(vinylMapper.dtoToDomain(body))
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PutMapping("/teacher")
  public ResponseEntity<?> updateArtistList(@RequestParam Long id, @RequestBody IdsDTO teacherIDs) {
    try {
      return vinylService.updateArtistListForVinyl(id, teacherIDs.getIds())
          .map(vinylMapper::domainToDTO).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/teacher")
  public ResponseEntity<?> getVinylListForArtist(@RequestParam Long id) {
    return vinylService.getVinyl(id)
        .map(vinylMapper::domainToDTO)
        .map(vinylDTO -> vinylDTO.getArtists().stream())
        .map(ResponseEntity::ok).orElse(ResponseEntity.ok().build());
  }
}
