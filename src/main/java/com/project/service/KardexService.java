package com.project.service;


import com.project.Util.ReportGeneratedKardex;
import com.project.entity.Kardex;
import com.project.repository.KardexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class KardexService {
    @Autowired
    private KardexRepository repository; // Asegúrate de que esté inyectado correctamente

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

    public byte[] exportPdf() {
        try {
            return ReportGeneratedKardex.exportToPdf(repository.findAll());
        } catch (JRException | FileNotFoundException e) {
            // Manejo de excepciones (log o lanzar una RuntimeException)
            throw new RuntimeException("Error al exportar a PDF", e);
        }
    }

    public byte[] exportXls() {
        try {
            return ReportGeneratedKardex.exportToXls(repository.findAll());
        } catch (JRException | FileNotFoundException e) {
            // Manejo de excepciones (log o lanzar una RuntimeException)
            throw new RuntimeException("Error al exportar a XLS", e);
        }
    }
}