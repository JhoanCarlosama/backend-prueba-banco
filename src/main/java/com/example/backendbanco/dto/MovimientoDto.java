package com.example.backendbanco.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class MovimientoDto {
    private Long id;
    private String tipo;
    private LocalDateTime fecha;
    private BigDecimal valor;
    private CuentaDto cuenta;
}
