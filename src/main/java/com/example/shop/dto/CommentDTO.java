package com.example.shop.dto;

import com.example.shop.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Builder
@AllArgsConstructor
@Data
public class CommentDTO {
    private String description;
    private String shortDescription;
    private Date date;
    private Integer rate;
}
