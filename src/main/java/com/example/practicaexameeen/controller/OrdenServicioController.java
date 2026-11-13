package com.example.practicaexameeen.controller;

import com.example.practicaexameeen.controller.dto.ActualizarCostoDTO;
import com.example.practicaexameeen.controller.dto.OrdenServicioDTO;
import com.example.practicaexameeen.model.OrdenServicio;
import com.example.practicaexameeen.service.OrdenServicioService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ordenes")
@CrossOrigin("*")
public class OrdenServicioController {

    private final OrdenServicioService servicio;

    public OrdenServicioController(OrdenServicioService servicio) {
        this.servicio = servicio;
    }

    @PostMapping
    public ResponseEntity<OrdenServicio> crear(@RequestBody @Valid OrdenServicioDTO payload) {
        return ResponseEntity
                .status(200)
                .body(servicio.crear(payload));
    }

    @PutMapping("/{id}/finalizar")
    public ResponseEntity<OrdenServicio> finalizar(@PathVariable Long id) {
        return ResponseEntity.status(200).body(servicio.finalizar(id));
    }

    @GetMapping
    public ResponseEntity<List<OrdenServicio>> listar() {
        return ResponseEntity.status(200).body(servicio.listar());
    }

    @PutMapping
    public ResponseEntity<OrdenServicio> actualizar(@RequestBody @Valid ActualizarCostoDTO payload){
        return ResponseEntity.status(200).body(servicio.actualizar(payload));
    }
}
