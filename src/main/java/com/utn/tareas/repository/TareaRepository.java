package com.utn.tareas.repository;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository //Acceso a datos
public class TareaRepository {
    private List<Tarea> tareas = new ArrayList<>();
    private AtomicLong contadorId = new AtomicLong(0);
    public TareaRepository() {
        tareas.add(new Tarea(1L,  "Comprar comida", false, Prioridad.ALTA));
        tareas.add(new Tarea(2L, "Estudiar Spring Boot", false, Prioridad.MEDIA));
        tareas.add(new Tarea(3L, "Hacer ejercicio", true, Prioridad.BAJA));
    }
    //Guardar una tarea (genera ID automático)
    public Tarea guardarTarea(Tarea tarea){
        if (tarea.getId() == null){
            long nuevoId = contadorId.incrementAndGet();// genera 1, 2, 3, ...
            tarea.setId(nuevoId);
        }
        tareas.add(tarea);
        return tarea;
    }
    //Obtener todas las tareas
    public List<Tarea> obtenerTareas(){
        return tareas;
    }
    // Buscar por ID (retorna Optional<Tarea>)
    public Optional<Tarea> buscarPorId(Long id){
            return tareas.stream()
                    .filter(t -> t.getId().equals(id))
                    .findFirst();
    }
    //Eliminar por ID
    public boolean eliminarporId(Long id){
        Optional<Tarea> tareaEncontrada = buscarPorId(id);

        if(tareaEncontrada.isPresent()){
            tareas.remove(tareaEncontrada.get());
            return true;
        }
        return false;

        //otra opcion era: public boolean eliminarPorId(Long id) {
        //    return tareas.removeIf(tarea -> tarea.getId().equals(id));
        //}
    }
}
