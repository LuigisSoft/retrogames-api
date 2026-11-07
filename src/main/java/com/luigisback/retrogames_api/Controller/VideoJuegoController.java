package com.luigisback.retrogames_api.Controller;

import com.luigisback.retrogames_api.Service.VideojuegoService;
import com.luigisback.retrogames_api.dto.VideojuegoRequest;
import com.luigisback.retrogames_api.dto.VideojuegoResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videojuegos")
public class VideoJuegoController {

    private final VideojuegoService videojuegoService;

    public VideoJuegoController(VideojuegoService videojuegoService) {
        this.videojuegoService = videojuegoService;
    }

    @PostMapping
    public ResponseEntity<VideojuegoResponse> crearVideojuegos(
            @Valid @RequestBody VideojuegoRequest request) {

        VideojuegoResponse response = videojuegoService.crearVideojuego(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public List<VideojuegoResponse> obtenerVideojuegos() {
        return videojuegoService.obtenerVideojuego();
    }

    @GetMapping("/{id}")
    public VideojuegoResponse obtenerVideojuegoPorId(@PathVariable Long id) {
        return videojuegoService.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public VideojuegoResponse actualizarVideojuego(
            @PathVariable Long id,
            @Valid @RequestBody VideojuegoRequest request) {

        return videojuegoService.actualizarVideojuego(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVideojuegos(@PathVariable Long id) {
        videojuegoService.eliminarVidejuego(id);
        return ResponseEntity.noContent().build();
    }
}