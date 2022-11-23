package com.example.studentandteacherapi.mappers;

import com.example.studentandteacherapi.domain.Student;
import com.example.studentandteacherapi.domain.Teacher;
import com.example.studentandteacherapi.dto.*;
import com.example.studentandteacherapi.repository.TeacherRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Scope("singleton")
@Component
public class StudentMapper {

  private final TeacherRepository teacherRepository;
  private final TeacherMapper teacherMapper;

  public StudentMapper(TeacherRepository teacherRepository, TeacherMapper teacherMapper) {
    this.teacherRepository = teacherRepository;
    this.teacherMapper = teacherMapper;
  }

  public  StudentDTO domainToDTO(Student student) {
    return StudentDTO.builder()
        .id(student.getId())
        .name(student.getName())
        .surname(student.getSurname())
        .email(student.getEmail())
        .age(student.getAge())
        .teachers(student.getTeachers().stream().map(teacherMapper::domainToDTO).collect(Collectors.toList()))
        .course(student.getCourse())
        .build();
  }

  public Student dtoToDomain(CreateStudentRequest student) {
    List<Teacher> teachers = (List<Teacher>) teacherRepository.findAllById(student.getTeachers());
    return Student.builder()
        .name(student.getName())
        .surname(student.getSurname())
        .email(student.getEmail())
        .age(student.getAge())
        .teachers(teachers)
        .course(student.getCourse())
        .build();
  }

  public Student dtoToDomain(EditStudentRequest student) {
    List<Teacher> teachers = (List<Teacher>) teacherRepository.findAllById(student.getTeachers());
    return Student.builder()
        .id(student.getId())
        .name(student.getName())
        .surname(student.getSurname())
        .email(student.getEmail())
        .age(student.getAge())
        .teachers(teachers)
        .course(student.getCourse())
        .build();
  }
}
