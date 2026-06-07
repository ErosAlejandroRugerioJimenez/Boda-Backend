package com.inventario.bodaapp.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/upload")
@CrossOrigin(origins = "http://localhost:4200")
public class UploadController {

    // La carpeta donde se guardarán las fotos (se creará en la raíz de tu proyecto)
    private static final String UPLOAD_DIR = "uploads/";

    @PostMapping
    public ResponseEntity<?> subirImagen(@RequestParam("foto") MultipartFile archivo) {
        if (archivo.isEmpty()) {
            return ResponseEntity.badRequest().body("Archivo vacío");
        }

        try {
            // 1. Crear la carpeta si no existe
            File directorio = new File(UPLOAD_DIR);
            if (!directorio.exists()) {
                directorio.mkdirs();
            }

            // 2. Generar un nombre único para la foto
            String nombreOriginal = archivo.getOriginalFilename();
            String extension = nombreOriginal.substring(nombreOriginal.lastIndexOf("."));
            String nombreNuevo = UUID.randomUUID().toString() + extension;

            // 3. Guardar el archivo en el disco duro
            Path rutaDestino = Paths.get(UPLOAD_DIR + nombreNuevo);
            Files.copy(archivo.getInputStream(), rutaDestino);

            // 4. Devolverle a Angular la ruta pública de la imagen
            String urlImagen = "/uploads/" + nombreNuevo;
            return ResponseEntity.ok(Map.of("url", urlImagen));

        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error al guardar la imagen");
        }
    }
}