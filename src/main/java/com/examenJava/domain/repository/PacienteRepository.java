package com.examenJava.domain.repository;

import java.time.LocalDate;
import java.util.List;

import com.examenJava.domain.entities.Paciente;

public interface PacienteRepository {
    Paciente guardar(Paciente paciente);
    Paciente buscarPorId(int id);
    List<Paciente> listarTodos();
    void eliminar(int id);
    void actualizar(Paciente paciente);
    void guardarPaciente(String nombre, String apellido,String fechaNacimiento,String email,
            String telefono, String direccion, LocalDate registrationDate);
}