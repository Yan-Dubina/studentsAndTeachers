package com.example.studentandteacherapi.controllers;

import com.example.studentandteacherapi.domain.Student;
import com.example.studentandteacherapi.dto.CreateStudentRequest;
import com.example.studentandteacherapi.dto.EditStudentRequest;
import com.example.studentandteacherapi.dto.IdsDTO;
import com.example.studentandteacherapi.dto.StudentDTO;
import com.example.studentandteacherapi.mappers.StudentMapper;
import com.example.studentandteacherapi.services.StudentService;
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
@RequestMapping("/student")
public class  StudentController {

  private final StudentMapper studentMapper;
  private final StudentService studentService;

  public StudentController(StudentService studentService, StudentMapper studentMapper) {
    this.studentService = studentService;
    this.studentMapper = studentMapper;
  }

  @GetMapping()
  public ResponseEntity<StudentDTO> getTeacherById(@RequestParam Long id) {
    Optional<StudentDTO> student = studentService.getStudent(id).map(studentMapper::domainToDTO);
    return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteStudentById(@RequestParam Long id) {
    return studentService.deleteStudentById(id).map(teacher ->
        ResponseEntity.ok().<Void>build()).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("/find")
  public ResponseEntity<List<StudentDTO>> getStudentPage(@RequestParam int page,
                                                         @RequestParam int numberOfElements,
                                                         @RequestParam Optional<String> sortBy) {
    List<StudentDTO> students = studentService.getAllStudent(page, numberOfElements, sortBy).stream()
        .map(studentMapper::domainToDTO).collect(Collectors.toList());
    return ResponseEntity.ok(students);
  }

  @GetMapping("/search")
  public ResponseEntity<List<StudentDTO>> getStudentsByNameOrSurname(@RequestParam Optional<String> name,
                                                                     @RequestParam Optional<String> surname,
                                                                     @RequestParam String type) {
    if (type.equals("OR")) {
      List<StudentDTO> students = studentService.findByNameOrSurname(name, surname).stream()
          .map(studentMapper::domainToDTO).collect(Collectors.toList());
      return ResponseEntity.ok(students);
    } else {
      List<StudentDTO> students = studentService.findByNameAndSurname(name, surname).stream()
          .map(studentMapper::domainToDTO).collect(Collectors.toList());
      return ResponseEntity.ok(students);
    }
  }

  @PostMapping
  public ResponseEntity<Void> createNewStudent(@Valid @RequestBody CreateStudentRequest body) {
    Student teacher = studentService.createStudent(studentMapper.dtoToDomain(body));
    return ResponseEntity.created(URI.create("/student?id=" + teacher.getId())).build();
  }

  @PutMapping
  public ResponseEntity<Student> updateStudent(@Valid @RequestBody EditStudentRequest body) {
    return studentService.updateStudent(studentMapper.dtoToDomain(body))
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PutMapping("/teacher")
  public ResponseEntity<?> updateTeacherList(@RequestParam Long id, @RequestBody IdsDTO teacherIDs) {
    try {
      return studentService.updateTeacherListForStudent(id, teacherIDs.getIds())
          .map(studentMapper::domainToDTO).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/teacher")
  public ResponseEntity<?> getStudentListForTeacher(@RequestParam Long id) {
    return studentService.getStudent(id)
        .map(studentMapper::domainToDTO)
        .map(studentDTO -> studentDTO.getTeachers().stream())
        .map(ResponseEntity::ok).orElse(ResponseEntity.ok().build());
  }
}
