package com.examenJava.application.usecase.Medico;

import com.examenJava.domain.entities.Medico;
import com.examenJava.domain.repository.MedicoRepository;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.Scanner;

public class RegistrarMedicoUseCase {
    private final MedicoRepository repository;
    private final Scanner scanner;

    public RegistrarMedicoUseCase(MedicoRepository repository, Scanner scanner) {
        this.repository = repository;
        this.scanner = scanner;
    }

    public void execute() {
        System.out.println("\n--- REGISTRAR NUEVO MÉDICO ---");
        
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        
        System.out.print("ID de especialidad: ");
        int especialidadId = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Horario de inicio (HH:MM): ");
        LocalTime horarioInicio = LocalTime.parse(scanner.nextLine());
        
        System.out.print("Horario de fin (HH:MM): ");
        LocalTime horarioFin = LocalTime.parse(scanner.nextLine());
        
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();

        Medico medico = new Medico();
        medico.setNombre(nombre);
        medico.setApellido(apellido);
        medico.setEspecialidadId(especialidadId);
        medico.setHorarioInicio(horarioInicio);
        medico.setHorarioFin(horarioFin);
        medico.setTelefono(telefono);
        medico.setEmail(email);
        medico.setCreatedAt(LocalDateTime.now());
        medico.setUpdatedAt(LocalDateTime.now());

        repository.guardar(medico);
        System.out.println("Médico registrado exitosamente!");
    }
}