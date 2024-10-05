package com.project.entity;

import com.project.enums.KARDEX_TRANSACTION;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "kardex")
public class Kardex {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private LocalDate fechaTransaccion;
    @NotNull
    private LocalDate fechaVencimiento;
    @NotNull
    private String producto;
    @NotNull
    @Enumerated(EnumType.STRING)
    private KARDEX_TRANSACTION tipoTransaccion;
    @NotNull
    private Integer cantidad;
    @NotNull
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
