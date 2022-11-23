package com.example.studentandteacherapi.services;

import com.example.studentandteacherapi.domain.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
  Optional<Teacher> getTeacher(Long id);

  Optional<Teacher> deleteTeacherById(Long id);

  List<Teacher> getAllTeacher(int pageNumber, int numberOfElements, Optional<String> stringOptionalField);

  Teacher createTeacher(Teacher teacher);

  Optional<Teacher> updateTeacher(Teacher teacher);

  Optional<Teacher> updateStudentListForTeacher(Long id, List<Long> studentIds);

  List<Teacher> findByNameAndSurname(Optional<String> name, Optional<String> surname);

  List<Teacher> findByNameOrSurname(Optional<String> name, Optional<String> surname);

}
