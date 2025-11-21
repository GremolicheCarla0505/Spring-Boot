package com.utn.tareas.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
@Profile("dev")
@Service
public class MensajeDevService implements MensajeService {
    @Override
    public void mostrarBienvenida() {
        System.out.println("👋 Bienvenido/a al entorno de DESARROLLO (dev)!");
        System.out.println("Este entorno es para pruebas, logs detallados y depuración.");
    }

    @Override
    public void mostrarDespedida() {
        System.out.println("👋 Hasta luego! Gracias por usar la app en modo DESARROLLO (dev).");
        System.out.println("Recuerda revisar los logs para más información.");
    }
}
