package com.project.service;

import com.project.entity.Product;
import com.project.repository.ProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }


    public Product saveProduct(Product product){
        return this.repository.save(product);
    }


    public Product getProductById(long id) {
        Optional<Product> optional = repository.findById(id);
        Product product = null;
        if (optional.isPresent()) {
            product = optional.get();
        } else {
            throw new RuntimeException("Product not found for id " + id);
        }
        return product;
    }


    public void deleteProductById(long id) {
        this.repository.deleteById(id);
    }


    public List<Product> getAllProduct(String keyword) {
        if (keyword != null) {
            return repository.search(keyword);
        } else
            return repository.findAll();
    }


    public Page<Product> findPaginated(int pageNo, int pageSize, String sortField, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.repository.findAll(pageable);
    }


}
