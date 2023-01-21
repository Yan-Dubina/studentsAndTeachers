package com.example.shop.repository;

import com.example.shop.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image,Long> {
    Image getImageByProductId(Long id);
    List<Image> getAllByProductIdIn(List<Long> id);
}
