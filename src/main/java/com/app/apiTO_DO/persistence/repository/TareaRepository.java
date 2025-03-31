package com.app.apiTO_DO.persistence.repository;

import com.app.apiTO_DO.persistence.entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {
    List<Tarea> findByCompletadaTrue();
    List<Tarea> findByCompletadaFalse();
}
