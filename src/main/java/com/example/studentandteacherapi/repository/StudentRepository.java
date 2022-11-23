package com.example.studentandteacherapi.repository;

import com.example.studentandteacherapi.domain.Student;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository  extends PagingAndSortingRepository<Student,Long> {
  List<Student> findStudentsByNameLikeAndSurnameLike(Optional<String> name, Optional<String> surname);
  List<Student> findStudentsByNameLikeOrSurnameLike(Optional<String> name, Optional<String> surname);
}
