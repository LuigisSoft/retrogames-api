package com.luigisback.retrogames_api.Controller;

import com.luigisback.retrogames_api.Entity.EstadoVideojuego;
import com.luigisback.retrogames_api.Service.VideojuegoService;
import com.luigisback.retrogames_api.dto.VideojuegoRequest;
import com.luigisback.retrogames_api.dto.VideojuegoResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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

    @GetMapping("/pagina")
    public Page<VideojuegoResponse> obtenerVideojuegosPaginados(Pageable pageable) {
        return videojuegoService.obtenerVideojuegosPaginados(pageable);
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

    @GetMapping("/buscar")
    public List<VideojuegoResponse> buscarPorTitulo(@RequestParam String titulo) {
        return videojuegoService.buscarPorTitulo(titulo);
    }

    @GetMapping("/plataforma")
    public List<VideojuegoResponse> buscarPorPlataforma(@RequestParam String plataforma) {
        return videojuegoService.buscarPorPlataforma(plataforma);
    }

    @GetMapping("/estado")
    public List<VideojuegoResponse> buscarPorEstado(@RequestParam EstadoVideojuego estado) {
        return videojuegoService.buscarPorEstado(estado);
    }

    @GetMapping("/precio")
    public List<VideojuegoResponse> buscarPorRangoPrecio(
            @RequestParam BigDecimal precioMin,
            @RequestParam BigDecimal precioMax) {

        return videojuegoService.buscarPorRangoPrecio(precioMin, precioMax);
    }
}