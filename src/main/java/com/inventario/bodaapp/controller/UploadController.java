package com.inventario.bodaapp.controller;

import com.inventario.bodaapp.service.S3Service; // Importar tu nuevo servicio
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/upload")
@CrossOrigin(origins = "*") // Cambiado para que funcione en producción (AWS)
public class UploadController {

    @Autowired
    private S3Service s3Service; // Inyectamos el poder de Amazon S3

    @PostMapping
    public ResponseEntity<?> subirImagen(@RequestParam("foto") MultipartFile archivo) {
        if (archivo.isEmpty()) {
            return ResponseEntity.badRequest().body("Archivo vacío");
        }

        try {
            // Mandamos la foto directamente a la cubeta de Amazon S3
            String urlImagenS3 = s3Service.uploadFile(archivo);

            // Le devolvemos a Angular la URL pública de Amazon
            return ResponseEntity.ok(Map.of("url", urlImagenS3));

        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error al guardar la imagen en S3");
        }
    }
}