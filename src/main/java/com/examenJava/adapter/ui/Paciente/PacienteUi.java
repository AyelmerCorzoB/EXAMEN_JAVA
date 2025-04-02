package com.examenJava.adapter.ui.Paciente;

import com.examenJava.application.usecase.Paciente.*;
import com.examenJava.domain.repository.PacienteRepository;
import com.examenJava.adapter.global.ConsoleUtils;

import java.util.Scanner;

public class PacienteUi {
    private final RegistrarPaciente registrarPaciente;
    private final BuscarPacientePorId buscarPacientePorId;
    private final ListarTodosLosPacientes listarTodosLosPacientes;
    private final ActualizarPaciente actualizarPaciente;
    private final EliminarPaciente eliminarPaciente;
    private final Scanner scanner;

    public PacienteUi(PacienteRepository pacienteRepository, Scanner scanner) {
        this.registrarPaciente = new RegistrarPaciente(pacienteRepository);
        this.buscarPacientePorId = new BuscarPacientePorId(pacienteRepository);
        this.listarTodosLosPacientes = new ListarTodosLosPacientes(pacienteRepository);
        this.actualizarPaciente = new ActualizarPaciente(pacienteRepository);
        this.eliminarPaciente = new EliminarPaciente(pacienteRepository);
        this.scanner = scanner;
    }

    public void mostrarMenu() {
        while (true) {
            ConsoleUtils.clear();
            System.out.println("\n╔═══════════════════════════════════════╗");
            System.out.println("║       GESTIÓN DE PACIENTES            ║");
            System.out.println("╠═══════════════════════════════════════╣");
            System.out.println("║ 1. Registrar nuevo paciente           ║");
            System.out.println("║ 2. Buscar paciente por ID             ║");
            System.out.println("║ 3. Listar todos los pacientes         ║");
            System.out.println("║ 4. Actualizar información de paciente ║");
            System.out.println("║ 5. Eliminar paciente                  ║");
            System.out.println("║ 6. Volver al menú principal           ║");
            System.out.println("╚═══════════════════════════════════════╝");
            System.out.print("Seleccione una opción: ");

            int opcion;
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número válido.");
                ConsoleUtils.pressEnterToContinue(scanner);
                continue;
            }

            switch (opcion) {
                case 1 -> registrarPaciente();
                case 2 -> buscarPacientePorId();
                case 3 -> listarPacientes();
                case 4 -> actualizarPaciente();
                case 5 -> eliminarPaciente();
                case 6 -> { return; }
                default -> {
                    System.out.println("Opción no válida. Intente nuevamente.");
                    ConsoleUtils.pressEnterToContinue(scanner);
                }
            }
        }
    }

    private void registrarPaciente() {
        ConsoleUtils.clear();
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║       REGISTRAR NUEVO PACIENTE        ║");
        System.out.println("╚═══════════════════════════════════════╝");
        registrarPaciente.ejecutar();
        ConsoleUtils.pressEnterToContinue(scanner);
    }

    private void buscarPacientePorId() {
        ConsoleUtils.clear();
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║         BUSCAR PACIENTE POR ID         ║");
        System.out.println("╚═══════════════════════════════════════╝");
        buscarPacientePorId.ejecutar();
        ConsoleUtils.pressEnterToContinue(scanner);
    }

    private void listarPacientes() {
        ConsoleUtils.clear();
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║        LISTADO DE PACIENTES            ║");
        System.out.println("╚═══════════════════════════════════════╝");
        listarTodosLosPacientes.ejecutar();
        ConsoleUtils.pressEnterToContinue(scanner);
    }

    private void actualizarPaciente() {
        ConsoleUtils.clear();
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║        ACTUALIZAR PACIENTE             ║");
        System.out.println("╚═══════════════════════════════════════╝");
        actualizarPaciente.ejecutar();
        ConsoleUtils.pressEnterToContinue(scanner);
    }

    private void eliminarPaciente() {
        ConsoleUtils.clear();
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║          ELIMINAR PACIENTE            ║");
        System.out.println("╚═══════════════════════════════════════╝");
        eliminarPaciente.ejecutar();
        ConsoleUtils.pressEnterToContinue(scanner);
    }
}