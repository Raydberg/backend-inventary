package com.project.entity;

import com.project.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String username;
    private String dni;
    private String email;
    private Role role;
    private String phone;
    private Boolean isActive;
    private String password;
}