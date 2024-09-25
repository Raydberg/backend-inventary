package com.project.controller;

import com.project.entity.Supplier;
import com.project.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/api")
public class SupplierController {
    @Autowired
    SupplierService service;

    @GetMapping("/suppliers")
    public List<Supplier> findAllSuppliers() {
        return service.findAllSuppliers();
    }

    @GetMapping("/suppliers/{id}")
    public Supplier findById(@PathVariable Long id) {
        Optional<Supplier> supplierOpt = service.findOneById(id);
        return supplierOpt.orElseThrow(null);
    }

    @PostMapping("/suppliers")
    public ResponseEntity<Supplier> create(@RequestBody Supplier supplier) {
        Supplier createdSupplier = service.create(supplier);
        return ResponseEntity.ok(createdSupplier);
    }
    @PutMapping("/suppliers/{id}")
    public ResponseEntity<Supplier> update(@PathVariable Long id,@RequestBody Supplier supplier){
        Supplier updateSupplier = service.update(id,supplier);
        return ResponseEntity.ok(updateSupplier);
    }
    @DeleteMapping("/suppliers/{id}")
    public ResponseEntity<Supplier> delete(@PathVariable Long id){
        service.delete(id);
        return  ResponseEntity.noContent().build();
    }


}
