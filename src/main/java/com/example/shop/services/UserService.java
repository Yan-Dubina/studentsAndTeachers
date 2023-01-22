package com.example.shop.services;

import com.example.shop.domain.ShopUser;

import java.util.Optional;

public interface UserService {

    ShopUser getUser(String token);
    Optional<Long> getOrderIdByToken(String token);
    Long updateOrderForUser(ShopUser shopUser);
    ShopUser save(ShopUser shopUser);

}
