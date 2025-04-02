package com.examenJava.application.usecase.Paciente;

import com.examenJava.domain.entities.Paciente;
import com.examenJava.domain.repository.PacienteRepository;

import java.util.List;

public class ListarTodosLosPacientes {

    private final PacienteRepository repository;

    public ListarTodosLosPacientes(PacienteRepository repository) {
        this.repository = repository;
    }

    public void ejecutar() {
        List<Paciente> pacientes = repository.listarTodos();

        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
        } else {
            System.out.println("Listado de pacientes:");
            for (Paciente paciente : pacientes) {
                System.out.println("ID: " + paciente.getId() + ", Nombre: " + paciente.getNombre() + ", Apellido: " + paciente.getApellido());
            }
        }
    }
}
