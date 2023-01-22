package com.example.shop.services.impl;

import com.example.shop.domain.ShopUser;
import com.example.shop.repository.UserRepository;
import com.example.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public ShopUser getUser(String token) {
        return userRepository.getUserByToken(token);
    }

    @Override
    public Optional<Long> getOrderIdByToken(String token) {
        Optional<ShopUser> user = userRepository.getUserByTokenAndLoginIsNull(token);
        if (user.isPresent()){
            if (!Objects.isNull(user.get().productsOrder)) {
                return Optional.of(user.get().productsOrder.id);
            }
        }return Optional.empty();
    }

    @Override
    public Long updateOrderForUser(ShopUser shopUser) {
        return userRepository.save(shopUser).getId();
    }

    @Override
    public ShopUser save(ShopUser shopUser) {
        return userRepository.save(shopUser);
    }

}
