package com.project.controller;

import com.project.entity.Kardex;
import com.project.service.KardexService;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ContentDisposition;
import org.springframework.web.server.ResponseStatusException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class KardexController {
    @Autowired
    private KardexService service;

    @GetMapping("/kardex")
    public List<Kardex> findAllKardex() {
        return service.findAllKardex();
    }

    @GetMapping("/kardex/{id}")
    public Kardex findById(@PathVariable Long id) {
        Optional<Kardex> kardex = service.findOneById(id);
        return kardex.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Kardex not found"));
    }

    @PostMapping("/kardex")
    public ResponseEntity<Kardex> createKardex(@RequestBody Kardex kardex) {
        Kardex createdKardex = service.create(kardex);
        return ResponseEntity.ok(createdKardex);
    }

    @PutMapping("/kardex/{id}")
    public ResponseEntity<Kardex> updateKardex(@PathVariable Long id, @RequestBody Kardex kardex) {
        Kardex updatedKardex = service.update(id, kardex);
        return ResponseEntity.ok(updatedKardex);
    }

    @DeleteMapping("/kardex/{id}")
    public ResponseEntity<Kardex> deleteKardex(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/export-pdf")
    public ResponseEntity<byte[]> exportPdf() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("BocetoProyect", "petsReport.pdf");
        return ResponseEntity.ok().headers(headers).body(service.exportPdf()); // Cambiado
    }

    @GetMapping("/export-xls")
    public ResponseEntity<byte[]> exportXls() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8");
        var contentDisposition = ContentDisposition.builder("attachment")
                .filename("BocetoProyect" + ".xls").build();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok().headers(headers).body(service.exportXls()); // Cambiado
    }
}

