package com.example.studentandteacherapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.List;

@Data
public class IdsDTO {
  @JsonFormat(shape = JsonFormat.Shape.ARRAY)
  List<Long> ids;
}
