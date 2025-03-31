package com.app.apiTO_DO.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TareaDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    private boolean completada;
}
