package com.luigisback.retrogames_api.Service;

import com.luigisback.retrogames_api.Entity.Videojuego;
import com.luigisback.retrogames_api.Repository.VideojuegoRepository;
import com.luigisback.retrogames_api.dto.VideojuegoRequest;
import com.luigisback.retrogames_api.dto.VideojuegoResponse;
import org.springframework.stereotype.Service;


@Service
public class VideojuegoService {

    private final VideojuegoRepository videojuegoRepository;

    public VideojuegoService(VideojuegoRepository videojuegoRepository) {
        this.videojuegoRepository = videojuegoRepository;
    }



    public VideojuegoResponse crearVideojuego(VideojuegoRequest request){

        Videojuego videojuego=new Videojuego();

        videojuego.setTitulo(request.getTitulo());
        videojuego.setPlataforma(request.getPlataforma());
        videojuego.setYear(request.getYear());
        videojuego.setEstado(request.getEstado());
        videojuego.setPrecio(request.getPrecio());

        Videojuego guardado=videojuegoRepository.save(videojuego);
        VideojuegoResponse response = new VideojuegoResponse();

        response.setId(guardado.getId());
        response.setTitulo(guardado.getTitulo());
        response.setPlataforma(guardado.getPlataforma());
        response.setYear(guardado.getYear());
        response.setPrecio(guardado.getPrecio());
        response.setEstado(guardado.getEstado());

        return response;


    }
}
