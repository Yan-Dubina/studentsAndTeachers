package com.example.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class EditArtistRequest {

  Long id;

  @NotBlank(message = "Name is required")
  @Size(min = 3, message = "Name must have more than two letters")
  String name;

  @NotNull
  @Min(value = 18)
  int age;

  List<Long> products;

}
