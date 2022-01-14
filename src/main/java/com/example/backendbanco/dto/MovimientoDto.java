package com.example.backendbanco.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MovimientoDto {
    private Long id;
    private String tipo;
    private LocalDate fecha;
    private BigDecimal valor;
    private CuentaDto cuenta;
}
