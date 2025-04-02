package com.examenJava.adapter.ui.Admin;

import com.examenJava.adapter.global.ConsoleUtils;
import com.examenJava.domain.entities.User;
import com.examenJava.domain.service.UserService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AdminUI {
    private final Scanner scanner;
    private final UserService userService;
    private final User currentUser;

    public AdminUI(Scanner scanner, UserService userService, User currentUser) {
        this.scanner = scanner;
        this.userService = userService;
        this.currentUser = currentUser;
    }

    public void mostrarMenu() {
        while (true) {
            ConsoleUtils.clear();
            System.out.println("\n╔════════════════════════════════════════════╗");
            System.out.println("║          PANEL DE ADMINISTRACIÓN           ║");
            System.out.printf("║       Usuario actual: %-23s  ║%n",
                    currentUser.getnombre() + " (" + currentUser.getRole() + ")");
            System.out.println("╠════════════════════════════════════════════╣");
            System.out.println("║ 1. Registrar nuevo usuario administrativo  ║");
            System.out.println("║ 2. Listar todos los usuarios               ║");
            System.out.println("║ 3. Vista Admin                             ║");
            System.out.println("║ 4. Cerrar sesión                           ║");
            System.out.println("╚════════════════════════════════════════════╝");
            System.out.print("Elija una opción: ");

            int option = obtenerOpcionValida();

            switch (option) {
                case 1 -> registerAdminUser();
                case 2 -> listUsuarios();
                case 4 -> {
                    return;
                }
                default -> System.out.println("Opción inválida.");
            }

            ConsoleUtils.pressEnterToContinue(scanner);
        }
    }

    private int obtenerOpcionValida(int min, int max) {
        while (true) {
            try {
                int opcion = scanner.nextInt();
                scanner.nextLine();

                if (opcion >= min && opcion <= max) {
                    return opcion;
                }
                System.out.printf("Por favor, ingrese un número entre %d y %d: ", min, max);
            } catch (InputMismatchException e) {
                System.out.print("Entrada inválida. Por favor ingrese un número: ");
                scanner.next();
            }
        }
    }

    private int obtenerOpcionValida() {
        return obtenerOpcionValida(1, 4);
    }

    private void registerAdminUser() {
        ConsoleUtils.clear();
        System.out.println("\n╔═════════════════════════════════════════╗");
        System.out.println("║    Registro de usuario administrativo   ║");
        System.out.println("╠═════════════════════════════════════════╣");

        String username;
        do {
            System.out.print("║ Username (mín. 3 caracteres): ");
            username = scanner.nextLine().trim();
        } while (username.length() < 3);

        String contrasena;
        do {
            System.out.print("║ contrasena (mín. 8 caracteres): ");
            contrasena = scanner.nextLine().trim();
        } while (contrasena.length() < 8);

        System.out.println("║ Seleccione el rol:");
        System.out.println("║ 1. ADMIN");
        System.out.println("║ 2. CASHIER");
        System.out.println("║ 3. INVENTORY");
        System.out.print("║ Opción: ");

        int opcionRol = obtenerOpcionValida(1, 3);
        String rol = switch (opcionRol) {
            case 1 -> "ADMIN";
            case 2 -> "CASHIER";
            case 3 -> "INVENTORY";
            default -> "CASHIER";
        };

        User nuevoUsuario = new User(username, contrasena, rol);
        User usuarioRegistrado = userService.register(nuevoUsuario, true);

        System.out.println("╔══════════════════════════════╗");
        System.out.println("║     Registrado con éxito!    ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.printf("║ %-15s ║ %-10s ║%n", "ID", usuarioRegistrado.getId());
        System.out.printf("║ %-15s ║ %-10s ║%n", "Usuario", usuarioRegistrado.getnombre());
        System.out.printf("║ %-15s ║ %-10s ║%n", "Rol", usuarioRegistrado.getRole());
        System.out.println("╚══════════════════════════════╝");
    }

    private void listUsuarios() {
        ConsoleUtils.clear();
        System.out.println("╔════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                         LISTADO DE USUARIOS                            ║");
        System.out.println("╠════╦══════════════════╦════════════════════╦═════════════╦═════════════╣");
        System.out.println("║ ID ║ Username         ║ Fecha Creación     ║ Rol         ║ Activo      ║");
        System.out.println("╠════╬══════════════════╬════════════════════╬═════════════╬═════════════╣");

        try {
            List<User> users = userService.getAllUsers();

            if (users.isEmpty()) {
                System.out.println("║                       No hay usuarios registrados.                      ║");
                System.out.println("╚═════════════════════════════════════════════════════════════════════════╝");
                return;
            }

            users.forEach(user -> System.out.printf(
                    "║ %-2d ║ %-16s ║ %-18s ║ %-10s  ║ %-11s ║%n",
                    user.getId(),
                    user.getnombre(),
                    user.getCreated_at().toString().substring(0, 16),
                    user.getRole(),
                    user.isActive() ? "Sí" : "No"));
            System.out.println("╚════╩══════════════════╩════════════════════╩═════════════╩═════════════╝");
            System.out.println("Total de usuarios: " + users.size());
        } catch (Exception e) {
            System.out.println(" Error al obtener la lista de usuarios: " + e.getMessage());
        }
    }
}
