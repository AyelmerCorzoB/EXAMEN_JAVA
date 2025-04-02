package com.examenJava.application.usecase.Paciente;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.examenJava.domain.entities.Paciente;
import com.examenJava.domain.repository.PacienteRepository;

public class PacienteUseCase {
    private final PacienteRepository repository;

    public PacienteUseCase(PacienteRepository repository) {
        this.repository = repository;
    }

    public void RegistrarPaciente(String nombre, String apellido, String fechaNacimiento, String email,
            String telefono, String direccion, LocalDate registrationDate) {

        Paciente paciente = new Paciente();

        paciente.setNombre(nombre);
        paciente.setApellido(apellido);
        paciente.setFechaNacimiento(registrationDate);
        paciente.setDireccion(direccion);
        paciente.setTelefono(telefono);
        paciente.setEmail(email);
        paciente.setCreatedAt(LocalDateTime.now());
        paciente.setUpdatedAt(LocalDateTime.now());

        repository.guardar(paciente);
    }

    public Paciente BuscarPacientePorId(int id) {
        return repository.buscarPorId(id);
    }

    public List<Paciente> ListarTodosLosPacientes() {
        return repository.listarTodos();
    }

    public void ActualizarPaciente(int id, String nombre, String apellido, String fechaNacimiento, String email,
            String telefono, String direccion, LocalDate registrationDate) {
        Paciente paciente = new Paciente();
        paciente.setId(id);
        paciente.setNombre(nombre);
        paciente.setApellido(apellido);
        paciente.setFechaNacimiento(registrationDate);
        paciente.setDireccion(direccion);
        paciente.setTelefono(telefono);
        paciente.setEmail(email);
        paciente.setCreatedAt(LocalDateTime.now());
        paciente.setUpdatedAt(LocalDateTime.now());

        repository.guardar(paciente);
    }

    public void deletePaciente(int id) {
        repository.eliminar(id);
    }
}
