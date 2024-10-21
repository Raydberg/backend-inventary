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
    @Column
    private String name;
    @NotNull
    @Column
    private String email;
    @NotNull
    @Column(name = "is_active")
    private Boolean isActive;
    @NotNull
    @Column
    private String phone;
    private String dni;
    public String getNameAsString() {
        return name;
    }
}
