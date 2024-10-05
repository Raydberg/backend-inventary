package com.project.entity;

import com.project.enums.Roles;
import com.project.enums.STATUS;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String dni;
    private String email;
    @Enumerated(EnumType.STRING)
    private Roles roles;
    private String phone;
    @Enumerated(EnumType.STRING)
    private STATUS status;
}
