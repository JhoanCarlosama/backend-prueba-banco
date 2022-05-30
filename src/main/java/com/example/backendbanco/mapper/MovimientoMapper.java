package com.example.backendbanco.mapper;

import com.example.backendbanco.dto.ClienteDto;
import com.example.backendbanco.dto.CuentaDto;
import com.example.backendbanco.dto.MovimientoDto;
import com.example.backendbanco.entity.Cliente;
import com.example.backendbanco.entity.Cuenta;
import com.example.backendbanco.entity.Movimiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class MovimientoMapper {
    public MovimientoDto mvtoToMvtoDto(Movimiento obj) {
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setId(obj.getCuenta().getCliente().getId());
        clienteDto.setNombre(obj.getCuenta().getCliente().getNombre());
        clienteDto.setDireccion(obj.getCuenta().getCliente().getDireccion());
        clienteDto.setTelefono(obj.getCuenta().getCliente().getTelefono());

        CuentaDto ctaDto = new CuentaDto();
        //ctaDto.setId(obj.getCuenta().getId());
        ctaDto.setId(obj.getId());
        ctaDto.setNumero(obj.getCuenta().getNumero());
        ctaDto.setSaldo(obj.getCuenta().getSaldo());
        ctaDto.setCliente(clienteDto);

        MovimientoDto mvtoDto = new MovimientoDto();
        mvtoDto.setId(obj.getId());
        mvtoDto.setTipo(obj.getTipo());
        mvtoDto.setFecha(obj.getFecha());
        mvtoDto.setValor(obj.getValor());
        mvtoDto.setCuenta(ctaDto);

        return mvtoDto;
    }

    public Movimiento mvtoDtoToMvto(MovimientoDto dto) {
        Cliente cliente = new Cliente();
        cliente.setId(dto.getCuenta().getCliente().getId());
        cliente.setNombre(dto.getCuenta().getCliente().getNombre());
        cliente.setDireccion(dto.getCuenta().getCliente().getDireccion());
        cliente.setTelefono(dto.getCuenta().getCliente().getTelefono());

        Cuenta cta = new Cuenta();
        //cta.setId(dto.getCuenta().getId());
        cta.setId(dto.getId());
        cta.setNumero(dto.getCuenta().getNumero());
        cta.setSaldo(dto.getCuenta().getSaldo());
        cta.setCliente(cliente);

        Movimiento mvto = new Movimiento();
        mvto.setId(dto.getId());
        mvto.setTipo(dto.getTipo());
        mvto.setFecha(dto.getFecha());
        mvto.setValor(dto.getValor());
        mvto.setCuenta(cta);

        return mvto;
    }
}
