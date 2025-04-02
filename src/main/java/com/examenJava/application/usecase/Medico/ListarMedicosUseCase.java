package com.examenJava.application.usecase.Medico;

import com.examenJava.domain.entities.Medico;
import com.examenJava.domain.repository.MedicoRepository;
import java.util.List;
import java.util.Scanner;

public class ListarMedicosUseCase {
    private final MedicoRepository repository;
    private final Scanner scanner;

    public ListarMedicosUseCase(MedicoRepository repository, Scanner scanner) {
        this.repository = repository;
        this.scanner = scanner;
    }

    public void execute() {
        System.out.println("\n--- LISTADO DE MÉDICOS ---");
        List<Medico> medicos = repository.listarTodos();
        
        if (medicos.isEmpty()) {
            System.out.println("No hay médicos registrados.");
        } else {
            for (Medico medico : medicos) {
                System.out.println("\nID: " + medico.getId());
                System.out.println("Nombre: " + medico.getNombre() + " " + medico.getApellido());
                System.out.println("Especialidad ID: " + medico.getEspecialidadId());
                System.out.println("Horario: " + medico.getHorarioInicio() + " - " + medico.getHorarioFin());
                System.out.println("Teléfono: " + medico.getTelefono());
                System.out.println("Email: " + medico.getEmail());
                System.out.println("---------------------------------");
            }
        }
        
        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
}