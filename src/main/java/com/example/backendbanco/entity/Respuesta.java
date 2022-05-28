package com.example.backendbanco.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Respuesta {
    public Integer status;
    public String type;
    public String title;
    public String message;
    public Object data;
}
