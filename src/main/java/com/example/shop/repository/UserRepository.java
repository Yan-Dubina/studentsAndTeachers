package com.example.shop.repository;

import com.example.shop.domain.ShopUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<ShopUser,Long> {

   ShopUser getUserByToken(String token);
   Optional<ShopUser> getUserByTokenAndLoginIsNull(String token);
}
