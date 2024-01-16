// EmpleadoDAO.java
package com.proyecto.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmpleadoDAO {

    // Métodos CRUD y utilidades aquí...

    public void insertarEmpleado(Empleado empleado) {
        // Código para insertar empleado en la base de datos
        // ...
    }

    // Otros métodos CRUD y utilidades aquí...

    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = ConexionBD.obtenerConexion();
            connection.setAutoCommit(false); // Deshabilitar el modo de auto-commit

            EmpleadoDAO empleadoDAO = new EmpleadoDAO();
            // Crear un objeto de prueba Empleado
            Empleado empleadoPrueba = new Empleado();
            empleadoPrueba.setCedula(123456789);
            empleadoPrueba.setNombre("John Doe");
            // Asegúrate de tener la imagen de la cara convertida a bytes para setearla
            // empleadoPrueba.setFoto(fotoEnBytes);
            empleadoPrueba.setFechaIngreso(java.sql.Date.valueOf("2024-01-12"));
            empleadoPrueba.setIdCargo(1); // IdCargo según la lista de cargos

            // Insertar el empleado de prueba
            empleadoDAO.insertarEmpleado(empleadoPrueba);

            connection.commit(); // Confirmar la transacción si todo ha ido bien
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback(); // Deshacer la transacción en caso de error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true); // Restaurar el modo de auto-commit
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
