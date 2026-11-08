package com.luigisback.retrogames_api.Repository;


import com.luigisback.retrogames_api.Entity.EstadoVideojuego;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.luigisback.retrogames_api.Entity.Videojuego;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface VideojuegoRepository extends JpaRepository<Videojuego,Long> {

    Page<Videojuego> findAll(Pageable pageable);
    List<Videojuego> findByTituloContainingIgnoreCase(String titulo);

    List<Videojuego> findByPlataformaIgnoreCase(String plataforma);

    List<Videojuego> findByEstado(EstadoVideojuego estado);

    List<Videojuego> findByPrecioBetween(BigDecimal precioMin, BigDecimal precioMax);

}


