package com.examenJava.domain.repository;

import java.util.List;
import com.examenJava.domain.entities.User;

public interface UserRepository {
    User guardar(User user);
    User buscarPorId(int id);
    List<User> listarTodos();
    User buscarPorNombre(String nombre);
    void actualizar(User user);
    void eliminar(int id);
}