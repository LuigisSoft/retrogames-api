package com.luigisback.retrogames_api.Repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.luigisback.retrogames_api.Entity.Videojuego;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideojuegoRepository extends JpaRepository<Videojuego,Long> {

    Page<Videojuego> findAll(Pageable pageable);
    List<Videojuego> findByTituloContainingIgnoreCase(String titulo);

}
