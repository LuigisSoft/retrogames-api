package com.luigisback.retrogames_api.Service;

import com.luigisback.retrogames_api.Entity.Usuario;
import com.luigisback.retrogames_api.Exception.UsuarioNotFoundException;
import com.luigisback.retrogames_api.Repository.UsuarioRepository;
import com.luigisback.retrogames_api.dto.UsuarioRequest;
import com.luigisback.retrogames_api.dto.UsuarioResponse;
import java.util.List;

import org.springframework.stereotype.Service;



@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponse actualizarUsuario(Long id, UsuarioRequest request){
        Usuario usuario=usuarioRepository.findById(id)
                .orElseThrow(()->new UsuarioNotFoundException("Uusuario no encontrado"));

        usuario.setNombre(request.getNombre());
        usuario.setApellidos(request.getApellidos());
        usuario.setCorreo(request.getCorreo());
        usuario.setDireccion(request.getDireccion());

        Usuario actualizado=usuarioRepository.save(usuario);
        UsuarioResponse response=new UsuarioResponse();

        response.setId(actualizado.getId());
        response.setNombre(actualizado.getNombre());
        response.setApellidos(actualizado.getApellidos());
        response.setDireccion(actualizado.getDireccion());
        response.setCorreo(actualizado.getCorreo());

        return response;

    }

    public UsuarioResponse crearUsuario(UsuarioRequest request) {

        Usuario usuario = new Usuario();

        usuario.setNombre(request.getNombre());
        usuario.setApellidos(request.getApellidos());
        usuario.setDireccion(request.getDireccion());
        usuario.setCorreo(request.getCorreo());

        Usuario guardado = usuarioRepository.save(usuario);

        UsuarioResponse response = new UsuarioResponse();

        response.setId(guardado.getId());
        response.setNombre(guardado.getNombre());
        response.setApellidos(guardado.getApellidos());
        response.setDireccion(guardado.getDireccion());
        response.setCorreo(guardado.getCorreo());

        return response;


    }

    public void  eliminarUsuario(Long id){
        Usuario usuario=usuarioRepository.findById(id)
                .orElseThrow(()->new UsuarioNotFoundException("Usuario no encontrado"));

        usuarioRepository.delete(usuario);

    }


    public List<UsuarioResponse> getAll() {

        return usuarioRepository.findAll()
                .stream()
                .map(usuario -> {
                    UsuarioResponse response = new UsuarioResponse();

                    response.setId(usuario.getId());
                    response.setNombre(usuario.getNombre());
                    response.setApellidos(usuario.getApellidos());
                    response.setCorreo(usuario.getCorreo());
                    response.setDireccion(usuario.getDireccion());

                    return response;
                })
                .toList();
    }

    public UsuarioResponse getById(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                                new UsuarioNotFoundException("Usuario no encontrado"));
        UsuarioResponse response = new UsuarioResponse();

        response.setId(usuario.getId());
        response.setNombre(usuario.getNombre());
        response.setApellidos(usuario.getApellidos());
        response.setCorreo(usuario.getCorreo());
        response.setDireccion(usuario.getDireccion());

        return response;
    }
}
