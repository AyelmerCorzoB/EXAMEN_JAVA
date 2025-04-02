CREATE DATABASE IF NOT EXISTS examenJava;
USE examenJava;

CREATE TABLE Especialidad (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE User (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    role ENUM('ADMIN', 'MEDICO', 'PACIENTE') NOT NULL,
    active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_login TIMESTAMP NULL,
    CHECK (LENGTH(nombre) >= 3),
    CHECK (LENGTH(contrasena) >= 8)
);

CREATE TABLE Medico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    especialidad_id INT NOT NULL,
    horario_inicio TIME NOT NULL,
    horario_fin TIME NOT NULL,
    telefono VARCHAR(20),
    email VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (especialidad_id) REFERENCES Especialidad(id)
);

CREATE TABLE Paciente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    direccion VARCHAR(255),
    telefono VARCHAR(20) NOT NULL,
    email VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE Cita (
    id INT AUTO_INCREMENT PRIMARY KEY,
    paciente_id INT NOT NULL,
    medico_id INT NOT NULL,
    fecha_hora DATETIME NOT NULL,
    estado ENUM('programada', 'completada', 'cancelada', 'no_asistio') DEFAULT 'programada',
    motivo TEXT,
    notas TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (paciente_id) REFERENCES Paciente(id),
    FOREIGN KEY (medico_id) REFERENCES Medico(id),
    INDEX (fecha_hora),
    INDEX (estado)
);

-- Tabla de Historial Médico (opcional para ampliar funcionalidad)
CREATE TABLE HistorialMedico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    paciente_id INT NOT NULL,
    medico_id INT NOT NULL,
    cita_id INT,
    fecha DATE NOT NULL,
    diagnostico TEXT,
    tratamiento TEXT,
    observaciones TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (paciente_id) REFERENCES Paciente(id),
    FOREIGN KEY (medico_id) REFERENCES Medico(id),
    FOREIGN KEY (cita_id) REFERENCES Cita(id)
);

-- INSERCIONES

INSERT INTO Especialidad (nombre) VALUES 
('Cardiología'),
('Pediatría'), 
('Dermatología'),
('Oftalmología'),
('Traumatología');

INSERT INTO User (nombre, contrasena, role) VALUES 
('admin', 'admin123', 'ADMIN'),
('medico1', 'medico123', 'MEDICO'),
('medico2', 'medico456', 'MEDICO'), 
('paciente1', 'paciente1', 'PACIENTE'),
('paciente2', 'paciente2', 'PACIENTE');

INSERT INTO Medico (nombre, apellido, especialidad_id, horario_inicio, horario_fin, telefono) VALUES
('Juan', 'Pérez', 1, '08:00:00', '16:00:00', '555-1001'),
('María', 'Gómez', 2, '09:00:00', '17:00:00', '555-1002'),
('Carlos', 'López', 3, '10:00:00', '18:00:00', '555-1003');

INSERT INTO Paciente (nombre, apellido, fecha_nacimiento, telefono) VALUES
('Ana', 'Martínez', '1985-05-15', '555-2001'),
('Luis', 'Rodríguez', '1990-08-22', '555-2002'),
('Sofía', 'Hernández', '1978-03-10', '555-2003');

INSERT INTO Cita (paciente_id, medico_id, fecha_hora, estado) VALUES
(1, 1, '2023-11-15 10:00:00', 'programada'),
(2, 2, '2023-11-16 11:00:00', 'programada'),
(3, 3, '2023-11-17 15:30:00', 'completada');

INSERT INTO HistorialMedico (paciente_id, medico_id, cita_id, fecha, diagnostico) VALUES
(3, 3, 3, '2023-11-17', 'Control rutinario sin novedades');
