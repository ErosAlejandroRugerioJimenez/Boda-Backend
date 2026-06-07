package com.inventario.bodaapp.dto;


import lombok.Data;

@Data
public class MensajeRequest {
    private String invitado;
    private String texto;
    private String imagen; // 👈 ¡NUEVA! Añade esta línea para que Spring no tire el dato
}