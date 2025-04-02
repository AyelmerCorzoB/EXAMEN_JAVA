package com.examenJava.adapter.ui.Admin;

import com.examenJava.adapter.global.ConsoleUtils;
import com.examenJava.adapter.ui.Medico.MedicoUI;
import com.examenJava.application.usecase.Medico.*;
import com.examenJava.application.usecase.Paciente.*;
import com.examenJava.domain.entities.User;
import com.examenJava.domain.repository.*;
import com.examenJava.domain.service.UserService;
import com.examenJava.infrastructure.database.ConnectionFactory;
import com.examenJava.infrastructure.persistence.*;

import java.util.Scanner;

public class VistaAdminUI {
    private final Scanner scanner;
    private final UserService userService;
    private User currentUser;
    private int currentPage = 1;
    private static final int MAX_OPTIONS_PER_PAGE = 6;
    private static final int EXIT_OPTION = 7;

    public VistaAdminUI(Scanner scanner, UserService userService, User currentUser) {
        this.scanner = scanner;
        this.userService = userService;
        this.currentUser = currentUser;
    }

    public void start() {
        showMainMenu();
    }

    private void showMainMenu() {
        int selectedOption;
        boolean exitProgram = false;

        do {
            ConsoleUtils.clear();
            printMainMenuOptions();

            selectedOption = getValidOption(1, EXIT_OPTION);

            if (selectedOption == MAX_OPTIONS_PER_PAGE) {
                currentPage = (currentPage == 1) ? 2 : 1;
            } else if (selectedOption == EXIT_OPTION) {
                exitProgram = true;
            } else {
                handleMenuOption(selectedOption);
            }
        } while (!exitProgram);
    }

    private void printMainMenuOptions() {
        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║     VISTA DE CONTROL ADMINISTRATIVO   ║");
        System.out.println("╠═══════════════════════════════════════╣");
        System.out.printf("║ Usuario: %-28s     ║%n", currentUser.getnombre());
        System.out.printf("║ Página %d de 2 %-23s ║%n", currentPage, "");
        System.out.println("╠═══════════════════════════════════════╣");

        if (currentPage == 1) {
            printPage1Options();
        } else {
            printPage2Options();
        }
        System.out.print("\nSeleccione una opción: ");
    }

    private void printPage1Options() {
        System.out.println("║ 1. Gestión de Médicos                 ║");
        System.out.println("║ 2. Gestión de Pacientes               ║");
        System.out.println("║ 3. Gestión de Especialidades          ║");
        System.out.println("║ 4. Gestión de Citas                   ║");
        System.out.println("║ 5. Gestión de Historial de Citas      ║");
        System.out.println("║                                       ║");
        System.out.println("║ 6. Siguiente página >>                ║");
        System.out.println("║ 7. Salir                              ║");
        System.out.println("╚═══════════════════════════════════════╝");
    }

    private void printPage2Options() {
        System.out.println("║ 1. Reportes de Médicos                ║");
        System.out.println("║ 2. Reportes de Pacientes              ║");
        System.out.println("║ 3. Configuración del Sistema          ║");
        System.out.println("║ 4. Backup de Datos                    ║");
        System.out.println("║ 5. Auditoría del Sistema              ║");
        System.out.println("║                                       ║");
        System.out.println("║ 6. << Página anterior                ║");
        System.out.println("║ 7. Salir                              ║");
        System.out.println("╚═══════════════════════════════════════╝");
    }

    private void handleMenuOption(int option) {
        try {
            if (currentPage == 1) {
                handlePage1Options(option);
            } else {
                handlePage2Options(option);
            }
        } catch (Exception e) {
            System.out.println("⚠️ Error: " + e.getMessage());
            ConsoleUtils.pressEnterToContinue(scanner);
        }
    }

    private void handlePage1Options(int option) {
        switch (option) {
            case 1 -> gestionMedicos();
            case 2 -> gestionPacientes();
            case 3 -> gestionEspecialidades();
            case 4 -> gestionCitas();
            case 5 -> gestionHistorialCitas();
            default -> System.out.println("Opción no válida");
        }
        ConsoleUtils.pressEnterToContinue(scanner);
    }

    private void handlePage2Options(int option) {
        switch (option) {
            case 1 -> reportesMedicos();
            case 2 -> reportesPacientes();
            case 3 -> configuracionSistema();
            case 4 -> backupDatos();
            case 5 -> auditoriaSistema();
            default -> System.out.println("Opción no válida");
        }
        ConsoleUtils.pressEnterToContinue(scanner);
    }

    private void gestionMedicos() {
        MedicoRepository medicoRepository = new MedicoRepositoryImpl(ConnectionFactory.crearConexion());
        MedicoUI medicoUI = new MedicoUI(medicoRepository, scanner);
        medicoUI.mostrarMenu();
    }

    private void gestionPacientes() {
        PacienteRepository pacienteRepository = new PacienteRepositoryImpl(ConnectionFactory.crearConexion());
        PacienteUseCase pacienteUseCase = new PacienteUseCase(pacienteRepository);

        RegistrarPaciente registrarPaciente = new RegistrarPaciente(pacienteRepository);
        BuscarPacientePorId buscarPaciente = new BuscarPacientePorId(pacienteRepository);
        ListarTodosLosPacientes listarPacientes = new ListarTodosLosPacientes(pacienteRepository);
        ActualizarPaciente actualizarPaciente = new ActualizarPaciente(pacienteRepository);
        EliminarPaciente eliminarPaciente = new EliminarPaciente(pacienteRepository);

        mostrarMenuPacientes(registrarPaciente, buscarPaciente, listarPacientes, actualizarPaciente, eliminarPaciente);
    }

    private void mostrarMenuPacientes(RegistrarPaciente registrar, BuscarPacientePorId buscar,
            ListarTodosLosPacientes listar, ActualizarPaciente actualizar,
            EliminarPaciente eliminar) {
        boolean salir = false;

        while (!salir) {
            ConsoleUtils.clear();
            System.out.println("╔═══════════════════════════════════════╗");
            System.out.println("║       GESTIÓN DE PACIENTES             ║");
            System.out.println("╠═══════════════════════════════════════╣");
            System.out.println("║ 1. Registrar nuevo paciente            ║");
            System.out.println("║ 2. Buscar paciente por ID              ║");
            System.out.println("║ 3. Listar todos los pacientes          ║");
            System.out.println("║ 4. Actualizar paciente                 ║");
            System.out.println("║ 5. Eliminar paciente                   ║");
            System.out.println("║ 6. Volver al menú principal            ║");
            System.out.println("╚═══════════════════════════════════════╝");
            System.out.print("Seleccione una opción: ");

            int opcion = getValidOption(1, 6);

            switch (opcion) {
                case 1 -> registrar.ejecutar();
                case 2 -> buscar.ejecutar();
                case 3 -> listar.ejecutar();
                case 4 -> actualizar.ejecutar();
                case 5 -> eliminar.ejecutar();
                case 6 -> salir = true;
            }

            if (opcion != 6) {
                ConsoleUtils.pressEnterToContinue(scanner);
            }
        }
    }

    private void gestionEspecialidades() {

        System.out.println("Gestión de especialidades - En desarrollo");
    }

    private void gestionCitas() {

        System.out.println("Gestión de citas - En desarrollo");
    }

    private void gestionHistorialCitas() {

        System.out.println("Gestión de historial de citas - En desarrollo");
    }

    private void reportesMedicos() {

        System.out.println("Reportes de médicos - En desarrollo");
    }

    private void reportesPacientes() {

        System.out.println("Reportes de pacientes - En desarrollo");
    }

    private void configuracionSistema() {

        System.out.println("Configuración del sistema - En desarrollo");
    }

    private void backupDatos() {

        System.out.println("Backup de datos - En desarrollo");
    }

    private void auditoriaSistema() {

        System.out.println("Auditoría del sistema - En desarrollo");
    }

    private int getValidOption(int min, int max) {
        while (true) {
            try {
                int option = Integer.parseInt(scanner.nextLine());
                if (option >= min && option <= max) {
                    return option;
                }
                System.out.printf("Opción debe estar entre %d y %d: ", min, max);
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Ingrese un número: ");
            }
        }
    }
}