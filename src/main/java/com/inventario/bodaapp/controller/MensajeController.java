package com.inventario.bodaapp.controller;

import com.inventario.bodaapp.dto.MensajeRequest;
import com.inventario.bodaapp.entity.Mensaje;
import com.inventario.bodaapp.service.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/mensajes")
@CrossOrigin(originPatterns = "*") // <--- ¡AQUÍ ESTÁ LA MAGIA!
public class MensajeController {

    @Autowired
    private MensajeService service;

    @PostMapping("/enviar")
    public java.util.Map<String, String> enviarMensaje(@RequestBody MensajeRequest request) {
        service.guardarYEnviar(request);
        // Le devolvemos un OK rápido al celular
        return java.util.Collections.singletonMap("status", "ok");
    }

    @GetMapping("/historial")
    public List<Mensaje> historial() {
        return service.obtenerHistorial();
    }
}