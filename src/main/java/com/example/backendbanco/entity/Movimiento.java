package com.example.backendbanco.entity;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "movimiento")
public class Movimiento {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="tipo")
    private String tipo;

    @Column(name="fecha")
    private LocalDate fecha;

    @Column(name="valor")
    private BigDecimal valor;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "id_cuenta", nullable = false)
    private Cuenta cuenta;
}
