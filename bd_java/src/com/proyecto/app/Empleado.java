// Empleado.java
package com.proyecto.app;

public class Empleado {
    // Atributos de la clase Empleado
    private int id;
    private int cedula;
    private String nombre;
    private byte[] foto; // Representación de la imagen como arreglo de bytes
    private java.sql.Date fechaIngreso;
    private int idCargo;

    // Constructor
    public Empleado(int cedula, String nombre, byte[] foto, java.sql.Date fechaIngreso, int idCargo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.foto = foto;
        this.fechaIngreso = fechaIngreso;
        this.idCargo = idCargo;
    }

    // Métodos getter y setter (puedes generarlos automáticamente en muchos entornos de desarrollo)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public java.sql.Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(java.sql.Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public int getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    // Otros métodos, si es necesario
}
