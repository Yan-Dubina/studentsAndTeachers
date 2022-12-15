package com.example.studentandteacherapi.repository;

import com.example.studentandteacherapi.domain.Vinyl;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VinylRepository extends PagingAndSortingRepository<Vinyl,Long> {
  List<Vinyl> findVinylsByNameLikeAndSurnameLike(Optional<String> name, Optional<String> surname);
  List<Vinyl> findVinylsByNameLikeOrSurnameLike(Optional<String> name, Optional<String> surname);
}
