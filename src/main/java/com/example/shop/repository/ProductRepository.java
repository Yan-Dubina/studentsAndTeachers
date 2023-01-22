package com.example.shop.repository;

import com.example.shop.domain.Product;
import com.example.shop.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findProductsByType(Type type);
    Product getById(Long id);
    List<Product> findAllByIdIn(List<Long> ids);
}
