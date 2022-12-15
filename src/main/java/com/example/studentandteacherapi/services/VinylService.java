package com.example.studentandteacherapi.services;

import com.example.studentandteacherapi.domain.Vinyl;

import java.util.List;
import java.util.Optional;

public interface VinylService {
  Optional<Vinyl> getVinyl(Long id);

  Optional<Vinyl> deleteVinylById(Long id);

  List<Vinyl> getAllVinyl(int pageNumber, int numberOfElements, Optional<String> stringOptionalField);

  Vinyl createVinyl(Vinyl vinyl);

  Optional<Vinyl> updateVinyl(Vinyl vinyl);

  Optional<Vinyl> updateArtistListForVinyl(Long id, List<Long> teacherIds);

  List<Vinyl> findByNameAndSurname(Optional<String> name, Optional<String> surname);

  List<Vinyl> findByNameOrSurname(Optional<String> name, Optional<String> surname);
}
