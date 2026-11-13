package com.example.practicaexameeen.repository;

import com.example.practicaexameeen.model.EstadoOrden;
import com.example.practicaexameeen.model.OrdenServicio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenServicioRepository extends JpaRepository<OrdenServicio, Long> {

}
