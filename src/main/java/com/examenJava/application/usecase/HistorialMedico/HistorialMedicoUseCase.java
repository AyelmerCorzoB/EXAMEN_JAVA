package com.examenJava.application.usecase.HistorialMedico;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.examenJava.domain.entities.HistorialMedico;
import com.examenJava.domain.repository.HistorialMedicoRepository;

public class HistorialMedicoUseCase {
    private final HistorialMedicoRepository repository;

    public HistorialMedicoUseCase(HistorialMedicoRepository repository) {
        this.repository = repository;
    }

    public void registrarHistorialMedico(int pacienteId, int medicoId, Integer citaId, LocalDate fecha, 
                                      String diagnostico, String tratamiento, String observaciones) {
        HistorialMedico historial = new HistorialMedico();
        historial.setPacienteId(pacienteId);
        historial.setMedicoId(medicoId);
        historial.setCitaId(citaId);
        historial.setFecha(fecha);
        historial.setDiagnostico(diagnostico);
        historial.setTratamiento(tratamiento);
        historial.setObservaciones(observaciones);
        historial.setCreatedAt(LocalDateTime.now());

        repository.guardar(historial);
    }

    public HistorialMedico buscarHistorialMedicoPorId(int id) {
        return repository.buscarPorId(id);
    }

    public List<HistorialMedico> listarTodosLosHistorialesMedicos() {
        return repository.listarTodos();
    }

    public void actualizarHistorialMedico(int id, int pacienteId, int medicoId, Integer citaId, LocalDate fecha,
                                        String diagnostico, String tratamiento, String observaciones) {
        HistorialMedico historial = new HistorialMedico();
        historial.setId(id);
        historial.setPacienteId(pacienteId);
        historial.setMedicoId(medicoId);
        historial.setCitaId(citaId);
        historial.setFecha(fecha);
        historial.setDiagnostico(diagnostico);
        historial.setTratamiento(tratamiento);
        historial.setObservaciones(observaciones);
        historial.setCreatedAt(LocalDateTime.now());

        repository.guardar(historial);
    }

    public void eliminarHistorialMedico(int id) {
        repository.eliminar(id);
    }
}