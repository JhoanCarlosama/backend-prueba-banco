package com.example.backendbanco.repository;

import com.example.backendbanco.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovimientoRepository  extends JpaRepository<Movimiento, Long> {

    List<Movimiento> findByFechaBetweenAndCuentaClienteNombre(LocalDate fechaInicio, LocalDate fechaFin, String nombreCliente);
}
