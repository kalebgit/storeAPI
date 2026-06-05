package com.kal.auth_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Datos para registrar un nuevo usuario")
public class DtoUserRegisterIn {

    @Schema(description = "Nombre completo del usuario", example = "Juan Pérez")
    private String name;

    @Schema(description = "Correo electrónico", example = "juan@ciencias.unam.mx")
    private String email;

    @Schema(description = "Contraseña", example = "mi_password_seguro")
    private String password;
}
