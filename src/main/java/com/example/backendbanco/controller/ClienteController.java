package com.example.backendbanco.controller;

import com.example.backendbanco.dto.ClienteDto;
import com.example.backendbanco.entity.Cliente;
import com.example.backendbanco.mapper.ClienteMapper;
import com.example.backendbanco.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
}
