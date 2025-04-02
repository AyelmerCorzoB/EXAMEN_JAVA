package com.examenJava.domain.entities;

import java.time.LocalDateTime;

public class Cita {
    private int id;
    private int pacienteId;
    private int medicoId;
    private LocalDateTime fechaHora;
    private String estado; // "programada", "completada", "cancelada", "no_asistio"
    private String motivo;
    private String notas;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Cita() {}

    public Cita(int pacienteId, int medicoId, LocalDateTime fechaHora) {
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.fechaHora = fechaHora;
        this.estado = "programada";
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
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

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Cita{" +
               "id=" + id +
               ", pacienteId=" + pacienteId +
               ", medicoId=" + medicoId +
               ", fechaHora=" + fechaHora +
               ", estado='" + estado + '\'' +
               '}';
    }
}