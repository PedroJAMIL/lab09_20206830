package com.example.lab09.beans;

import java.io.Serializable;
import java.sql.Date;

public class Transaccion implements Serializable {
    private int idtransacciones;
    private double monto;
    private String descripcion;
    private String titulo;
    private Date fecha;
    private int usuarios_idusuarios;
    private String tipo;


    public Transaccion(int idtransacciones, double monto, String descripcion, String titulo, Date fecha, int usuarios_idusuarios, String tipo) {
        this.idtransacciones = idtransacciones;
        this.monto = monto;
        this.descripcion = descripcion;
        this.titulo = titulo;
        this.fecha = fecha;
        this.usuarios_idusuarios = usuarios_idusuarios;
        this.tipo = tipo;
    }

    // Getters and Setters

    public int getIdtransacciones() {
        return idtransacciones;
    }

    public void setIdtransacciones(int idtransacciones) {
        this.idtransacciones = idtransacciones;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getUsuarios_idusuarios() {
        return usuarios_idusuarios;
    }

    public void setUsuarios_idusuarios(int usuarios_idusuarios) {
        this.usuarios_idusuarios = usuarios_idusuarios;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}