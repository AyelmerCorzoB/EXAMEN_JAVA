package com.examenJava.application.usecase.Medico;

import com.examenJava.domain.entities.Medico;
import com.examenJava.domain.repository.MedicoRepository;
import java.util.Scanner;

public class BuscarMedicoPorIdUseCase {
    private final MedicoRepository repository;
    private final Scanner scanner;

    public BuscarMedicoPorIdUseCase(MedicoRepository repository, Scanner scanner) {
        this.repository = repository;
        this.scanner = scanner;
    }

    public void execute() {
        System.out.println("\n--- BUSCAR MÉDICO POR ID ---");
        System.out.print("Ingrese el ID del médico: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        Medico medico = repository.buscarPorId(id);
        
        if (medico != null) {
            System.out.println("\nInformación del médico:");
            System.out.println("ID: " + medico.getId());
            System.out.println("Nombre: " + medico.getNombre() + " " + medico.getApellido());
            System.out.println("Especialidad ID: " + medico.getEspecialidadId());
            System.out.println("Horario: " + medico.getHorarioInicio() + " - " + medico.getHorarioFin());
            System.out.println("Teléfono: " + medico.getTelefono());
            System.out.println("Email: " + medico.getEmail());
        } else {
            System.out.println("No se encontró ningún médico con el ID " + id);
        }
    }
}