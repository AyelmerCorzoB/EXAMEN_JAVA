package com.examenJava.application.usecase.Medico;

import java.util.Scanner;

import com.examenJava.domain.repository.MedicoRepository;

public class EliminarMedico {
    private final MedicoRepository repository;

    public EliminarMedico(MedicoRepository repository) {
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
