package com.examenJava.adapter.ui.Medico;

import java.util.Scanner;

import com.examenJava.adapter.global.ConsoleUtils;
import com.examenJava.application.usecase.Medico.ActualizarMedicoUseCase;
import com.examenJava.application.usecase.Medico.BuscarMedicoPorIdUseCase;
import com.examenJava.application.usecase.Medico.EliminarMedico;
import com.examenJava.application.usecase.Medico.ListarMedicosUseCase;
import com.examenJava.application.usecase.Medico.RegistrarMedicoUseCase;
import com.examenJava.domain.repository.MedicoRepository;

public class MedicoUI {
    private final RegistrarMedicoUseCase registrarMedico;
    private final BuscarMedicoPorIdUseCase buscarMedicoPorId;
    private final ListarMedicosUseCase listarTodosLosMedicos;
    private final ActualizarMedicoUseCase actualizarMedico;
    private final EliminarMedico eliminarMedico;
    private final Scanner scanner;

    public MedicoUI(MedicoRepository medicoRepository, Scanner scanner) {
        this.registrarMedico = new RegistrarMedicoUseCase(medicoRepository, scanner);
        this.buscarMedicoPorId = new BuscarMedicoPorIdUseCase(medicoRepository, scanner);
        this.listarTodosLosMedicos = new ListarMedicosUseCase(medicoRepository, scanner);
        this.actualizarMedico = new ActualizarMedicoUseCase(medicoRepository, scanner);  
        this.eliminarMedico = new EliminarMedico(medicoRepository);
        this.scanner = scanner;
    }

    public void mostrarMenu() {
        while (true) {
            ConsoleUtils.clear();
            System.out.println("\n╔═══════════════════════════════════════╗");
            System.out.println("║       GESTIÓN DE MEDICOS            ║");
            System.out.println("╠═══════════════════════════════════════╣");
            System.out.println("║ 1. Registrar nuevo medico           ║");
            System.out.println("║ 2. Buscar medico por ID             ║");
            System.out.println("║ 3. Listar todos los medicos         ║");
            System.out.println("║ 4. Actualizar información de medico ║");
            System.out.println("║ 5. Eliminar medico                  ║");
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
                case 1 -> registrarMedico();
                case 2 -> buscarMedicoPorId();
                case 3 -> listarMedicos();
                case 4 -> actualizarMedico();
                case 5 -> eliminarMedico();
                case 6 -> { return; }
                default -> {
                    System.out.println("Opción no válida. Intente nuevamente.");
                    ConsoleUtils.pressEnterToContinue(scanner);
                }
            }
        }
    }

    private void registrarMedico() {
        ConsoleUtils.clear();
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║       REGISTRAR NUEVO MEDICO        ║");
        System.out.println("╚═══════════════════════════════════════╝");
        registrarMedico.execute();
        ConsoleUtils.pressEnterToContinue(scanner);
    }

    private void buscarMedicoPorId() {
        ConsoleUtils.clear();
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║         BUSCAR MEDICO POR ID         ║");
        System.out.println("╚═══════════════════════════════════════╝");
        buscarMedicoPorId.execute();
        ConsoleUtils.pressEnterToContinue(scanner);
    }

    private void listarMedicos() {
        ConsoleUtils.clear();
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║        LISTADO DE MEDICOS            ║");
        System.out.println("╚═══════════════════════════════════════╝");
        listarTodosLosMedicos.execute();
        ConsoleUtils.pressEnterToContinue(scanner);
    }

    private void actualizarMedico() {
        ConsoleUtils.clear();
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║        ACTUALIZAR MEDICO             ║");
        System.out.println("╚═══════════════════════════════════════╝");
        actualizarMedico.execute();
        ConsoleUtils.pressEnterToContinue(scanner);
    }

    private void eliminarMedico() {
        ConsoleUtils.clear();
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║          ELIMINAR MEDICO            ║");
        System.out.println("╚═══════════════════════════════════════╝");
        eliminarMedico.ejecutar();
        ConsoleUtils.pressEnterToContinue(scanner);
    }
}
