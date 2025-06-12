package com.example.lab09.beans;

import java.io.Serializable;

public class Usuario implements Serializable {
    private int idusuarios;
    private String nombre;
    private String apellido;
    private String pass;
    private String dni;
    private String correo;


    public Usuario(int idusuarios, String nombre, String apellido, String pass, String dni, String correo) {
        this.idusuarios = idusuarios;
        this.nombre = nombre;
        this.apellido = apellido;
        this.pass = pass;
        this.dni = dni;
        this.correo = correo;
    }

    public int getIdusuarios() {
        return idusuarios;
    }

    public void setIdusuarios(int idusuarios) {
        this.idusuarios = idusuarios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}