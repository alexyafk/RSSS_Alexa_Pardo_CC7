package com.example.practicaexameeen.controller;

import com.example.practicaexameeen.controller.dto.VehiculoDTO;
import com.example.practicaexameeen.model.Vehiculo;
import com.example.practicaexameeen.service.VehiculoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehiculos")
@CrossOrigin("*")
public class VehiculoController {

    private final VehiculoService servicio;

    public VehiculoController(VehiculoService servicio) {
        this.servicio = servicio;
    }

    @PostMapping
    public ResponseEntity<Vehiculo> registrar(@RequestBody @Valid VehiculoDTO payload) {
        return ResponseEntity
                .status(200)
                .body(servicio.registrar(payload));
    }

    @PutMapping("/{id}/desactivar")
    public ResponseEntity<Vehiculo> desactivar(@PathVariable @Valid Long id) {
        return ResponseEntity.status(200).body(servicio.desactivar(id));
    }
}
