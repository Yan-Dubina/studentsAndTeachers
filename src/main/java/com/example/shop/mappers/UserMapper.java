package com.example.shop.mappers;

import com.example.shop.domain.ShopUser;
import com.example.shop.dto.RegistryRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public ShopUser createUser(RegistryRequest req){
        return ShopUser.builder().password(req.getPassword()).login(req.getUsername()).firstname(req.getFirtsname())
                .lastname(req.getLastname()).mail(req.getMail()).build();
    }
}
