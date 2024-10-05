package com.project.repository;

import com.project.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT product FROM Product product WHERE CONCAT(product.id, ' ', product.nameProduct, ' ', product.code, ' ', product.description, ' ', product.price, ' ', product.units, ' ', product.status) LIKE %?1%")
    public List<Product> search(String keyword);

    public Product findByNameProduct(String nameProduct);
}