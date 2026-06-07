package com.inventario.bodaapp.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Le dice a Spring: "Si alguien pide una URL que empiece con /uploads/,
        // búscalo en la carpeta 'uploads/' del sistema de archivos local".
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }
}