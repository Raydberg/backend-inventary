package com.project.entity;

import com.project.enums.STATUS;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_name")
    private String nameProduct;
    private String code;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Category category;
    private String description;
    private Double price;
    private Integer units;
    @Column(nullable = true,length = 255)
    private String photos;
    @Column(name = "is_active")
    private Boolean isActive;
    @Transient
    public String getPhotosImagePath() {
        if (photos == null) return null;
        return "/product-photos/" + id + "/" + photos;
    }
}
