package com.examenJava.application.usecase.Paciente;

import com.examenJava.domain.repository.PacienteRepository;

import java.util.Scanner;

public class EliminarPaciente {

    private final PacienteRepository repository;

    public EliminarPaciente(PacienteRepository repository) {
        this.repository = repository;
    }

    public void ejecutar() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Ingrese el ID del paciente a eliminar: ");
            int id = scanner.nextInt();

            if (id <= 0) {
                System.out.println("El ID del paciente debe ser mayor que cero.");
                return;
            }

            repository.eliminar(id);
        }

        System.out.println("Paciente eliminado con éxito.");
    }
}
