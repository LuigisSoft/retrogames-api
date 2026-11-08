package com.luigisback.retrogames_api.Service;

import com.luigisback.retrogames_api.Entity.EstadoVideojuego;
import com.luigisback.retrogames_api.Entity.Videojuego;
import com.luigisback.retrogames_api.Exception.VideojuegoNotFoundException;
import com.luigisback.retrogames_api.Repository.VideojuegoRepository;
import com.luigisback.retrogames_api.dto.VideojuegoRequest;
import com.luigisback.retrogames_api.dto.VideojuegoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;



@Service
public class VideojuegoService {

    private final VideojuegoRepository videojuegoRepository;

    public VideojuegoService(VideojuegoRepository videojuegoRepository) {
        this.videojuegoRepository = videojuegoRepository;
    }


     public List <VideojuegoResponse>obtenerVideojuego(){
        return videojuegoRepository.findAll().stream()
                .map( videojuego->{
                    VideojuegoResponse response= new VideojuegoResponse();
                    response.setId(videojuego.getId());
                    response.setTitulo(videojuego.getTitulo());
                    response.setPrecio(videojuego.getPrecio());
                    response.setPlataforma(videojuego.getPlataforma());
                    response.setEstado(videojuego.getEstado());
                    response.setYear(videojuego.getYear());

                    return response;
                })
                .collect(Collectors.toList());
    }

    public VideojuegoResponse obtenerPorId(Long id) {

        Videojuego videojuego = videojuegoRepository.findById(id)
                .orElseThrow(() -> new VideojuegoNotFoundException(
                        "Videojuego no encontrado"
                ));

        VideojuegoResponse response = new VideojuegoResponse();

        response.setId(videojuego.getId());
        response.setTitulo(videojuego.getTitulo());
        response.setPlataforma(videojuego.getPlataforma());
        response.setYear(videojuego.getYear());
        response.setPrecio(videojuego.getPrecio());
        response.setEstado(videojuego.getEstado());

        return response;
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

    public VideojuegoResponse actualizarVideojuego(Long id, VideojuegoRequest request){
        Videojuego videojuego=videojuegoRepository.findById(id)
                .orElseThrow(()->new VideojuegoNotFoundException("Videojuego no encontrado"));

        videojuego.setTitulo(request.getTitulo());
        videojuego.setPlataforma(request.getPlataforma());
        videojuego.setYear(request.getYear());
        videojuego.setEstado(request.getEstado());
        videojuego.setPrecio(request.getPrecio());

        Videojuego actualizado=videojuegoRepository.save(videojuego);
        VideojuegoResponse response=new VideojuegoResponse();

        response.setId(actualizado.getId());
        response.setTitulo(actualizado.getTitulo());
        response.setPlataforma(actualizado.getPlataforma());
        response.setYear(actualizado.getYear());
        response.setPrecio(actualizado.getPrecio());
        response.setEstado(actualizado.getEstado());

        return response;


    }

    public void  eliminarVidejuego(Long id){
        Videojuego videojuego=videojuegoRepository.findById(id)
                .orElseThrow(()->new VideojuegoNotFoundException("Videojuego no encontrado"));

        videojuegoRepository.delete(videojuego);

    }

    private VideojuegoResponse convertirAResponse(Videojuego videojuego) {

        VideojuegoResponse response = new VideojuegoResponse();

        response.setId(videojuego.getId());
        response.setTitulo(videojuego.getTitulo());
        response.setPrecio(videojuego.getPrecio());
        response.setPlataforma(videojuego.getPlataforma());
        response.setEstado(videojuego.getEstado());
        response.setYear(videojuego.getYear());

        return response;
    }

    public Page<VideojuegoResponse> obtenerVideojuegosPaginados(Pageable pageable) {

        return videojuegoRepository.findAll(pageable)
                .map(this::convertirAResponse);
    }

    public List<VideojuegoResponse> buscarPorTitulo(String titulo) {
        return videojuegoRepository.findByTituloContainingIgnoreCase(titulo)
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    public List<VideojuegoResponse> buscarPorPlataforma(String plataforma) {
        return videojuegoRepository.findByPlataformaIgnoreCase(plataforma)
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    public List<VideojuegoResponse> buscarPorEstado(EstadoVideojuego estado) {
        return videojuegoRepository.findByEstado(estado)
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    public List<VideojuegoResponse> buscarPorRangoPrecio(BigDecimal precioMin, BigDecimal precioMax) {
        return videojuegoRepository.findByPrecioBetween(precioMin, precioMax)
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }



}
