package com.luigisback.retrogames_api.dto;

import com.luigisback.retrogames_api.Entity.EstadoVideojuego;

import java.math.BigDecimal;

public class VideojuegoRequest {


    String titulo;
    String plataforma;
    int year;
    BigDecimal precio;
    EstadoVideojuego estado;

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public EstadoVideojuego getEstado() {
        return estado;
    }

    public void setEstado(EstadoVideojuego estado) {
        this.estado = estado;
    }
}
