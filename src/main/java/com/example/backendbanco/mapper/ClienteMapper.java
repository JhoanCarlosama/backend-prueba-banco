package com.example.backendbanco.mapper;

import com.example.backendbanco.dto.ClienteDto;
import com.example.backendbanco.entity.Cliente;
import org.springframework.stereotype.Service;

@Service
public class ClienteMapper {
    public ClienteDto clienteToClienteDto(Cliente obj) {
        ClienteDto outDto = new ClienteDto();
        outDto.setId(obj.getId());
        outDto.setNombre(obj.getNombre());
        outDto.setDireccion(obj.getDireccion());
        outDto.setTelefono(obj.getTelefono());
        return outDto;
    }

    public Cliente clienteDtoToCliente(ClienteDto dto){
        Cliente obj = new Cliente();
        obj.setId(dto.getId());
        obj.setNombre(dto.getNombre());
        obj.setDireccion(dto.getDireccion());
        obj.setTelefono(dto.getTelefono());
        return obj;
    }
}
