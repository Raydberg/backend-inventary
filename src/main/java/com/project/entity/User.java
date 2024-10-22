package com.project.entity;

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
    private String phone;
    @Column(name = "is_active")
    private Boolean isActive;
    public String getUserAsString() {
        return name;
    }
}
