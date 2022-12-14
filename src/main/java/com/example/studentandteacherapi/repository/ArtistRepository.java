package com.example.studentandteacherapi.repository;

import com.example.studentandteacherapi.domain.Artist;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistRepository extends PagingAndSortingRepository<Artist, Long> {
  List<Artist> findArtistsByNameLikeAndSurnameLike (Optional<String> name, Optional<String> surname);
  List<Artist> findArtistsByNameLikeOrSurnameLike (Optional<String> name, Optional<String> surname);
}
