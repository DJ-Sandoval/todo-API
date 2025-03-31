package com.app.apiTO_DO.service.implementation;

import com.app.apiTO_DO.controller.dto.TareaDTO;
import com.app.apiTO_DO.persistence.entity.Tarea;
import com.app.apiTO_DO.persistence.exception.ResourceNotFoundException;
import com.app.apiTO_DO.persistence.repository.TareaRepository;
import com.app.apiTO_DO.service.interfaces.TareaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TareaServiceImpl implements TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TareaDTO crearTarea(TareaDTO tareaDTO) {
        Tarea tarea = new Tarea();
        tarea.setTitulo(tareaDTO.getTitulo());
        tarea.setDescripcion(tareaDTO.getDescripcion());
        tarea.setCompletada(tareaDTO.isCompletada());
        // fechaCreacion se establece automáticamente en la entidad

        Tarea nuevaTarea = tareaRepository.save(tarea);
        return convertirATareaDTO(nuevaTarea);
    }

    private TareaDTO convertirATareaDTO(Tarea tarea) {
        TareaDTO tareaDTO = new TareaDTO();
        tareaDTO.setId(tarea.getId());
        tareaDTO.setTitulo(tarea.getTitulo());
        tareaDTO.setDescripcion(tarea.getDescripcion());
        tareaDTO.setCompletada(tarea.isCompletada());
        return tareaDTO;
    }

    @Override
    public List<TareaDTO> obtenerTodasLasTareas() {
        List<Tarea> tareas = tareaRepository.findAll();
        return tareas.stream()
                .map(tarea -> modelMapper.map(tarea, TareaDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TareaDTO obtenerTareaPorId(Long id) {
        Tarea tarea = tareaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarea no encontrada con id: " + id));
        return modelMapper.map(tarea, TareaDTO.class);
    }

    @Override
    public List<TareaDTO> obtenerTareasCompletadas() {
        List<Tarea> tareas = tareaRepository.findByCompletadaTrue();
        return tareas.stream()
                .map(tarea -> modelMapper.map(tarea, TareaDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TareaDTO> obtenerTareasPendientes() {
        List<Tarea> tareas = tareaRepository.findByCompletadaFalse();
        return tareas.stream()
                .map(tarea -> modelMapper.map(tarea, TareaDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TareaDTO marcarTareaComoCompletada(Long id) {
        Tarea tarea = tareaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarea no encontrada con id: " + id));
        tarea.setCompletada(true);
        Tarea tareaActualizada = tareaRepository.save(tarea);
        return modelMapper.map(tareaActualizada, TareaDTO.class);
    }

    @Override
    public void eliminarTarea(Long id) {
        Tarea tarea = tareaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarea no encontrada con id: " + id));
        tareaRepository.delete(tarea);
    }
}
