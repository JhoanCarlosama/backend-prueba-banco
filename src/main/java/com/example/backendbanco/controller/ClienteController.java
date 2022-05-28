package com.example.backendbanco.controller;

import com.example.backendbanco.dto.ClienteDto;
import com.example.backendbanco.entity.Cliente;
import com.example.backendbanco.entity.Respuesta;
import com.example.backendbanco.mapper.ClienteMapper;
import com.example.backendbanco.repository.ClienteRepository;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteRepository repository;

    @Autowired
    private ClienteMapper mapper;

    @RequestMapping(value="list")
    public ResponseEntity index() {
        List<ClienteDto> listAll = new ArrayList();

        List<Cliente> list = repository.findAll();

        list.forEach((param) -> {
            listAll.add(mapper.clienteToClienteDto(param));
        });

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(list);
    }

    @GetMapping(value = "/show/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Optional<Cliente> cliente = repository.findById(id);

        Respuesta respuesta = Respuesta.builder()
                .status(200)
                .type("success")
                .title("Perfecto!")
                .message("Se encontró el cliente con éxito.")
                .data(cliente)
                .build();
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PostMapping(value = "/create-update")
    public ResponseEntity createUpdate(@RequestBody ClienteDto dto) {
        if (dto.getId() != null) {
            Cliente cliente = repository.findById(dto.getId()).orElse(null);
            cliente.setNombre(dto.getNombre());
            cliente.setDireccion(dto.getDireccion());
            cliente.setTelefono(dto.getTelefono());
            cliente = repository.save(cliente);
        } else {
            repository.save(mapper.clienteDtoToCliente(dto));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dto);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Cliente cliente = repository.findById(id).orElse(null);
        repository.delete(cliente);
        ClienteDto outDto = mapper.clienteToClienteDto(cliente);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(outDto);
    }

    @GetMapping(value = "/search/name/{name}")
    public ResponseEntity delete(@PathVariable String name) {
        Cliente cliente = repository.findByNombre(name);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cliente);
    }
}
