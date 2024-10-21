package com.project.service;

import com.project.entity.Kardex;
import com.project.entity.Supplier;
import com.project.entity.User;
import com.project.repository.KardexRepository;
import com.project.repository.SupplierRepository;
import com.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class KardexService {
    @Autowired
    private KardexRepository repository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Kardex> findAllKardex() {
        return repository.findAll();
    }

    public Optional<Kardex> findOneById(Long id) {
        return repository.findById(id);
    }

    public Kardex create(Kardex kardex) {
        try {
            Supplier supplier = kardex.getSupplier();
            if (supplier != null && supplier.getId() == null) {
                supplier = supplierRepository.save(supplier);
                kardex.setSupplier(supplier);
            }

            User user = kardex.getUser();
            if (user != null && user.getId() == null) {
                user = userRepository.save(user);
                kardex.setUser(user);
            }

            return repository.save(kardex);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear el kardex", e);
        }
    }

    public Kardex update(Long id, Kardex kardex) {
        Supplier supplier = kardex.getSupplier();
        if (supplier != null && supplier.getId() == null) {
            supplier = supplierRepository.save(supplier);
            kardex.setSupplier(supplier);
        }

        User user = kardex.getUser();
        if (user != null && user.getId() == null) {
            user = userRepository.save(user);
            kardex.setUser(user);
        }

        kardex.setId(id);
        return repository.save(kardex);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}