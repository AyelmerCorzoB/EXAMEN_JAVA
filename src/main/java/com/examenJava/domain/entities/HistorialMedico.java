package com.examenJava.domain.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class HistorialMedico {
    private int id;
    private int pacienteId;
    private int medicoId;
    private Integer citaId;
    private LocalDate fecha;
    private String diagnostico;
    private String tratamiento;
    private String observaciones;
    private LocalDateTime createdAt;

    public HistorialMedico() {}

    public HistorialMedico(int pacienteId, int medicoId, LocalDate fecha) {
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.fecha = fecha;
        this.createdAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(int pacienteId) {
        this.pacienteId = pacienteId;
    }

    public int getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(int medicoId) {
        this.medicoId = medicoId;
    }

    public Integer getCitaId() {
        return citaId;
    }

    public void setCitaId(Integer citaId) {
        this.citaId = citaId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "HistorialMedico{" +
               "id=" + id +
               ", pacienteId=" + pacienteId +
               ", medicoId=" + medicoId +
               ", fecha=" + fecha +
               '}';
    }
}