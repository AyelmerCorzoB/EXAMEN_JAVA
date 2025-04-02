package com.examenJava.application.usecase.Medico;

import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.List;

import com.examenJava.domain.entities.Medico;
import com.examenJava.domain.repository.MedicoRepository;

public class MedicoUseCase {
    private final MedicoRepository repository;

    public MedicoUseCase(MedicoRepository repository) {
        this.repository = repository;
    }

    public void registrarMedico(String nombre, String apellido, int especialidadId, 
                              LocalTime horarioInicio, LocalTime horarioFin, 
                              String telefono, String email) {
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
    }

    public Medico buscarMedicoPorId(int id) {
        return repository.buscarPorId(id);
    }

    public List<Medico> listarTodosLosMedicos() {
        return repository.listarTodos();
    }

    public void actualizarMedico(int id, String nombre, String apellido, int especialidadId,
                               LocalTime horarioInicio, LocalTime horarioFin,
                               String telefono, String email) {
        Medico medico = new Medico();
        medico.setId(id);
        medico.setNombre(nombre);
        medico.setApellido(apellido);
        medico.setEspecialidadId(especialidadId);
        medico.setHorarioInicio(horarioInicio);
        medico.setHorarioFin(horarioFin);
        medico.setTelefono(telefono);
        medico.setEmail(email);
        medico.setUpdatedAt(LocalDateTime.now());

        repository.guardar(medico);
    }

    public void eliminarMedico(int id) {
        repository.eliminar(id);
    }
}