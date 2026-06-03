package com.kal.auth_service.controller;

import com.kal.auth_service.dto.DtoUserLoginIn;
import com.kal.auth_service.dto.DtoUserRegisterIn;
import com.kal.auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody DtoUserRegisterIn userIn){
        authService.register(userIn);
        //que pasa si no pongo .build()??
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    //regresamos un map pues sera un json
    public ResponseEntity<Map<String, String>> login(@RequestBody DtoUserLoginIn userIn){
        String token = authService.login(userIn);
        return ResponseEntity.ok(Map.of("token", token));
    }

}
