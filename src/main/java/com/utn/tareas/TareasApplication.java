package com.utn.tareas;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import com.utn.tareas.repository.TareaRepository;
import com.utn.tareas.service.MensajeService;
import com.utn.tareas.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TareasApplication implements CommandLineRunner {
	private final TareaService tareaService;
	private  final MensajeService mensajeService;

	public TareasApplication(TareaService tareaService, MensajeService mensajeService){
		this.tareaService = tareaService;
		this.mensajeService = mensajeService;
	}
	public static void main(String[] args) {
		SpringApplication.run(TareasApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		// acá adentro va lo que la app ejecuta al iniciar
		// 1) Mensaje de bienvenida
		mensajeService.mostrarBienvenida();
		// 2) Mostrar configuración actual
		System.out.println("\n Configuración actual ");
		tareaService.imprimirPropiedadesConfiguracion();
		// 3) Listar todas las tareas iniciales
		System.out.println("\n Tareas iniciales ");
		tareaService.listarTareas().forEach(System.out::println);
		// 4) Agregar una nueva tarea
		System.out.println("\n Agregando nueva tarea ");
		Tarea nueva = tareaService.agregarTarea(
				"Repasar teoría de Spring Boot",
				Prioridad.ALTA
		);
		System.out.println("Tarea agregada: " + nueva);
		// 5) Listar tareas pendientes
		System.out.println("Tareas Pendientes");
		tareaService.listarTareasPendientes().forEach(System.out::println);
		// 6) Marcar una tarea como completada (uso la que acabo de crear)
		System.out.println("Marcar Tarea como completada");
		tareaService.marcarTareaCompletada(nueva.getId()).ifPresentOrElse(
				t -> System.out.println("Marcada como completada: " + t),
				() -> System.out.println("No se encontró la tarea con id " + nueva.getId())
		);
		// 7) Mostrar estadísticas
		System.out.println("Estadisticas");
		System.out.println(tareaService.obtenerEstadisticas());

		// 8) Listar tareas completadas
		System.out.println("Tareas Completadas");
		tareaService.listarTareasCompletadas().forEach(System.out::println);
		// 9) Mensaje de despedida
		System.out.println();
		mensajeService.mostrarDespedida();
	}
}
