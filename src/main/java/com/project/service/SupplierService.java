package com.project.service;

import com.project.entity.Supplier;
import com.project.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SupplierService {
    @Autowired
    SupplierRepository repository;

    public List<Supplier> findAllSuppliers(){
        return repository.findAll();
    }
    public Optional<Supplier> findOneById(Long id){
        return  repository.findById(id);
    }

    public Supplier create(Supplier supplier) {
        if (supplier.getId() != null && repository.existsById(supplier.getId())) {
            throw new RuntimeException("Supplier already exists");
        }
        return repository.save(supplier);
    }
    public Supplier update( Long id,Supplier supplier){
         supplier.setId(id);
            return repository.save(supplier);

    }

    public void delete(Long id){
        repository.deleteById(id);
    }



}
