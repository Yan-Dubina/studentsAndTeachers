package com.example.shop.dto;

import com.example.shop.domain.Comment;
import com.example.shop.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class ProductDTO {
  private Long id;
  private Type type;
  private String description;
  private Long cost;
  private List<CommentDTO> comments;
  private ArtistDTO artist;
  private byte[] image;
}
