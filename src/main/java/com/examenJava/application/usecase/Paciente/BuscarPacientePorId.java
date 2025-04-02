package com.examenJava.application.usecase.Paciente;

import com.examenJava.domain.entities.Paciente;
import com.examenJava.domain.repository.PacienteRepository;

import java.util.Scanner;

public class BuscarPacientePorId {

    private final PacienteRepository repository;

    public BuscarPacientePorId(PacienteRepository repository) {
        this.repository = repository;
    }

    public void ejecutar() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el ID del paciente a buscar: ");
        int id = scanner.nextInt();

        if (id <= 0) {
            System.out.println("El ID del paciente debe ser mayor que cero.");
            return;
        }

        Paciente paciente = repository.buscarPorId(id);

        if (paciente != null) {
            System.out.println("Paciente encontrado: ");
            System.out.println("ID: " + paciente.getId());
            System.out.println("Nombre: " + paciente.getNombre());
            System.out.println("Apellido: " + paciente.getApellido());
            System.out.println("Fecha de Nacimiento: " + paciente.getFechaNacimiento());
            System.out.println("Teléfono: " + paciente.getTelefono());
            System.out.println("Email: " + paciente.getEmail());
        } else {
            System.out.println("Paciente no encontrado.");
        }
    }
}
