package com.examenJava.domain.repository;

import java.util.List;

import com.examenJava.domain.entities.HistorialMedico;

public interface HistorialMedicoRepository {
    HistorialMedico guardar(HistorialMedico historial);
    HistorialMedico buscarPorId(int id);
    List<HistorialMedico> listarTodos();
    void eliminar(int id);
}