package com.kal.auth_service.service;

import com.kal.auth_service.dto.DtoCustomerFeign;
import com.kal.auth_service.dto.DtoUserLoginIn;
import com.kal.auth_service.dto.DtoUserRegisterIn;
import com.kal.auth_service.entity.User;
import com.kal.auth_service.feign.CustomerClient;
import com.kal.auth_service.repository.RepoUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final RepoUser repoUser;
    private final CustomerClient customerClient;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public void register(DtoUserRegisterIn req){
        if (repoUser.existsByEmail(req.getEmail())){
            throw new IllegalArgumentException("El email ya esta registrado");
        }

        User user = User.builder()
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .enabled(true)
                .build();

        repoUser.save(user);

        customerClient.createCustomer(new DtoCustomerFeign(
                user.getId(),
                user.getName(),
                user.getEmail()
        ));
    }

    public String login(DtoUserLoginIn req){
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
        User user = (User) auth.getPrincipal();
        return jwtService.generateToken(user);
    }
}
