package com.luigisback.retrogames_api.Controller;


import com.luigisback.retrogames_api.Entity.Videojuego;
import com.luigisback.retrogames_api.Service.VideojuegoService;
import com.luigisback.retrogames_api.dto.VideojuegoRequest;
import com.luigisback.retrogames_api.dto.VideojuegoResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videojuegos")
public class VideoJuegoController {

    private final VideojuegoService videojuegoService;

    public VideoJuegoController(VideojuegoService videojuegoService) {
        this.videojuegoService = videojuegoService;
    }


    //creo los videojuegos
    @PostMapping
    public VideojuegoResponse crearVideojuegos(@RequestBody VideojuegoRequest request) {
        return videojuegoService.crearVideojuego(request);
    }

    //obtengo todos los videjuegos
    @GetMapping
    public List<VideojuegoResponse> obtenerVideojuegos() {
        return videojuegoService.obtenerVideojuego();
    }

    @PutMapping("/{id}")
    public VideojuegoResponse actualizarVideojuego(@PathVariable Long id, @RequestBody VideojuegoRequest request) {
        return videojuegoService.actualizarVideojuego(id, request);
    }

    @DeleteMapping("/{id}")
    public void eliminarVideojuegos(@PathVariable Long id) {
        videojuegoService.eliminarVidejuego(id);

    }
}



