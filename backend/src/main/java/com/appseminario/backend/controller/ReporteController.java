package com.appseminario.backend.controller;

import com.appseminario.backend.dto.ReporteRequest;
import com.appseminario.backend.model.Reporte;
import com.appseminario.backend.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @PostMapping
    public ResponseEntity<Reporte> crearReporte(@RequestBody ReporteRequest request) {
        Reporte guardado = reporteService.saveReporte(request);
        return ResponseEntity.ok(guardado);
    }

    @GetMapping
    public ResponseEntity<List<Reporte>> listarReportes() {
        List<Reporte> reportes = reporteService.getAllReportes();
        return ResponseEntity.ok(reportes);
    }
}
