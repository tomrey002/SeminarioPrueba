package com.appseminario.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appseminario.backend.dto.ReporteRequest;
import com.appseminario.backend.model.Reporte;
import com.appseminario.backend.repository.ReporteRepository;

@Service
public class ReporteService {
    
    @Autowired
    private ReporteRepository reporteRepository;

    public Reporte saveReporte(ReporteRequest reporteRequest) {
        Reporte reporte = new Reporte();
        reporte.setUsuario(reporteRequest.getUsuario());
        reporte.setComentario(reporteRequest.getComentario());
        reporte.setLatitud(reporteRequest.getLatitud());
        reporte.setLongitud(reporteRequest.getLongitud());
        
        return reporteRepository.save(reporte);
    }

    public List<Reporte> getAllReportes() {
        return reporteRepository.findAll();
    }

}
