package com.examenJava.infrastructure.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.examenJava.domain.entities.Paciente;
import com.examenJava.domain.repository.PacienteRepository;
import com.examenJava.infrastructure.database.ConnectionDb;

public class PacienteRepositoryImpl implements PacienteRepository {

    private final ConnectionDb connection;

    public PacienteRepositoryImpl(ConnectionDb connection) {
        this.connection = connection;
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        if (paciente.getTelefono() == null || paciente.getTelefono().trim().isEmpty()) {
            throw new IllegalArgumentException("El número de teléfono es obligatorio");
        }

        if (paciente.getCreatedAt() == null) {
            paciente.setCreatedAt(LocalDateTime.now());
        }
        paciente.setUpdatedAt(LocalDateTime.now());

        String sql = "INSERT INTO Paciente (nombre, apellido, fecha_nacimiento, direccion, telefono, email, created_at, updated_at) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connection.getConexion();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, paciente.getNombre());
            stmt.setString(2, paciente.getApellido());
            stmt.setDate(3, paciente.getFechaNacimiento() != null ? Date.valueOf(paciente.getFechaNacimiento()) : null);
            stmt.setString(4, paciente.getDireccion());
            stmt.setString(5, paciente.getTelefono());
            stmt.setString(6, paciente.getEmail());
            stmt.setTimestamp(7, java.sql.Timestamp.valueOf(paciente.getCreatedAt()));
            stmt.setTimestamp(8, java.sql.Timestamp.valueOf(paciente.getUpdatedAt()));

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating patient failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    paciente.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating patient failed, no ID obtained.");
                }
            }

            return paciente;
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el paciente: " + e.getMessage(), e);
        }
    }

    @Override
    public Paciente buscarPorId(int id) {
        String sql = "SELECT * FROM Paciente WHERE id = ?";

        try (Connection conn = connection.getConexion();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("id"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setApellido(rs.getString("apellido"));
                paciente.setFechaNacimiento(
                        rs.getDate("fecha_nacimiento") != null ? rs.getDate("fecha_nacimiento").toLocalDate() : null);
                paciente.setDireccion(rs.getString("direccion"));
                paciente.setTelefono(rs.getString("telefono"));
                paciente.setEmail(rs.getString("email"));
                paciente.setCreatedAt(
                        rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null);
                paciente.setUpdatedAt(
                        rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null);
                return paciente;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar paciente por ID: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Paciente> listarTodos() {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM Paciente";

        try (Connection conn = connection.getConexion();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("id"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setApellido(rs.getString("apellido"));
                paciente.setFechaNacimiento(
                        rs.getDate("fecha_nacimiento") != null ? rs.getDate("fecha_nacimiento").toLocalDate() : null);
                paciente.setDireccion(rs.getString("direccion"));
                paciente.setTelefono(rs.getString("telefono"));
                paciente.setEmail(rs.getString("email"));
                paciente.setCreatedAt(
                        rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null);
                paciente.setUpdatedAt(
                        rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null);

                pacientes.add(paciente);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar pacientes: " + e.getMessage(), e);
        }
        return pacientes;
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM Paciente WHERE id = ?";

        try (Connection conn = connection.getConexion();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deleting patient failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar paciente: " + e.getMessage(), e);
        }
    }

    @Override
    public void guardarPaciente(String nombre, String apellido, String fechaNacimiento, String email,
            String telefono, String direccion, LocalDate registrationDate) {

        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de teléfono es obligatorio");
        }

        Paciente paciente = new Paciente();
        paciente.setNombre(nombre);
        paciente.setApellido(apellido);
        paciente.setFechaNacimiento(fechaNacimiento != null ? LocalDate.parse(fechaNacimiento) : null);
        paciente.setEmail(email);
        paciente.setTelefono(telefono);
        paciente.setDireccion(direccion);
        paciente.setCreatedAt(LocalDateTime.now());
        paciente.setUpdatedAt(LocalDateTime.now());

        if (paciente.getFechaNacimiento() == null) {
            paciente.setFechaNacimiento(LocalDate.now());
        }

        guardar(paciente);
    }

    @Override
    public void actualizar(Paciente paciente) {
    paciente.setUpdatedAt(LocalDateTime.now());

    String sql = "UPDATE Paciente SET nombre = ?, apellido = ?, fecha_nacimiento = ?, "
               + "direccion = ?, telefono = ?, email = ?, updated_at = ? WHERE id = ?";

    try (Connection conn = connection.getConexion();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, paciente.getNombre());
        stmt.setString(2, paciente.getApellido());
        stmt.setDate(3, paciente.getFechaNacimiento() != null ? Date.valueOf(paciente.getFechaNacimiento()) : null);
        stmt.setString(4, paciente.getDireccion());
        stmt.setString(5, paciente.getTelefono());
        stmt.setString(6, paciente.getEmail());
        stmt.setTimestamp(7, java.sql.Timestamp.valueOf(paciente.getUpdatedAt()));
        stmt.setInt(8, paciente.getId());

        int affectedRows = stmt.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Actualizar paciente falló, ninguna fila afectada.");
        }
    } catch (SQLException e) {
        throw new RuntimeException("Error al actualizar paciente: " + e.getMessage(), e);
    }
}

}