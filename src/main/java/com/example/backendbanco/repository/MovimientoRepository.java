package com.example.backendbanco.repository;

import com.example.backendbanco.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoRepository  extends JpaRepository<Movimiento, Long> {
}
