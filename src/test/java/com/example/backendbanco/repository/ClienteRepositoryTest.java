package com.example.backendbanco.repository;

import com.example.backendbanco.entity.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository repository;

    @Test
    void saveTest() {
        Cliente cliente =  new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Pepe Perez");
        cliente.setDireccion("Casa 12");
        cliente.setTelefono("3212222");

        repository.save(cliente);
        Assertions.assertNotEquals(cliente.getId(), 0);
    }
}
