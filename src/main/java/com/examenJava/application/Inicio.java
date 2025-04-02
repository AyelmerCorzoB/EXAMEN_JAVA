package com.examenJava.application;

import java.time.LocalDate;
import java.util.Scanner;

import com.examenJava.adapter.global.ConsoleUtils;
import com.examenJava.adapter.ui.Admin.AdminUI;
import com.examenJava.domain.entities.Paciente;
import com.examenJava.domain.entities.User;
import com.examenJava.domain.repository.PacienteRepository;
import com.examenJava.domain.service.UserService;
import com.examenJava.infrastructure.database.ConnectionDb;
import com.examenJava.infrastructure.database.ConnectionFactory;
import com.examenJava.infrastructure.persistence.PacienteRepositoryImpl;
import com.examenJava.infrastructure.persistence.UserRepositoryImpl;

public class Inicio {
    private final Scanner scanner;
    private final UserService userService;
    private User currentUser = null;
    private final PacienteRepository pacienteRepository;

    public Inicio() {
        this.scanner = new Scanner(System.in);
        ConnectionDb connection = ConnectionFactory.crearConexion();
        this.userService = new UserService(new UserRepositoryImpl(connection));
        this.pacienteRepository = new PacienteRepositoryImpl(connection);
    }

    public void start() {
        mostrarMenuPrincipal();
        scanner.close();
    }

    private void mostrarMenuPrincipal() {
        final String RESET = "\u001B[0m";
        final String CYAN_BOLD = "\u001B[1;36m";

        while (true) {
            ConsoleUtils.clear();
            System.out.println(CYAN_BOLD + "╔═════════════════════════════╗");
            System.out.println("║ Bienvenido A Citas Médicas  ║");
            System.out.println("╠═════════════════════════════╣");
            System.out.println("║ 1. Login                    ║");
            System.out.println("║ 2. Registrarse              ║");
            System.out.println("║ 3. Salir                    ║");
            System.out.println("╚═════════════════════════════╝" + RESET);
            System.out.print("Elija una opción: ");

            int option = obtenerOpcionValida(1, 3);

            switch (option) {
                case 1 -> Login();
                case 2 -> Register();
                case 3 -> {
                    System.out.println("Saliendo del sistema...");
                    return;
                }
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }

    private int obtenerOpcionValida(int min, int max) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.print("Error: No se permiten entradas vacías. Intente nuevamente: ");
                    continue;
                }
                int opcion = Integer.parseInt(input);
                if (opcion < min || opcion > max) {
                    System.out.printf("Error: Opción debe estar entre %d y %d. Intente nuevamente: ", min, max);
                    continue;
                }

                return opcion;
            } catch (NumberFormatException e) {
                System.out.print("Error: Debe ingresar un número válido. Intente nuevamente: ");
            }
        }
    }

    private void Login() {
        ConsoleUtils.clear();

        final String RESET = "\u001B[0m";
        final String CYAN_BOLD = "\u001B[1;36m";
        final String GREEN = "\u001B[32m";
        final String RED = "\u001B[91m";
        final String YELLOW = "\u001B[33m";

        System.out.println(CYAN_BOLD + "╔════════════════════════════════════╗" + RESET);
        System.out.println(
                CYAN_BOLD + "║" + YELLOW + "           INICIO DE SESIÓN         " + RESET + CYAN_BOLD + "║" + RESET);
        System.out.println(CYAN_BOLD + "╠════════════════════════════════════╣" + RESET);

        System.out.print(CYAN_BOLD + "║ " + RESET + " ° Usuario:  ");
        String nombre = scanner.nextLine();

        System.out.print(CYAN_BOLD + "║ " + RESET + " ° Contraseña: ");
        String contrasena = scanner.nextLine();
        System.out.println(CYAN_BOLD + "╚════════════════════════════════════╝" + RESET);

        User user = userService.authenticate(nombre, contrasena);

        if (user != null) {
            this.currentUser = user;
            ConsoleUtils.clear();
            System.out.println(GREEN + "╔════════════════════════════════════╗");
            System.out.println("║           LOGIN EXITOSO            ║");
            System.out.println("╠════════════════════════════════════╣");
            System.out.printf("║ %-20s: %-12s ║\n", "Bienvenido", user.getnombre().toUpperCase());
            System.out.printf("║ %-20s: %-12s ║\n", "Rol", user.getRole());
            System.out.println("╚════════════════════════════════════╝" + RESET);

            ConsoleUtils.pressEnterToContinue(scanner);
            redirigirSegunRol();
        } else {
            ConsoleUtils.clear();
            System.out.println(RED + "\n╔════════════════════════════════════╗");
            System.out.println("║    ¡¡¡¡CREDENCIALES INVÁLIDAS!!!   ║");
            System.out.println("╚════════════════════════════════════╝" + RESET);
            ConsoleUtils.pressEnterToContinue(scanner);
        }
    }

    private void redirigirSegunRol() {
        ConnectionDb connection = ConnectionFactory.crearConexion();

        switch (currentUser.getRole()) {
            case "ADMIN" -> {
                AdminUI adminUI = new AdminUI(scanner, userService, currentUser);
                adminUI.mostrarMenu();
            }
        }
        this.currentUser = null;
    }

    private void Register() {
        System.out.println("\n--- Registro de nuevo usuario ---");
        User newUser = solicitarDatosRegister();
        User registeredUser = userService.register(newUser, false);

        if (registeredUser != null) {
            System.out.println("\nRegistro de usuario exitoso! ID: " + registeredUser.getId());

            System.out.println("Ahora complete sus datos como cliente:");
            solicitarDatosPaciente(registeredUser);

            System.out.println("\nRegistro completo! Ahora puedes iniciar sesión como CONSUMER.");
        } else {
            System.out.println("\nRegistro fallido. El nombre de usuario ya está en uso.");
        }
        ConsoleUtils.pressEnterToContinue(scanner);
    }

    private User solicitarDatosRegister() {
        System.out.print("nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("contrasena: ");
        String contrasena = scanner.nextLine();

        return new User(nombre, contrasena, "CONSUMER");
    }

    private void solicitarDatosPaciente(User user) {
        System.out.print("\nPrimer Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Primer Apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("Fecha de Nacimiento (YYYY-MM-DD): ");
        String fechaNacimiento = scanner.nextLine();

        System.out.print("Teléfono: ");
        int telefono = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();

        System.out.println("Correo Electronico:");
        String email = scanner.nextLine();

        LocalDate registrationDate = LocalDate.now();

        pacienteRepository.guardarPaciente(nombre, apellido, email, fechaNacimiento,String.valueOf(telefono), direccion,
                registrationDate);
    }

    public static void main(String[] args) {
        new Inicio().start();
    }
}