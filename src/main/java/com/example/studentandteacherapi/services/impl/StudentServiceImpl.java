package com.example.studentandteacherapi.services.impl;

import com.example.studentandteacherapi.domain.Student;
import com.example.studentandteacherapi.domain.Teacher;
import com.example.studentandteacherapi.repository.StudentRepository;
import com.example.studentandteacherapi.repository.TeacherRepository;
import com.example.studentandteacherapi.services.StudentService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

  private final StudentRepository studentRepository;
  private final TeacherRepository teacherRepository;

  public StudentServiceImpl(StudentRepository studentRepository, TeacherRepository teacherRepository) {
    this.studentRepository = studentRepository;
    this.teacherRepository = teacherRepository;
  }

  @Override
  public Optional<Student> getStudent(Long id) {
    return studentRepository.findById(id);
  }

  @Override
  public Optional<Student> deleteStudentById(Long id) {
    return getStudent(id).map(student -> {
      studentRepository.deleteById(student.getId());
      return student;
    });
  }

  @Override
  public List<Student> getAllStudent(int pageNumber, int numberOfElements, Optional<String> sortBy) {
    return studentRepository
        .findAll(PageRequest.of(pageNumber, numberOfElements, Sort.by(sortBy.orElse("name"))))
        .toList();
  }

  @Override
  public Student createStudent(Student student) {
    return studentRepository.save(student);
  }

  @Override
  public Optional<Student> updateStudent(Student student) {
    return getStudent(student.getId()).map(t -> studentRepository.save(student));
  }

  @Override
  public Optional<Student> updateTeacherListForStudent(Long id, List<Long> teachersIds) {
    List<Teacher> teachers = (List<Teacher>) teacherRepository.findAllById(teachersIds);
    Assert.isTrue(teachers.size() == teachersIds.size(), "Invalid teacherIds");
    return getStudent(id).map(student -> {
      student.setTeachers(teachers);
      return studentRepository.save(student);
    });
  }

  @Override
  public List<Student> findByNameAndSurname(Optional<String> name, Optional<String> surname) {
    return studentRepository.findStudentsByNameLikeAndSurnameLike(name,surname);
  }

  @Override
  public List<Student> findByNameOrSurname(Optional<String> name, Optional<String> surname) {
    return studentRepository.findStudentsByNameLikeOrSurnameLike(name,surname);
  }

}
