package com.luigisback.retrogames_api.Repository;

import com.luigisback.retrogames_api.Entity.Usuario;
import com.luigisback.retrogames_api.Entity.Videojuego;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
