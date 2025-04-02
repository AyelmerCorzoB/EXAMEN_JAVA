package com.examenJava.domain.repository;

import java.util.List;

import com.examenJava.domain.entities.Paciente;

public interface PacienteRepository {
    Paciente guardar(Paciente paciente);
    Paciente buscarPorId(int id);
    List<Paciente> listarTodos();
    void eliminar(int id);
}