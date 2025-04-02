package com.examenJava.infrastructure.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.examenJava.domain.entities.Especialidad;
import com.examenJava.domain.repository.EspecialidadRepository;
import com.examenJava.infrastructure.database.ConnectionDb;

public class EspecialidadRepositoryImpl implements EspecialidadRepository {

    private final ConnectionDb connection;

    public EspecialidadRepositoryImpl(ConnectionDb connection) {
        this.connection = connection;
    }

    @Override
    public Especialidad guardar(Especialidad especialidad) {
        if (especialidad.getNombre() == null || especialidad.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la especialidad es obligatorio");
        }

        if (especialidad.getCreatedAt() == null) {
            especialidad.setCreatedAt(LocalDateTime.now());
        }
        especialidad.setUpdatedAt(LocalDateTime.now());

        String sql = "INSERT INTO Especialidad (nombre, descripcion, created_at, updated_at) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = connection.getConexion();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, especialidad.getNombre());
            stmt.setString(2, especialidad.getDescripcion());
            stmt.setTimestamp(3, Timestamp.valueOf(especialidad.getCreatedAt()));
            stmt.setTimestamp(4, Timestamp.valueOf(especialidad.getUpdatedAt()));

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating specialty failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    especialidad.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating specialty failed, no ID obtained.");
                }
            }
            
            return especialidad;
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la especialidad: " + e.getMessage(), e);
        }
    }

    @Override
    public Especialidad buscarPorId(int id) {
        String sql = "SELECT * FROM Especialidad WHERE id = ?";

        try (Connection conn = connection.getConexion();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Especialidad especialidad = new Especialidad();
                especialidad.setId(rs.getInt("id"));
                especialidad.setNombre(rs.getString("nombre"));
                especialidad.setDescripcion(rs.getString("descripcion"));
                
                Timestamp createdAt = rs.getTimestamp("created_at");
                especialidad.setCreatedAt(createdAt != null ? createdAt.toLocalDateTime() : null);
                
                Timestamp updatedAt = rs.getTimestamp("updated_at");
                especialidad.setUpdatedAt(updatedAt != null ? updatedAt.toLocalDateTime() : null);
                
                return especialidad;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar especialidad por ID: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Especialidad> listarTodos() {
        List<Especialidad> especialidades = new ArrayList<>();
        String sql = "SELECT * FROM Especialidad";

        try (Connection conn = connection.getConexion();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Especialidad especialidad = new Especialidad();
                especialidad.setId(rs.getInt("id"));
                especialidad.setNombre(rs.getString("nombre"));
                especialidad.setDescripcion(rs.getString("descripcion"));
                
                Timestamp createdAt = rs.getTimestamp("created_at");
                especialidad.setCreatedAt(createdAt != null ? createdAt.toLocalDateTime() : null);
                
                Timestamp updatedAt = rs.getTimestamp("updated_at");
                especialidad.setUpdatedAt(updatedAt != null ? updatedAt.toLocalDateTime() : null);
                
                especialidades.add(especialidad);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar especialidades: " + e.getMessage(), e);
        }
        return especialidades;
    }

    @Override
    public void eliminar(int id) {
        
        String checkSql = "SELECT COUNT(*) FROM Medico WHERE especialidad_id = ?";
        String deleteSql = "DELETE FROM Especialidad WHERE id = ?";

        try (Connection conn = connection.getConexion();
                PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {

            
            checkStmt.setInt(1, id);
            ResultSet rs = checkStmt.executeQuery();
            
            if (rs.next() && rs.getInt(1) > 0) {
                throw new SQLException("No se puede eliminar la especialidad porque tiene médicos asociados");
            }

            deleteStmt.setInt(1, id);
            int affectedRows = deleteStmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deleting specialty failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar especialidad: " + e.getMessage(), e);
        }
    }
}