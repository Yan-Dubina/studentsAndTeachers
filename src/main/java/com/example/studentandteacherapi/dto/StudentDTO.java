package com.example.studentandteacherapi.dto;

import com.example.studentandteacherapi.domain.Student;
import com.example.studentandteacherapi.domain.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class StudentDTO {
  Long id;
  String name;
  String surname;
  String email;
  int age;
  List<TeacherDTO> teachers;
  String course;
}
