package com.example.studentandteacherapi.mappers;

import com.example.studentandteacherapi.domain.Student;
import com.example.studentandteacherapi.domain.Teacher;
import com.example.studentandteacherapi.dto.CreateTeacherRequest;
import com.example.studentandteacherapi.dto.EditTeacherRequest;
import com.example.studentandteacherapi.dto.TeacherDTO;
import com.example.studentandteacherapi.repository.StudentRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class TeacherMapper {

  private final StudentRepository studentRepository;
  private final StudentMapper studentMapper;

  public TeacherMapper(StudentRepository studentRepository, @Lazy StudentMapper studentMapper) {
    this.studentRepository = studentRepository;
    this.studentMapper = studentMapper;
  }

  public TeacherDTO domainToDTO(Teacher teacher) {
    return TeacherDTO.builder()
        .id(teacher.getId())
        .name(teacher.getName())
        .surname(teacher.getSurname())
        .email(teacher.getEmail())
        .age(teacher.getAge())
        .students(teacher.getStudents().stream().map(studentMapper::domainToDTO).collect(Collectors.toList()))
        .subject(teacher.getCourse())
        .build();
  }

  public Teacher dtoToDomain(CreateTeacherRequest teacher) {
    List<Student> students = (List<Student>) studentRepository.findAllById(teacher.getStudents());
    return Teacher.builder()
        .name(teacher.getName())
        .surname(teacher.getSurname())
        .email(teacher.getEmail())
        .age(teacher.getAge())
        .students(students)
        .course(teacher.getCourse())
        .build();
  }

  public Teacher dtoToDomain(EditTeacherRequest teacher) {
    List<Student> students = (List<Student>) studentRepository.findAllById(teacher.getStudents());
    return Teacher.builder()
        .id(teacher.getId())
        .name(teacher.getName())
        .surname(teacher.getSurname())
        .email(teacher.getEmail())
        .age(teacher.getAge())
        .students(students)
        .course(teacher.getCourse())
        .build();
  }

}
