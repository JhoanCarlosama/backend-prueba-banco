package com.example.backendbanco.mapper;

import com.example.backendbanco.dto.ClienteDto;
import com.example.backendbanco.dto.CuentaDto;
import com.example.backendbanco.entity.Cliente;
import com.example.backendbanco.entity.Cuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuentaMapper {
    @Autowired
    ClienteMapper clienteMapper;
    public CuentaDto cuentaToCuentaDto(Cuenta obj) {

        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setId(obj.getCliente().getId());
        clienteDto.setNombre(obj.getCliente().getNombre());
        clienteDto.setDireccion(obj.getCliente().getDireccion());
        clienteDto.setTelefono(obj.getCliente().getTelefono());

        CuentaDto outDto = new CuentaDto();
        outDto.setId(obj.getId());
        outDto.setNumero(obj.getNumero());
        outDto.setSaldo(obj.getSaldo());
        outDto.setCliente(clienteDto);
        return outDto;
    }

    public Cuenta cuentaDtoToCuenta(CuentaDto dto){
        Cliente aux = new Cliente();
        aux.setId(dto.getCliente().getId());
        aux.setNombre(dto.getCliente().getNombre());
        aux.setDireccion(dto.getCliente().getDireccion());
        aux.setTelefono(dto.getCliente().getTelefono());

        Cuenta obj = new Cuenta();
        obj.setId(dto.getId());
        obj.setNumero(dto.getNumero());
        obj.setSaldo(dto.getSaldo());
        //obj.setCliente(clienteMapper.clienteDtoToCliente(dto.getCliente()));
        obj.setCliente(aux);
        return obj;
    }
}
