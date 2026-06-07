package com.inventario.bodaapp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String invitado;
    private String texto;

    // ¡LA ANOTACIÓN VA AQUÍ, JUSTO ARRIBA DE LA FECHA!
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaEnvio = LocalDateTime.now();
    // Dentro de tu clase Mensaje
    @Column(name = "imagen") // Opcional, pero buena práctica
    private String imagen;
    // ¡No olvides agregar sus Getters y Setters hasta abajo!
    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
