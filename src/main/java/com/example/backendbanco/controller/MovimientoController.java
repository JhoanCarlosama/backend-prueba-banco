package com.example.backendbanco.controller;

import com.example.backendbanco.dto.CuentaDto;
import com.example.backendbanco.dto.MovimientoDto;
import com.example.backendbanco.entity.Cliente;
import com.example.backendbanco.entity.Cuenta;
import com.example.backendbanco.entity.Movimiento;
import com.example.backendbanco.mapper.MovimientoMapper;
import com.example.backendbanco.repository.CuentaRepository;
import com.example.backendbanco.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/movimiento")
public class MovimientoController {
    @Autowired
    private MovimientoRepository repository;

    @Autowired
    private CuentaRepository repositoryCta;

    @Autowired
    private MovimientoMapper mapper;

    @RequestMapping(value="list")
    public ResponseEntity index() {
        List<MovimientoDto> listAll = new ArrayList();

        List<Movimiento> list = repository.findAll();

        list.forEach((param) -> {
            listAll.add(mapper.mvtoToMvtoDto(param));
        });

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(list);
    }
    @GetMapping(value = "/show/{id}")
    public ResponseEntity show(@PathVariable Long id) {
        Optional<Movimiento> cta = repository.findById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cta);
    }

    @PostMapping(value = "/create-update")
    public ResponseEntity createUpdate(@RequestBody MovimientoDto dto) {
        if (dto.getId() != null) {
            Movimiento movimiento = repository.findById(dto.getId()).orElse(null);
            Cuenta cuenta = repositoryCta.findById(dto.getCuenta().getId()).orElse(null);

            movimiento.setTipo(dto.getTipo());
            movimiento.setFecha(dto.getFecha());
            movimiento.setValor(dto.getValor());
            movimiento.setCuenta(cuenta);

            repository.save(movimiento);
        } else {
            repository.save(mapper.mvtoDtoToMvto(dto));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dto);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Movimiento movimiento = repository.findById(id).orElse(null);
        repository.delete(movimiento);
        MovimientoDto outDto = mapper.mvtoToMvtoDto(movimiento);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(outDto);
    }
}
