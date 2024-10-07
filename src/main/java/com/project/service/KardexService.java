package com.project.service;

import com.project.entity.Kardex;
import com.project.repository.KardexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KardexService {
    @Autowired
    private KardexRepository repository;

    public List<Kardex> findAllKardex() {
        return repository.findAll();
    }

    public Optional<Kardex> findOneById(Long id) {
        return repository.findById(id);
    }

    public Kardex create(Kardex kardex) {
        return repository.save(kardex);
    }

    public Kardex update(Long id, Kardex kardex) {
        kardex.setId(id);
        return repository.save(kardex);
    }
    public void delete(Long id) {
        repository.deleteById(id);
    }


}