package com.kal.auth_service.controller;

import com.kal.auth_service.dto.DtoUserLoginIn;
import com.kal.auth_service.dto.DtoUserRegisterIn;
import com.kal.auth_service.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Tag(name = "Autenticación", description = "Registro e inicio de sesión de usuarios")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Registrar un nuevo usuario", security = {})
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuario registrado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "409", description = "El email ya está registrado")
    })
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody DtoUserRegisterIn userIn) {
        authService.register(userIn);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Iniciar sesión, devuelve token JWT", security = {})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login exitoso, se devuelve el token"),
            @ApiResponse(responseCode = "401", description = "Credenciales incorrectas")
    })
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody DtoUserLoginIn userIn) {
        String token = authService.login(userIn);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
