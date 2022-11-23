package com.example.studentandteacherapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class EditStudentRequest {

  Long id;

  @NotBlank(message = "Name is required")
  @Size(min = 3, message = "Name must have more than two letters")
  String name;

  @NotBlank(message = "Surname is required")
  @Size(min = 3, message = "Surname must have more than two letters")
  String surname;

  @NotBlank(message = "Email is required")
  @Email(message = "Email is invalid")
  String email;

  @NotNull
  @Min(value = 18)
  int age;

  List<Long> teachers;

  String course;

}
