package com.example.studentandteacherapi;

import com.example.studentandteacherapi.domain.Student;
import com.example.studentandteacherapi.domain.Teacher;
import com.example.studentandteacherapi.repository.StudentRepository;
import com.example.studentandteacherapi.repository.TeacherRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class TestData implements ApplicationRunner {

  private StudentRepository studentRepository;
  private TeacherRepository teacherRepository;

  public TestData(StudentRepository studentRepository, TeacherRepository teacherRepository) {
    this.studentRepository = studentRepository;
    this.teacherRepository = teacherRepository;
  }

  @Override
  public void run(ApplicationArguments args) {
    Student testStudent = Student.builder()
        .name("Andry")
        .surname("Kowalski")
        .email("corsar@gmail.com")
        .age(20)
        .course("IT")
        .build();
    studentRepository.save(testStudent);

    testStudent = Student.builder()
        .name("Jonny")
        .surname("Witewski")
        .email("witold@mail.com")
        .age(20)
        .course("HR")
        .build();
    studentRepository.save(testStudent);

    testStudent = Student.builder()
        .name("Tommy")
        .surname("Java")
        .email("java@mail.ua")
        .age(20)
        .course("IT")
        .build();
    studentRepository.save(testStudent);

    Teacher teacher = Teacher.builder()
        .name("Andy")
        .surname("Smith")
        .email("java@mail.ua")
        .age(20)
        .course("IT")
        .build();
    teacherRepository.save(teacher);

    teacher = Teacher.builder()
        .name("Madison")
        .surname("Jones")
        .email("java@gmail.com")
        .age(20)
        .course("IT")
        .build();
    teacherRepository.save(teacher);

    teacher = Teacher.builder()
        .name("George")
        .surname("Davies")
        .email("spring@mail.com")
        .age(20)
        .course("IT")
        .build();

    teacherRepository.save(teacher);
  }
}

