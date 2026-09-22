package com.luigisback.retrogames_api.Repository;

import com.luigisback.retrogames_api.Entity.Videojuego;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideojuegoRepository extends JpaRepository<Videojuego,Long> {
}
