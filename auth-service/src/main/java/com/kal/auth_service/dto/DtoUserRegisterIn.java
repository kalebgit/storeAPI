package com.kal.auth_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DtoUserRegisterIn {
    private String name;
    private String email;
    private String password;
}
