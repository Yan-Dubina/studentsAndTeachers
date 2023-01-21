package com.example.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class ArtistDTO {

  Long id;

  String name;

  String surname;

  String email;

  int age;

  List<ProductDTO> vinyls;

  String subject;
}
