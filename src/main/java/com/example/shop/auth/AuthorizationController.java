package com.example.shop.auth;

import com.example.shop.domain.ShopUser;
import com.example.shop.dto.ProductDTO;
import com.example.shop.dto.RegistryRequest;
import com.example.shop.mappers.UserMapper;
import com.example.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.security.Principal;
import java.util.Base64;

@Controller
@RequestMapping("/auth")
public class AuthorizationController {

    @Autowired
    InMemoryUserDetailsManager manager;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody String login, String password) {
        if (manager.userExists(login)) {
            UserDetails details = manager.loadUserByUsername(login);
            boolean isCorrect = encoder.encode(password).equals(details.getPassword());
            if (isCorrect) {
                return ResponseEntity.ok(true);
            }
        }
        return ResponseEntity.badRequest().body(false);

    }
    @RequestMapping("/register")
    public ResponseEntity<ShopUser> register(@RequestBody RegistryRequest user) {
        UserDetails newUser = User.withUsername(user.getUsername()).roles("USER")
                .password(encoder.encode(user.getPassword())).build();
        manager.createUser(newUser);
        return ResponseEntity.created(URI.create("/main")).body(userService.save(userMapper.createUser(user)));
    }

    @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
                .substring("Basic".length()).trim();
        return () -> new String(Base64.getDecoder()
                .decode(authToken)).split(":")[0];
    }
}
