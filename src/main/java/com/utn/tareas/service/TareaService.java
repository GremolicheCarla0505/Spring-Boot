package com.utn.tareas.service;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import com.utn.tareas.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service//Lógica de negocio: Clases que contienen las reglas y procesos del dominio
public class TareaService {
    private final TareaRepository repository;

    @Value("${app.nombre}")
    private String nombre;
    @Value("${app.max-tareas}")
    private Long maxTareas;
    @Value("${app.mostrar-estadisticas}")
    private boolean mostrarEstadisticas;

    @Autowired
    public TareaService(TareaRepository repo){
        this.repository = repo;
    }
    //Agregar una nueva tarea (recibe descripción y prioridad)
    public Tarea agregarTarea(String descripcion, Prioridad prioridad){
        if(repository.obtenerTareas().size() >= maxTareas){
            throw new IllegalArgumentException( "Se alcanzó el máximo de tareas permitidas: " + maxTareas);
        }
        Tarea nueva = new Tarea();
        nueva.setDescripcion(descripcion);
        nueva.setPrioridad(prioridad);
        nueva.setCompletada(false);

        return repository.guardarTarea(nueva);
    }
    //Listar todas las tareas
    public List<Tarea> listarTareas( ){
        return repository.obtenerTareas();
    }
    //Listar solo las tareas pendientes (no completadas)
    public List<Tarea> listarTareasPendientes( ) {
        return repository.obtenerTareas().stream()
                .filter(t -> !t.isCompletada())
                .toList();
    }
    //Listar solo las tareas completadas
    public List<Tarea> listarTareasCompletadas( ) {
        return repository.obtenerTareas().stream()
                .filter(t -> t.isCompletada())
                .toList();
    }
    //Marcar una tarea como completada (recibe ID)
    public Optional<Tarea> marcarTareaCompletada(Long id){
        Optional<Tarea> tareaOptional= repository.buscarPorId(id);
        if(tareaOptional.isPresent()){
            Tarea tarea = tareaOptional.get();
            tarea.setCompletada(true);
            return Optional.of(tarea);
        }
        return Optional.empty();
    }
    //Obtener estadísticas (retorna String formateado con: total, completadas, pendientes)
    public String obtenerEstadisticas(){
        List<Tarea> todas= repository.obtenerTareas();

        long total =todas.size();
        long completadas = todas.stream().filter(Tarea -> Tarea.isCompletada()).count();
        long pendientes = total - completadas;

           return String.format("Total de tareas: %d\nCompletadas: %d\nPendientes: %d",
                   total, completadas, pendientes);
    }
    //metodo que imprima las propiedades de configuración
    public void imprimirPropiedadesConfiguracion(){
        System.out.println("Las propiedades de configuracion son: " + "Nombre de la aplicación: "+ nombre +  "Máximo de tareas: " + maxTareas +"Mostrar estadísticas: " + mostrarEstadisticas );
    }
}
