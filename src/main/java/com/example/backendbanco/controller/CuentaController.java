package com.example.backendbanco.controller;

import com.example.backendbanco.dto.CuentaDto;
import com.example.backendbanco.entity.Cliente;
import com.example.backendbanco.entity.Cuenta;
import com.example.backendbanco.entity.Respuesta;
import com.example.backendbanco.mapper.CuentaMapper;
import com.example.backendbanco.repository.ClienteRepository;
import com.example.backendbanco.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/cuenta")
public class CuentaController {
    @Autowired
    private CuentaRepository repository;

    @Autowired
    private ClienteRepository repositoryCliente;

    @Autowired
    private CuentaMapper mapper;

    @RequestMapping(value="list")
    public ResponseEntity index() {
        List<CuentaDto> listAll = new ArrayList();

        List<Cuenta> list = repository.findAll();

        list.forEach((param) -> {
            listAll.add(mapper.cuentaToCuentaDto(param));
        });

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(list);
    }

    @GetMapping(value = "/show/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Optional<Cuenta> cuenta = repository.findById(id);

        Respuesta respuesta = Respuesta.builder()
                .status(200)
                .type("success")
                .title("Perfecto!")
                .message("Se encontró la cuenta con éxito.")
                .data(cuenta)
                .build();

        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PostMapping(value = "/create-update")
    public ResponseEntity createUpdate(@RequestBody CuentaDto dto) {
        if (dto.getId() != null) {
            Cuenta cuenta = repository.findById(dto.getId()).orElse(null);
            Cliente cliente = repositoryCliente.findById(dto.getCliente().getId()).orElse(null);

            cuenta.setNumero(dto.getNumero());
            cuenta.setSaldo(dto.getSaldo());
            cuenta.setCliente(cliente);

            repository.save(cuenta);
        } else {
            repository.save(mapper.cuentaDtoToCuenta(dto));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dto);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Cuenta cuenta = repository.findById(id).orElse(null);
        repository.delete(cuenta);
        CuentaDto outDto = mapper.cuentaToCuentaDto(cuenta);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(outDto);
    }
}
