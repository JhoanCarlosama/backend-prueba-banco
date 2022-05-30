package com.example.backendbanco.controller;

import com.example.backendbanco.dto.CuentaDto;
import com.example.backendbanco.dto.MovimientoDto;
import com.example.backendbanco.entity.Cliente;
import com.example.backendbanco.entity.Cuenta;
import com.example.backendbanco.entity.Movimiento;
import com.example.backendbanco.entity.Respuesta;
import com.example.backendbanco.mapper.MovimientoMapper;
import com.example.backendbanco.repository.CuentaRepository;
import com.example.backendbanco.repository.MovimientoRepository;
import com.example.backendbanco.utils.ExcelExporter;
import com.example.backendbanco.utils.FilterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    List<Movimiento> movimientos;

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
    public ResponseEntity<?> show(@PathVariable Long id) {
        Optional<Movimiento> movimiento = repository.findById(id);

        Respuesta respuesta = Respuesta.builder()
                .status(200)
                .type("success")
                .title("Perfecto!")
                .message("Se encontró el movimiento con éxito.")
                .data(movimiento)
                .build();

        return new ResponseEntity<>(respuesta, HttpStatus.OK);
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

    @PostMapping(value = "/search/filters")
    public void searchFilters(@RequestBody FilterRequest request, HttpServletResponse response) throws IOException {
        List<Movimiento> movimientos = repository
                .findByFechaBetweenAndCuentaClienteId(request.fechaInicio, request.fechaFin, request.idCliente);

        exportToExcel(response, movimientos);
    }

    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response, List<Movimiento> movimientos) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=movimientos_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        ExcelExporter excelExporter = new ExcelExporter(movimientos);
        excelExporter.export(response);
    }
}
