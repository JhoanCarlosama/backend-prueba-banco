package com.example.backendbanco.dto;

import lombok.Data;

@Data
public class ClienteDto {
    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;
}
