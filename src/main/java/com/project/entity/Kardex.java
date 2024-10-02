package com.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "kardex")
public class Kardex {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private LocalDate fechaTransaccion;
    @NotNull
    private LocalDate fechaVencimiento;
    @NotNull
    private String producto;
    @NotNull
    private String tipoTransaccion;
    @NotNull
    private int cantidad;
    @NotNull
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


}
