package com.examenJava.application.usecase.Especialidad;

import java.time.LocalDateTime;
import java.util.List;

import com.examenJava.domain.entities.Especialidad;
import com.examenJava.domain.repository.EspecialidadRepository;

public class EspecialidadUseCase {
    private final EspecialidadRepository repository;

    public EspecialidadUseCase(EspecialidadRepository repository) {
        this.repository = repository;
    }

    public void registrarEspecialidad(String nombre, String descripcion) {
        Especialidad especialidad = new Especialidad();
        especialidad.setNombre(nombre);
        especialidad.setDescripcion(descripcion);
        especialidad.setCreatedAt(LocalDateTime.now());
        especialidad.setUpdatedAt(LocalDateTime.now());

        repository.guardar(especialidad);
    }

    public Especialidad buscarEspecialidadPorId(int id) {
        return repository.buscarPorId(id);
    }

    public List<Especialidad> listarTodasLasEspecialidades() {
        return repository.listarTodos();
    }

    public void actualizarEspecialidad(int id, String nombre, String descripcion) {
        Especialidad especialidad = new Especialidad();
        especialidad.setId(id);
        especialidad.setNombre(nombre);
        especialidad.setDescripcion(descripcion);
        especialidad.setUpdatedAt(LocalDateTime.now());

        repository.guardar(especialidad);
    }

    public void eliminarEspecialidad(int id) {
        repository.eliminar(id);
    }
}