package com.examenJava.infrastructure.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.examenJava.domain.entities.Medico;
import com.examenJava.domain.repository.MedicoRepository;
import com.examenJava.infrastructure.database.ConnectionDb;

public class MedicoRepositoryImpl implements MedicoRepository {

    private final ConnectionDb connection;

    public MedicoRepositoryImpl(ConnectionDb connection) {
        this.connection = connection;
    }

    @Override
    public Medico guardar(Medico medico) {
        if (medico.getNombre() == null || medico.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del médico es obligatorio");
        }
        if (medico.getApellido() == null || medico.getApellido().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido del médico es obligatorio");
        }
        if (medico.getHorarioInicio() == null || medico.getHorarioFin() == null) {
            throw new IllegalArgumentException("El horario de atención es obligatorio");
        }

        if (medico.getCreatedAt() == null) {
            medico.setCreatedAt(LocalDateTime.now());
        }
        medico.setUpdatedAt(LocalDateTime.now());

        String sql = "INSERT INTO Medico (nombre, apellido, especialidad_id, horario_inicio, horario_fin, telefono, email, created_at, updated_at) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connection.getConexion();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, medico.getNombre());
            stmt.setString(2, medico.getApellido());
            stmt.setInt(3, medico.getEspecialidadId());
            stmt.setTime(4, Time.valueOf(medico.getHorarioInicio()));
            stmt.setTime(5, Time.valueOf(medico.getHorarioFin()));
            stmt.setString(6, medico.getTelefono());
            stmt.setString(7, medico.getEmail());
            stmt.setTimestamp(8, Timestamp.valueOf(medico.getCreatedAt()));
            stmt.setTimestamp(9, Timestamp.valueOf(medico.getUpdatedAt()));

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating doctor failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    medico.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating doctor failed, no ID obtained.");
                }
            }
            
            return medico;
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el médico: " + e.getMessage(), e);
        }
    }

    @Override
    public Medico buscarPorId(int id) {
        String sql = "SELECT * FROM Medico WHERE id = ?";

        try (Connection conn = connection.getConexion();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Medico medico = new Medico();
                medico.setId(rs.getInt("id"));
                medico.setNombre(rs.getString("nombre"));
                medico.setApellido(rs.getString("apellido"));
                medico.setEspecialidadId(rs.getInt("especialidad_id"));
                
                Time horarioInicio = rs.getTime("horario_inicio");
                medico.setHorarioInicio(horarioInicio != null ? horarioInicio.toLocalTime() : null);
                
                Time horarioFin = rs.getTime("horario_fin");
                medico.setHorarioFin(horarioFin != null ? horarioFin.toLocalTime() : null);
                
                medico.setTelefono(rs.getString("telefono"));
                medico.setEmail(rs.getString("email"));
                
                Timestamp createdAt = rs.getTimestamp("created_at");
                medico.setCreatedAt(createdAt != null ? createdAt.toLocalDateTime() : null);
                
                Timestamp updatedAt = rs.getTimestamp("updated_at");
                medico.setUpdatedAt(updatedAt != null ? updatedAt.toLocalDateTime() : null);
                
                return medico;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar médico por ID: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Medico> listarTodos() {
        List<Medico> medicos = new ArrayList<>();
        String sql = "SELECT * FROM Medico";

        try (Connection conn = connection.getConexion();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Medico medico = new Medico();
                medico.setId(rs.getInt("id"));
                medico.setNombre(rs.getString("nombre"));
                medico.setApellido(rs.getString("apellido"));
                medico.setEspecialidadId(rs.getInt("especialidad_id"));
                
                Time horarioInicio = rs.getTime("horario_inicio");
                medico.setHorarioInicio(horarioInicio != null ? horarioInicio.toLocalTime() : null);
                
                Time horarioFin = rs.getTime("horario_fin");
                medico.setHorarioFin(horarioFin != null ? horarioFin.toLocalTime() : null);
                
                medico.setTelefono(rs.getString("telefono"));
                medico.setEmail(rs.getString("email"));
                
                Timestamp createdAt = rs.getTimestamp("created_at");
                medico.setCreatedAt(createdAt != null ? createdAt.toLocalDateTime() : null);
                
                Timestamp updatedAt = rs.getTimestamp("updated_at");
                medico.setUpdatedAt(updatedAt != null ? updatedAt.toLocalDateTime() : null);
                
                medicos.add(medico);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar médicos: " + e.getMessage(), e);
        }
        return medicos;
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM Medico WHERE id = ?";

        try (Connection conn = connection.getConexion();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deleting doctor failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar médico: " + e.getMessage(), e);
        }
    }
}