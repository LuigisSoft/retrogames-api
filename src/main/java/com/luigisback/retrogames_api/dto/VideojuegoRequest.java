package com.luigisback.retrogames_api.dto;

import com.luigisback.retrogames_api.Entity.EstadoVideojuego;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class VideojuegoRequest {

    @NotBlank(message = "El título es oblitorio")
    String titulo;
    @NotBlank(message = "El título es oblitorio")
    String plataforma;
    @Min(value = 1970, message = "El año debe ser como mínimo 1970")
    @Max(value = 2026, message = "El año debe ser como máximo 2026")
    int year;
    @Positive(message = "El precio debe ser mayor de 0")
    BigDecimal precio;
    @NotNull(message = "El estado es obligatorio")
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
