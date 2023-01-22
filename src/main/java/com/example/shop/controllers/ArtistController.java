package com.example.shop.controllers;


import com.example.shop.domain.Artist;
import com.example.shop.dto.ArtistDTO;
import com.example.shop.dto.CreateArtistRequest;
import com.example.shop.dto.EditArtistRequest;
import com.example.shop.mappers.ArtistMapper;
import com.example.shop.mappers.ProductMapper;
import com.example.shop.services.ArtistService;
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


    public ArtistController(ArtistService artistService, ArtistMapper artistMapper, ProductMapper productMapper) {
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


    @GetMapping("/vinyls")
    public ResponseEntity<?> getVinylListForArtist(@RequestParam Long id) {
        return artistService.getArtist(id)
                .map(artistMapper::domainToDTO)
                .map(artistDTO -> artistDTO.getVinyls().stream())
                .map(ResponseEntity::ok).orElse(ResponseEntity.ok().build());
    }
}

