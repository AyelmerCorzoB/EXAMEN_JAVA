package com.examenJava.domain.entities;

import java.time.LocalTime;
import java.time.LocalDateTime;

public class Medico {
    private int id;
    private String nombre;
    private String apellido;
    private int especialidadId;
    private LocalTime horarioInicio;
    private LocalTime horarioFin;
    private String telefono;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Medico() {
    }

    public Medico(String nombre, String apellido, int especialidadId,
            LocalTime horarioInicio, LocalTime horarioFin) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidadId = especialidadId;
        this.horarioInicio = horarioInicio;
        this.horarioFin = horarioFin;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEspecialidadId() {
        return especialidadId;
    }

    public void setEspecialidadId(int especialidadId) {
        this.especialidadId = especialidadId;
    }

    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalTime getHorarioFin() {
        return horarioFin;
    }

    public void setHorarioFin(LocalTime horarioFin) {
        this.horarioFin = horarioFin;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        return "Medico{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", especialidadId=" + especialidadId +
                '}';
    }
}