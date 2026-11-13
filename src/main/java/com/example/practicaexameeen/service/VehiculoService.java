package com.example.practicaexameeen.service;

import com.example.practicaexameeen.controller.dto.VehiculoDTO;
import com.example.practicaexameeen.exception.CustomException;
import com.example.practicaexameeen.model.EstadoOrden;
import com.example.practicaexameeen.model.Vehiculo;
import com.example.practicaexameeen.repository.OrdenServicioRepository;
import com.example.practicaexameeen.repository.VehiculoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;
    private final OrdenServicioRepository ordenRepository;

    public VehiculoService(VehiculoRepository vehiculoRepository, OrdenServicioRepository ordenRepository) {
        this.vehiculoRepository = vehiculoRepository;
        this.ordenRepository = ordenRepository;
    }

    public Vehiculo registrar(VehiculoDTO payload) {

        if (vehiculoRepository.existsByPlaca(payload.getPlaca())) {
            throw new CustomException("Ya existe un vehiculo con esa placa");
        }
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setPlaca(payload.getPlaca());
        vehiculo.setMarca(payload.getMarca());
        vehiculo.setModelo(payload.getModelo());
        vehiculo.setPropietario(payload.getPropietario());
        vehiculo.setActivo(true);
        return vehiculoRepository.save(vehiculo);
    }

    public Vehiculo desactivar(Long id) {
        Vehiculo vehiculo =vehiculoRepository.findById(id)
                .orElseThrow(() -> new CustomException("El vehiculo no existe"));

        vehiculo.setActivo(false);
        return vehiculoRepository.save(vehiculo);
    }
}
