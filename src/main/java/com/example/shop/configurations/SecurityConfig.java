//package com.example.studentandteacherapi.configurations;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//class SecurityConfig {
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user1 = User.withUsername("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("USER")
//                .roles("ADMIN")
//                .authorities("SCOPE_read", "SCOPE_write")
//                .build();
//        UserDetails user2 = User.withUsername("user")
//                .password(passwordEncoder().encode("user"))
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user1, user2);
//    }
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        http.cors()
////                .and()
////                .authorizeRequests()
////                .antMatchers(HttpMethod.GET, "/")
////                .hasAuthority("SCOPE_read")
////                .antMatchers(HttpMethod.POST, "/api/foos")
////                .hasAuthority("SCOPE_write")
////                .anyRequest()
////                .authenticated()
////                .and()
////                .oauth2ResourceServer()
////                .jwt();
//        return http.build();
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}