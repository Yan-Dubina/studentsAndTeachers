package com.example.shop.domain;

import com.example.shop.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Type type;

  private String description;

  private Long cost;

  @OneToMany
  private List<Comment> comments;

  @ManyToOne
  private Artist artist;
}
