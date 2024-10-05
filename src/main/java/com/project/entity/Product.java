package com.project.entity;

import com.project.enums.STATUS;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_name")
    private String nameProduct;
    private String code;
    @ManyToOne
    @JoinColumn(name = "category_id",nullable = false)
    private Category category;
    private String description;
    private Double price;
    private Integer units;
    @Column(nullable = true, length = 64)
    private String photos;
    @Enumerated(EnumType.STRING)
    private STATUS status;
    @Transient
    public String getPhotosImagePath() {
        if (photos == null) return null;
        return "/product-photos/" + id + "/" + photos;
    }
}
