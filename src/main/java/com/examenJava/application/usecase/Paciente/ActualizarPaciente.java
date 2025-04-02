package com.examenJava.application.usecase.Paciente;

import java.time.LocalDateTime;
import java.util.Scanner;

import com.examenJava.domain.entities.Paciente;
import com.examenJava.domain.repository.PacienteRepository;

public class ActualizarPaciente {
     private final PacienteRepository repository;

    public ActualizarPaciente(PacienteRepository repository) {
        this.repository = repository;
    }

    public void ejecutar() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("\nIngrese el ID del paciente a actualizar: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Ingrese el nombre del paciente: ");
            String nombre = scanner.nextLine();

            System.out.print("Ingrese el apellido del paciente: ");
            String apellido = scanner.nextLine();

            System.out.print("Ingrese la fecha de nacimiento (YYYY-MM-DD): ");
            String fechaNacimientoStr = scanner.nextLine();
            LocalDateTime fechaNacimiento = null;
            try {
                fechaNacimiento = LocalDateTime.parse(fechaNacimientoStr + "T00:00:00");
            } catch (Exception e) {
                System.out.println("Fecha de nacimiento no válida. Se utilizará la fecha actual.");
                fechaNacimiento = LocalDateTime.now();
            }

            System.out.print("Ingrese la dirección del paciente: ");
            String direccion = scanner.nextLine();

            System.out.print("Ingrese el teléfono del paciente: ");
            String telefono = scanner.nextLine();
            if (telefono == null || telefono.trim().isEmpty()) {
                throw new IllegalArgumentException("El número de teléfono es obligatorio");
            }

            System.out.print("Ingrese el email del paciente: ");
            String email = scanner.nextLine();

            Paciente paciente = new Paciente();
            paciente.setId(id);
            paciente.setNombre(nombre);
            paciente.setApellido(apellido);
            paciente.setFechaNacimiento(fechaNacimiento != null ? fechaNacimiento.toLocalDate() : null);
            paciente.setDireccion(direccion);
            paciente.setTelefono(telefono);
            paciente.setEmail(email);
            paciente.setCreatedAt(LocalDateTime.now());
            paciente.setUpdatedAt(LocalDateTime.now());

            repository.actualizar(paciente);
        }

        System.out.println("Paciente registrado con éxito.");
    }
}
