package com.examenJava.domain.repository;

import java.util.List;

import com.examenJava.domain.entities.Medico;

public interface MedicoRepository {
    Medico guardar(Medico medico);
    Medico buscarPorId(int id);
    List<Medico> listarTodos();
    void eliminar(int id);
}