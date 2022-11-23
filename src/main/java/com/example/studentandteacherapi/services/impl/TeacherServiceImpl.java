package com.example.studentandteacherapi.services.impl;

import com.example.studentandteacherapi.domain.Student;
import com.example.studentandteacherapi.domain.Teacher;
import com.example.studentandteacherapi.repository.StudentRepository;
import com.example.studentandteacherapi.repository.TeacherRepository;
import com.example.studentandteacherapi.services.TeacherService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

  private final TeacherRepository teacherRepository;
  private final StudentRepository studentRepository;

  public TeacherServiceImpl(TeacherRepository teacherRepository, StudentRepository studentRepository) {
    this.teacherRepository = teacherRepository;
    this.studentRepository = studentRepository;
  }

  @Override
  public Optional<Teacher> getTeacher(Long id) {
    return teacherRepository.findById(id);
  }

  @Override
  public Optional<Teacher> deleteTeacherById(Long id) {
    return getTeacher(id).map(teacher -> {
      teacherRepository.deleteById(teacher.getId());
      return teacher;
    });
  }

  @Override
  public List<Teacher> getAllTeacher(int pageNumber, int numberOfElements, Optional<String> sortBy) {
    return teacherRepository
        .findAll(PageRequest.of(pageNumber, numberOfElements, Sort.by(sortBy.orElse("name"))))
        .toList();
  }

  @Override
  public Teacher createTeacher(Teacher teacher) {
    return teacherRepository.save(teacher);
  }

  @Override
  public Optional<Teacher> updateTeacher(Teacher teacher) {
    return getTeacher(teacher.getId()).map(t -> teacherRepository.save(teacher));
  }

  @Override
  public Optional<Teacher> updateStudentListForTeacher(Long id, List<Long> studentIds) {
    List<Student> students = (List<Student>) studentRepository.findAllById(studentIds);
    Assert.isTrue(students.size() == studentIds.size(), "Invalid studentIds");
    return getTeacher(id).map(teacher -> {
      teacher.setStudents(students);
      return teacherRepository.save(teacher);
    });
  }

  @Override
  public List<Teacher> findByNameAndSurname(Optional<String> name, Optional<String> surname) {
    return teacherRepository.findTeachersByNameLikeAndSurnameLike(name, surname);
  }

  @Override
  public List<Teacher> findByNameOrSurname(Optional<String> name, Optional<String> surname) {
    return teacherRepository.findTeachersByNameLikeOrSurnameLike(name, surname);
  }

}
