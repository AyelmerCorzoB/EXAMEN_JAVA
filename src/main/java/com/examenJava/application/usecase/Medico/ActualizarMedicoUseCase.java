package com.examenJava.application.usecase.Medico;

import com.examenJava.domain.entities.Medico;
import com.examenJava.domain.repository.MedicoRepository;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ActualizarMedicoUseCase {
    private final MedicoRepository repository;
    private final Scanner scanner;

    public ActualizarMedicoUseCase(MedicoRepository repository, Scanner scanner) {
        this.repository = repository;
        this.scanner = scanner;
    }

    public void execute() {
        System.out.println("\n--- ACTUALIZAR MÉDICO ---");
        System.out.print("Ingrese el ID del médico a actualizar: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        Medico medicoExistente = repository.buscarPorId(id);
        
        if (medicoExistente == null) {
            System.out.println("No se encontró ningún médico con el ID " + id);
            return;
        }
        
        System.out.println("\nDatos actuales:");
        System.out.println("Nombre: " + medicoExistente.getNombre());
        System.out.println("Apellido: " + medicoExistente.getApellido());
        System.out.println("Especialidad ID: " + medicoExistente.getEspecialidadId());
        System.out.println("Horario: " + medicoExistente.getHorarioInicio() + " - " + medicoExistente.getHorarioFin());
        System.out.println("Teléfono: " + medicoExistente.getTelefono());
        System.out.println("Email: " + medicoExistente.getEmail());
        
        System.out.println("\nIngrese los nuevos datos (deje en blanco para mantener el valor actual):");
        
        System.out.print("Nombre [" + medicoExistente.getNombre() + "]: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Apellido [" + medicoExistente.getApellido() + "]: ");
        String apellido = scanner.nextLine();
        
        System.out.print("Especialidad ID [" + medicoExistente.getEspecialidadId() + "]: ");
        String especialidadStr = scanner.nextLine();
        int especialidadId = especialidadStr.isEmpty() ? medicoExistente.getEspecialidadId() : Integer.parseInt(especialidadStr);
        
        System.out.print("Horario de inicio [" + medicoExistente.getHorarioInicio() + "]: ");
        String horarioInicioStr = scanner.nextLine();
        LocalTime horarioInicio = horarioInicioStr.isEmpty() ? medicoExistente.getHorarioInicio() : LocalTime.parse(horarioInicioStr);
        
        System.out.print("Horario de fin [" + medicoExistente.getHorarioFin() + "]: ");
        String horarioFinStr = scanner.nextLine();
        LocalTime horarioFin = horarioFinStr.isEmpty() ? medicoExistente.getHorarioFin() : LocalTime.parse(horarioFinStr);
        
        System.out.print("Teléfono [" + medicoExistente.getTelefono() + "]: ");
        String telefono = scanner.nextLine();
        
        System.out.print("Email [" + medicoExistente.getEmail() + "]: ");
        String email = scanner.nextLine();

        Medico medico = new Medico();
        medico.setId(id);
        medico.setNombre(nombre.isEmpty() ? medicoExistente.getNombre() : nombre);
        medico.setApellido(apellido.isEmpty() ? medicoExistente.getApellido() : apellido);
        medico.setEspecialidadId(especialidadId);
        medico.setHorarioInicio(horarioInicio);
        medico.setHorarioFin(horarioFin);
        medico.setTelefono(telefono.isEmpty() ? medicoExistente.getTelefono() : telefono);
        medico.setEmail(email.isEmpty() ? medicoExistente.getEmail() : email);
        medico.setUpdatedAt(LocalDateTime.now());

        repository.guardar(medico);
        System.out.println("Médico actualizado exitosamente!");
    }
}