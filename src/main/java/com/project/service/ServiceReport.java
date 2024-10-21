package com.project.service;

import com.project.entity.Kardex;
import com.project.repository.KardexRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServiceReport {

    @Autowired
    KardexRepository repositoryKardex;

    public byte[] exportReport() throws Exception {
        List<Kardex> empresaryList = repositoryKardex.findAll();

        File file = ResourceUtils.getFile("classpath:empresary_xls.jrxml");
        JasperReport jr = JasperCompileManager.compileReport(file.getAbsolutePath());

        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(empresaryList);
        Map<String, Object> hm = new HashMap<>();
        hm.put("Created By", "Learn Code With Sankalp");

        JasperPrint jp = JasperFillManager.fillReport(jr, hm, ds);

        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setExporterInput(new SimpleExporterInput(jp));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));

        SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
        configuration.setOnePagePerSheet(true);
        configuration.setDetectCellType(true);
        exporter.setConfiguration(configuration);
        exporter.exportReport();

        return baos.toByteArray();
    }
}