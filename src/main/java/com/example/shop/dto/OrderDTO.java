package com.example.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@AllArgsConstructor
@Data
public class OrderDTO {
    List<ProductDTO> products;
}
