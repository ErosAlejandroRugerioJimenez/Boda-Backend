package com.inventario.bodaapp.service;

import com.inventario.bodaapp.dto.MensajeRequest;
import com.inventario.bodaapp.entity.Mensaje;
import com.inventario.bodaapp.repository.MensajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MensajeService {

    @Autowired
    private MensajeRepository repository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public Mensaje guardarYEnviar(MensajeRequest request) {
        Mensaje mensaje = new Mensaje();
        mensaje.setInvitado(request.getInvitado());
        mensaje.setTexto(request.getTexto());
        mensaje.setImagen(request.getImagen());
        // 1. Guardamos en DB
        Mensaje guardado = repository.save(mensaje);

        // 2. Enviamos por WebSocket al canal del DJ
        messagingTemplate.convertAndSend("/topic/saludos", guardado);

        return guardado;
    }

    public List<Mensaje> obtenerHistorial() {
        return repository.findTop50ByOrderByFechaEnvioDesc();
    }
}