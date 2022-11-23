package com.example.studentandteacherapi.services;

import com.example.studentandteacherapi.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
  Optional<Student> getStudent(Long id);

  Optional<Student> deleteStudentById(Long id);

  List<Student> getAllStudent(int pageNumber, int numberOfElements, Optional<String> stringOptionalField);

  Student createStudent(Student student);

  Optional<Student> updateStudent(Student student);

  Optional<Student> updateTeacherListForStudent(Long id, List<Long> teacherIds);

  List<Student> findByNameAndSurname(Optional<String> name, Optional<String> surname);

  List<Student> findByNameOrSurname(Optional<String> name, Optional<String> surname);
}
