package com.examenJava.domain.repository;

import java.util.List;

import com.examenJava.domain.entities.Cita;

public interface CitaRepository {
    Cita guardar(Cita cita);
    Cita buscarPorId(int id);
    List<Cita> listarTodos();
    void eliminar(int id);
}