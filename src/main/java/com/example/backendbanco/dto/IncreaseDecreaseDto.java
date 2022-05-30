package com.example.backendbanco.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IncreaseDecreaseDto {
    private String tipo;
    private BigDecimal newBalance;
    private Long idCuenta;
}
