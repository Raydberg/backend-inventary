package com.project.entity;

import com.project.enums.STATUS;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(unique = true)
    private String name;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    @Column(name = "is_active")
    private Boolean isActive;
    @NotNull
    @Column(unique = true)
    private String phone;
    @Enumerated(EnumType.STRING)
    private STATUS status;


}
