package com.app.apiTO_DO.service.interfaces;

import com.app.apiTO_DO.controller.dto.TareaDTO;

import java.util.List;

public interface TareaService {
    TareaDTO crearTarea(TareaDTO tareaDTO);
    List<TareaDTO> obtenerTodasLasTareas();
    TareaDTO obtenerTareaPorId(Long id);
    List<TareaDTO> obtenerTareasCompletadas();
    List<TareaDTO> obtenerTareasPendientes();
    TareaDTO marcarTareaComoCompletada(Long id);
    void eliminarTarea(Long id);
}
