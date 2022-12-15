package com.example.studentandteacherapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class VinylDTO {
  Long id;
  String name;
  String surname;
  String email;
  int age;
  List<ArtistDTO> artists;
  String course;
}
