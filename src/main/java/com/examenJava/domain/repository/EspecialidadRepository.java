package com.examenJava.domain.repository;

import java.util.List;

import com.examenJava.domain.entities.Especialidad;

public interface EspecialidadRepository {
    Especialidad guardar(Especialidad especialidad);
    Especialidad buscarPorId(int id);
    List<Especialidad> listarTodos();
    void eliminar(int id);
}
