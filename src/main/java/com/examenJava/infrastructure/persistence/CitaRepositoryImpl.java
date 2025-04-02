package com.examenJava.infrastructure.persistence;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.examenJava.domain.entities.Cita;
import com.examenJava.domain.repository.CitaRepository;
import com.examenJava.infrastructure.database.ConnectionDb;

public class CitaRepositoryImpl implements CitaRepository {

    private final ConnectionDb connection;

    public CitaRepositoryImpl(ConnectionDb connection) {
        this.connection = connection;
    }

    @Override
    public Cita guardar(Cita cita) {
        String sql = "INSERT INTO Cita (paciente_id, medico_id, fecha_hora, estado, motivo, notas, created_at, updated_at) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, cita.getPacienteId());
            stmt.setInt(2, cita.getMedicoId());
            stmt.setTimestamp(3, Timestamp.valueOf(cita.getFechaHora()));
            stmt.setString(4, cita.getEstado());
            stmt.setString(5, cita.getMotivo());
            stmt.setString(6, cita.getNotas());
            stmt.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                cita.setId(rs.getInt(1));
            }

            return cita;
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar cita: " + e.getMessage());
        }
    }

    @Override
    public Cita buscarPorId(int id) {
        String sql = "SELECT * FROM Cita WHERE id = ?";
        
        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Cita cita = new Cita();
                cita.setId(rs.getInt("id"));
                cita.setPacienteId(rs.getInt("paciente_id"));
                cita.setMedicoId(rs.getInt("medico_id"));
                cita.setFechaHora(rs.getTimestamp("fecha_hora").toLocalDateTime());
                cita.setEstado(rs.getString("estado"));
                cita.setMotivo(rs.getString("motivo"));
                cita.setNotas(rs.getString("notas"));
                return cita;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar cita: " + e.getMessage());
        }
    }

    @Override
    public List<Cita> listarTodos() {
        List<Cita> citas = new ArrayList<>();
        String sql = "SELECT * FROM Cita";

        try (Connection conn = connection.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cita cita = new Cita();
                cita.setId(rs.getInt("id"));
                cita.setPacienteId(rs.getInt("paciente_id"));
                cita.setMedicoId(rs.getInt("medico_id"));
                cita.setFechaHora(rs.getTimestamp("fecha_hora").toLocalDateTime());
                cita.setEstado(rs.getString("estado"));
                citas.add(cita);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar citas: " + e.getMessage());
        }
        return citas;
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM Cita WHERE id = ?";

        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar cita: " + e.getMessage());
        }
    }
}