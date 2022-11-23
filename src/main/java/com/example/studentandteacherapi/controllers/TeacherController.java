package com.example.studentandteacherapi.controllers;


import com.example.studentandteacherapi.domain.Teacher;
import com.example.studentandteacherapi.dto.CreateTeacherRequest;
import com.example.studentandteacherapi.dto.EditTeacherRequest;
import com.example.studentandteacherapi.dto.IdsDTO;
import com.example.studentandteacherapi.dto.TeacherDTO;
import com.example.studentandteacherapi.mappers.StudentMapper;
import com.example.studentandteacherapi.mappers.TeacherMapper;
import com.example.studentandteacherapi.services.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

  private final TeacherService teacherService;
  private final TeacherMapper teacherMapper;

  public TeacherController(TeacherService teacherService, TeacherMapper teacherMapper, StudentMapper studentMapper) {
    this.teacherService = teacherService;
    this.teacherMapper = teacherMapper;
  }

  @GetMapping()
  public ResponseEntity<TeacherDTO> getTeacherById(@RequestParam Long id) {
    return teacherService.getTeacher(id).map(teacherMapper::domainToDTO)
        .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteTeacherById(@RequestParam Long id) {
    return teacherService.deleteTeacherById(id).map(teacher ->
        ResponseEntity.ok().<Void>build()).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("/find")
  public ResponseEntity<?> getTeachersPage(@RequestParam Optional<String> sortBy,
                                           @RequestParam int page,
                                           @RequestParam int numberOfElements) {
    try {
      List<TeacherDTO> teachers = teacherService.getAllTeacher(page, numberOfElements, sortBy).stream()
          .map(teacherMapper::domainToDTO).collect(Collectors.toList());
      return ResponseEntity.ok(teachers);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/search")
  public ResponseEntity<List<TeacherDTO>> getTeachersByNameAndSurname(@RequestParam Optional<String> name,
                                                                      @RequestParam Optional<String> surname,
                                                                      @RequestParam String type) {
    if (type.equals("OR")) {
      List<TeacherDTO> students = teacherService.findByNameOrSurname(name, surname).stream()
          .map(teacherMapper::domainToDTO).collect(Collectors.toList());
      return ResponseEntity.ok(students);
    } else {
      List<TeacherDTO> students = teacherService.findByNameAndSurname(name, surname).stream()
          .map(teacherMapper::domainToDTO).collect(Collectors.toList());
      return ResponseEntity.ok(students);
    }
  }

  @PostMapping
  public ResponseEntity<Void> createNewTeacher(@Valid @RequestBody CreateTeacherRequest body) {
    Teacher teacher = teacherService.createTeacher(teacherMapper.dtoToDomain(body));
    return ResponseEntity.created(URI.create("/teacher?id=" + teacher.getId())).build();
  }

  @PutMapping
  public ResponseEntity<TeacherDTO> updateTeacher(@Valid @RequestBody EditTeacherRequest body) {
    return teacherService.updateTeacher(teacherMapper.dtoToDomain(body))
        .map(teacherMapper::domainToDTO)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PutMapping("/students")
  public ResponseEntity<?> updateStudentList(@RequestParam Long id, @RequestBody IdsDTO body) {
    try {
      return teacherService
          .updateStudentListForTeacher(id, body.getIds())
          .map(teacherMapper::domainToDTO).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/students")
  public ResponseEntity<?> getStudentListForTeacher(@RequestParam Long id) {
    return teacherService.getTeacher(id)
        .map(teacherMapper::domainToDTO)
        .map(teacherDTO -> teacherDTO.getStudents().stream())
        .map(ResponseEntity::ok).orElse(ResponseEntity.ok().build());
  }
}

