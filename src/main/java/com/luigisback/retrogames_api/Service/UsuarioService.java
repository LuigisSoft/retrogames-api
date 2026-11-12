
package com.luigisback.retrogames_api.Service;

import com.luigisback.retrogames_api.Entity.Usuario;
import com.luigisback.retrogames_api.Exception.UsuarioNotFoundException;
import com.luigisback.retrogames_api.Repository.UsuarioRepository;
import com.luigisback.retrogames_api.dto.UsuarioRequest;
import com.luigisback.retrogames_api.dto.UsuarioResponse;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // MÉTODO PRIVADO: CONVERTIR USUARIO EN USUARIORESPONSE
    private UsuarioResponse convertirAResponse(Usuario usuario) {

        UsuarioResponse response = new UsuarioResponse();

        response.setId(usuario.getId());
        response.setNombre(usuario.getNombre());
        response.setApellidos(usuario.getApellidos());
        response.setCorreo(usuario.getCorreo());
        response.setDireccion(usuario.getDireccion());

        return response;
    }

    // CREAR USUARIO
    public UsuarioResponse crearUsuario(UsuarioRequest request) {

        Usuario usuario = new Usuario();

        usuario.setNombre(request.getNombre());
        usuario.setApellidos(request.getApellidos());
        usuario.setDireccion(request.getDireccion());
        usuario.setCorreo(request.getCorreo());

        Usuario guardado = usuarioRepository.save(usuario);

        return convertirAResponse(guardado);
    }

    // ACTUALIZAR USUARIO
    public UsuarioResponse actualizarUsuario(Long id, UsuarioRequest request) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new UsuarioNotFoundException("Usuario no encontrado"));

        usuario.setNombre(request.getNombre());
        usuario.setApellidos(request.getApellidos());
        usuario.setCorreo(request.getCorreo());
        usuario.setDireccion(request.getDireccion());

        Usuario actualizado = usuarioRepository.save(usuario);

        return convertirAResponse(actualizado);
    }

    // ELIMINAR USUARIO
    public void eliminarUsuario(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new UsuarioNotFoundException("Usuario no encontrado"));

        usuarioRepository.delete(usuario);
    }

    // OBTENER TODOS LOS USUARIOS
    public List<UsuarioResponse> getAll() {

        return usuarioRepository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    // OBTENER USUARIO POR ID
    public UsuarioResponse getById(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new UsuarioNotFoundException("Usuario no encontrado"));

        return convertirAResponse(usuario);
    }
}
