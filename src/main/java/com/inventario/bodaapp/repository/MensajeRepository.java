package com.inventario.bodaapp.repository;


import com.inventario.bodaapp.entity.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
    // Obtenemos los últimos 50 mensajes para el DJ
    List<Mensaje> findTop50ByOrderByFechaEnvioDesc();
}