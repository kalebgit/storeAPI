package com.kal.auth_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Credenciales para iniciar sesión")
public class DtoUserLoginIn {

    @Schema(description = "Correo electrónico del usuario", example = "juan@ciencias.unam.mx")
    private String email;

    @Schema(description = "Contraseña del usuario", example = "mi_password_seguro")
    private String password;
}
