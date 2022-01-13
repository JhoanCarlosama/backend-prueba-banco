package com.example.backendbanco.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CuentaDto {
    private Long id;
    private BigDecimal numero;
    private BigDecimal saldo;
    private ClienteDto cliente;
}
