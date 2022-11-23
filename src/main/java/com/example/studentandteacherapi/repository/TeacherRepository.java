package com.example.studentandteacherapi.repository;

import com.example.studentandteacherapi.domain.Teacher;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends PagingAndSortingRepository<Teacher, Long> {
  List<Teacher> findTeachersByNameLikeAndSurnameLike (Optional<String> name,Optional<String> surname);
  List<Teacher> findTeachersByNameLikeOrSurnameLike (Optional<String> name,Optional<String> surname);
}
