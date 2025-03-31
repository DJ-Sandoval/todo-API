package com.app.apiTO_DO.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.app.apiTO_DO.controller.dto.TareaDTO;
import com.app.apiTO_DO.service.interfaces.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tareas")
@Tag(name = "Tareas", description = "Endpoints para gestionar tareas")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @PostMapping("/nueva")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('DEVELOPER') or hasRole('INVITED')")
    @Operation(summary = "Crear una nueva tarea", description = "Este endpoint permite a los usuarios crear una nueva tarea en el sistema.")
    public ResponseEntity<TareaDTO> crearTarea(@RequestBody TareaDTO tareaDTO) {
        TareaDTO nuevaTarea = tareaService.crearTarea(tareaDTO);
        return new ResponseEntity<>(nuevaTarea, HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('DEVELOPER') or hasRole('INVITED')")
    @Operation(summary = "Obtener todas las tareas", description = "Devuelve una lista con todas las tareas registradas.")
    public ResponseEntity<List<TareaDTO>> obtenerTodasLasTareas() {
        List<TareaDTO> tareas = tareaService.obtenerTodasLasTareas();
        return ResponseEntity.ok(tareas);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('DEVELOPER') or hasRole('INVITED')")
    @Operation(summary = "Obtener todas las tareas", description = "Devuelve una lista por su clave(ID)")
    public ResponseEntity<TareaDTO> obtenerTareaPorId(@PathVariable Long id) {
        TareaDTO tareaDTO = tareaService.obtenerTareaPorId(id);
        return ResponseEntity.ok(tareaDTO);
    }

    @GetMapping("/completadas")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('DEVELOPER') or hasRole('INVITED')")
    @Operation(summary = "Obtener todas las tareas completadas", description = "Devuelve una lista con todas las tareas completadas.")
    public ResponseEntity<List<TareaDTO>> obtenerTareasCompletadas() {
        List<TareaDTO> tareas = tareaService.obtenerTareasCompletadas();
        return ResponseEntity.ok(tareas);
    }

    @GetMapping("/pendientes")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('DEVELOPER') or hasRole('INVITED')")
    @Operation(summary = "Obtener todas las tareas pendientes", description = "Devuelve una lista con todas las tareas pendientes.")
    public ResponseEntity<List<TareaDTO>> obtenerTareasPendientes() {
        List<TareaDTO> tareas = tareaService.obtenerTareasPendientes();
        return ResponseEntity.ok(tareas);
    }

    @PatchMapping("/{id}/completar")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('DEVELOPER') or hasRole('INVITED')")
    @Operation(summary = "Completar una tarea", description = "Completas una tarea")
    public ResponseEntity<TareaDTO> marcarTareaComoCompletada(@PathVariable Long id) {
        TareaDTO tareaDTO = tareaService.marcarTareaComoCompletada(id);
        return ResponseEntity.ok(tareaDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('DEVELOPER') or hasRole('INVITED')")
    @Operation(summary = "Eliminar Tarea", description = "Elimina una tarea por su clave id.")
    public ResponseEntity<Void> eliminarTarea(@PathVariable Long id) {
        tareaService.eliminarTarea(id);
        return ResponseEntity.noContent().build();
    }
}
