package com.luigisback.retrogames_api.Entity;


import com.luigisback.retrogames_api.Controller.VideoJuegoController;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class Videojuego {

    @Id
    @GeneratedValue
    private long id;
    private String titulo;
    private String plataforma;
    public int year;
    private BigDecimal precio;
    private EstadoVideojuego estado;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
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
