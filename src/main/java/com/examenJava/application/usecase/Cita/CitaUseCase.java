package com.examenJava.application.usecase.Cita;

import java.time.LocalDateTime;
import java.util.List;

import com.examenJava.domain.entities.Cita;
import com.examenJava.domain.repository.CitaRepository;

public class CitaUseCase {
    private final CitaRepository repository;

    public CitaUseCase(CitaRepository repository) {
        this.repository = repository;
    }

    public void registrarCita(int pacienteId, int medicoId, LocalDateTime fechaHora, String motivo, String estado) {
        Cita cita = new Cita();
        cita.setPacienteId(pacienteId);
        cita.setMedicoId(medicoId);
        cita.setFechaHora(fechaHora);
        cita.setMotivo(motivo);
        cita.setEstado(estado);
        cita.setCreatedAt(LocalDateTime.now());
        cita.setUpdatedAt(LocalDateTime.now());

        repository.guardar(cita);
    }

    public Cita buscarCitaPorId(int id) {
        return repository.buscarPorId(id);
    }

    public List<Cita> listarTodasLasCitas() {
        return repository.listarTodos();
    }

    public void actualizarCita(int id, int pacienteId, int medicoId, LocalDateTime fechaHora, String motivo, String estado) {
        Cita cita = new Cita();
        cita.setId(id);
        cita.setPacienteId(pacienteId);
        cita.setMedicoId(medicoId);
        cita.setFechaHora(fechaHora);
        cita.setMotivo(motivo);
        cita.setEstado(estado);
        cita.setUpdatedAt(LocalDateTime.now());

        repository.guardar(cita);
    }

    public void eliminarCita(int id) {
        repository.eliminar(id);
    }
}