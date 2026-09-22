package com.luigisback.retrogames_api.Controller;


import com.luigisback.retrogames_api.Entity.Videojuego;
import com.luigisback.retrogames_api.Service.VideojuegoService;
import com.luigisback.retrogames_api.dto.VideojuegoRequest;
import com.luigisback.retrogames_api.dto.VideojuegoResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/videojuegos")
public class VideoJuegoController {

    private final VideojuegoService videojuegoService;

    public VideoJuegoController(VideojuegoService videojuegoService) {
        this.videojuegoService = videojuegoService;
    }

    @GetMapping
    public String Videojuego() {
        return "Hola, soy la api";
    }
    @PostMapping

    public VideojuegoResponse crearVideojuego(@RequestBody VideojuegoRequest request){
        return  videojuegoService.crearVideojuego(request);
    }




}
