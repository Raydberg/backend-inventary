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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private LocalDate dateOperation;
    @NotNull
    private LocalDate expirationDate;
    @NotNull
    @Enumerated(EnumType.STRING)
    private KARDEX_TRANSACTION tipoTransacction;
    @NotNull
    private Integer count;
    @NotNull
    private String description;
    @NotNull
    private String productName;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public String getTipoTransacctionAsString() {
        return tipoTransacction.toString();
    }
    public String getSupplierNameAsString() {
        return supplier.getNameAsString();
    }
    public String getUserAsString() {
        return user.getUserAsString();
    }
}