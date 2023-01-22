package com.example.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Data
public class RegistryRequest {
    @NotBlank
    String firtsname;
    @NotBlank
    String lastname;
    @NotBlank
    String mail;
    @NotBlank
    String username;
    @NotBlank
    String password;
    @NotBlank
    String confirmPassword;
}
