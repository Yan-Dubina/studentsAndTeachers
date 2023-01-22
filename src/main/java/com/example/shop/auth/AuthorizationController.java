package com.example.shop.auth;

import com.example.shop.domain.ShopUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;
import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class AuthorizationController {

    @Autowired
    InMemoryUserDetailsManager manager;
    @Autowired
    PasswordEncoder encoder;

    @RequestMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody ShopUser user) {
        if (manager.userExists(user.getLogin())) {
            UserDetails details = manager.loadUserByUsername(user.getLogin());
            boolean isCorrect = encoder.encode(user.getPassword()).equals(details.getPassword());
            if (isCorrect) {
                return ResponseEntity.ok(true);
            }
        }
        return ResponseEntity.badRequest().body(false);

    }

    @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
                .substring("Basic".length()).trim();
        return () -> new String(Base64.getDecoder()
                .decode(authToken)).split(":")[0];
    }
}
