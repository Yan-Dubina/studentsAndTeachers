package com.example.shop.repository;

import com.example.shop.domain.ProductsOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<ProductsOrder, Long> {
    @Override
    Optional<ProductsOrder> findById(Long id);
}
