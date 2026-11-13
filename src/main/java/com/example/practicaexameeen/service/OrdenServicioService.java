package com.example.practicaexameeen.service;

import com.example.practicaexameeen.controller.dto.ActualizarCostoDTO;
import com.example.practicaexameeen.controller.dto.OrdenServicioDTO;
import com.example.practicaexameeen.exception.CustomException;
import com.example.practicaexameeen.model.EstadoOrden;
import com.example.practicaexameeen.model.OrdenServicio;
import com.example.practicaexameeen.model.Vehiculo;
import com.example.practicaexameeen.repository.OrdenServicioRepository;
import com.example.practicaexameeen.repository.VehiculoRepository;
import java.util.List;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class OrdenServicioService {

    private final OrdenServicioRepository ordenRepository;
    private final VehiculoRepository vehiculoRepository;

    public OrdenServicioService(OrdenServicioRepository ordenRepository, VehiculoRepository vehiculoRepository) {
        this.ordenRepository = ordenRepository;
        this.vehiculoRepository = vehiculoRepository;
    }

    public OrdenServicio crear(OrdenServicioDTO payload) {
        Vehiculo vehiculo = vehiculoRepository.findById(payload.getVehiculoId())
                .orElseThrow(() -> new CustomException("El vehiculo no existe"));
        if (!vehiculo.isActivo()) {
            throw new CustomException("El vehiculo no esta activo");
        }

        OrdenServicio orden = new OrdenServicio();
        orden.setDescripcion(payload.getDescripcion());
        orden.setTipo(payload.getTipo());
        orden.setEstado(EstadoOrden.RECIBIDA);
        orden.setCosto(0);
        orden.setVehiculo(vehiculo);
        return ordenRepository.save(orden);
    }

    public OrdenServicio finalizar(Long id) {
        OrdenServicio orden = ordenRepository.findById(id)
                .orElseThrow(() -> new CustomException("La orden no existe"));
        if (orden.getEstado() != EstadoOrden.EN_REPARACION) {
            throw new CustomException("Solo se pueden finalizar ordenes en reparacion");
        }
        if (orden.getCosto() <= 0) {
            throw new CustomException("El costo debe ser mayor a cero");
        }
        orden.setEstado(EstadoOrden.FINALIZADA);
        return ordenRepository.save(orden);
    }

    public List<OrdenServicio> listar() {
        List<OrdenServicio> listaCompleta= ordenRepository.findAll();
        return listaCompleta ;
    }

    @Transactional
    public OrdenServicio actualizar(@Valid ActualizarCostoDTO payload) {
        OrdenServicio ordenServicio = ordenRepository.findById(payload.getId()).orElseThrow(
                () -> new CustomException("no existe ")
        );

        if(payload.getNuevoCosto() <0){
            throw new CustomException("El precio no puede ser menor a 0");
        }


        return ordenRepository.save(ordenServicio);
    }
}
