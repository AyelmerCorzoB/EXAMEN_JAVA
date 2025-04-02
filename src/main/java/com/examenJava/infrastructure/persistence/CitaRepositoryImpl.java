package com.examenJava.infrastructure.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale.Category;
import java.util.Map;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    }

    @Override
    public Cita buscarPorId(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorId'");
    }

    @Override
    public List<Cita> listarTodos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarTodos'");
    }

    @Override
    public void eliminar(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }

    
}