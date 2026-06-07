package com.inventario.bodaapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic") // El prefijo para los canales
                .setTaskScheduler(heartBeatScheduler()) // ¡Paso 1: Asignar el scheduler que creamos abajo!
                .setHeartbeatValue(new long[] {20000, 20000}); // ¡Paso 2: 20 segundos de ida y vuelta!
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-boda")
                .setAllowedOriginPatterns("http://localhost:4200"); // Permite conexión desde Angular
    }

    // ¡Paso 3: Este Bean es el motor que mantiene los latidos corriendo en segundo plano!
    @Bean
    public TaskScheduler heartBeatScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(1); // Con un solo hilo es suficiente
        scheduler.setThreadNamePrefix("ws-heartbeat-");
        return scheduler;
    }
}