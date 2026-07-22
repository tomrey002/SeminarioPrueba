package com.appseminario.backend.dto;
import lombok.Data;

@Data
public class ReporteRequest {
    private String usuario;
    private String comentario;
    private Double latitud;
    private Double longitud;
}
