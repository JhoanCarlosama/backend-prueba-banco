package com.example.backendbanco.utils;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FilterRequest {
    public String nombreCliente;
    public LocalDate fechaInicio;
    public LocalDate fechaFin;
}
