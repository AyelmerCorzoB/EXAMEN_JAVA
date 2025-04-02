package com.examenJava.domain.service;

import com.examenJava.domain.entities.User;
import com.examenJava.domain.repository.UserRepository;

import java.sql.Timestamp;
import java.util.List;

public class UserService {
    private final UserRepository userRepository;
    private User currentUser;

    public User getCurrentUser() {
        return this.currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User authenticate(String nombre, String password) {
        User user = userRepository.buscarPorNombre(nombre);
        
        if (user == null || !user.getPassword().equals(password)) {
            return null;
        }
    
        if (!user.isActive()) {
            System.err.println("Cuenta desactivada");
            return null;
        }
    
        user.setLast_login(new Timestamp(System.currentTimeMillis()));
        userRepository.actualizar(user);
        this.currentUser = user;
        return user;
    }

    public User register(User user, boolean isAdmin) {
        try {

            if (user.getnombre() == null || user.getnombre().isEmpty() ||
                    user.getPassword() == null || user.getPassword().isEmpty()) {
                System.err.println("Error: nombre y password son obligatorios");
                return null;
            }

            if (userRepository.buscarPorNombre(user.getnombre()) != null) {
                System.err.println("Error: El nombre de usuario '" + user.getnombre() + "' ya está registrado");
                return null;
            }

            if(!isAdmin) {
                user.setRole("CONSUMER"); 
            }

            return userRepository.guardar(user);

        } catch (Exception e) {
            System.err.println("Error técnico al registrar usuario: " + e.getMessage());
            return null;
        }
    }

    public List<User> getAllUsers() {
        try {
            return userRepository.listarTodos();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la lista de usuarios: " + e.getMessage(), e);
        }
    }

    public List<User> getUsersByRole(String role) {
        try {
            return userRepository.listarTodos().stream()
                    .filter(user -> user.getRole().equalsIgnoreCase(role))
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener usuarios por rol: " + e.getMessage(), e);
        }
    }

    public User getUserById(int id) {
        try {
            return userRepository.buscarPorId(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al Search usuario por ID: " + e.getMessage(), e);
        }
    }

    public void updateUser(User user) {
        try {
            if (userRepository.buscarPorId(user.getId()) == null) {
                throw new IllegalArgumentException("El usuario no existe");
            }
            userRepository.actualizar(user);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar usuario: " + e.getMessage(), e);
        }
    }

    public void toggleUserStatus(int userId, boolean active) {
        try {
            User user = userRepository.buscarPorId(userId);
            if (user == null) {
                throw new IllegalArgumentException("Usuario no encontrado");
            }
            user.setActive(active);
            userRepository.actualizar(user);
        } catch (Exception e) {
            throw new RuntimeException("Error al cambiar estado de usuario: " + e.getMessage(), e);
        }
    }
}