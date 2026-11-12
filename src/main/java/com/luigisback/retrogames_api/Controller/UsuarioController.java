
package com.luigisback.retrogames_api.Controller;

import com.luigisback.retrogames_api.Service.UsuarioService;
import com.luigisback.retrogames_api.dto.UsuarioRequest;
import com.luigisback.retrogames_api.dto.UsuarioResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Usuarios",
        description = "Operaciones para gestionar los usuarios"
)
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // CREAR USUARIO
    @Operation(
            summary = "Crear usuario",
            description = "Registra un nuevo usuario en la aplicación"
    )
    @ApiResponse(responseCode = "201", description = "Usuario creado correctamente")
    @ApiResponse(responseCode = "400", description = "Los datos enviados no son válidos")
    @PostMapping
    public ResponseEntity<UsuarioResponse> crearUsuario(
            @Valid @RequestBody UsuarioRequest request) {

        UsuarioResponse response = usuarioService.crearUsuario(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ACTUALIZAR USUARIO
    @Operation(
            summary = "Actualizar usuario",
            description = "Actualiza los datos de un usuario existente"
    )
    @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente")
    @ApiResponse(responseCode = "400", description = "Los datos enviados no son válidos")
    @ApiResponse(responseCode = "404", description = "No se ha encontrado el usuario")
    @PutMapping("/{id}")
    public UsuarioResponse actualizarUsuario(
            @Parameter(description = "Identificador del usuario")
            @PathVariable Long id,
            @Valid @RequestBody UsuarioRequest request) {

        return usuarioService.actualizarUsuario(id, request);
    }

    // ELIMINAR USUARIO
    @Operation(
            summary = "Eliminar usuario",
            description = "Elimina un usuario mediante su identificador"
    )
    @ApiResponse(responseCode = "204", description = "Usuario eliminado correctamente")
    @ApiResponse(responseCode = "404", description = "No se ha encontrado el usuario")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(
            @Parameter(description = "Identificador del usuario")
            @PathVariable Long id) {

        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    // LISTAR USUARIOS
    @Operation(
            summary = "Listar usuarios",
            description = "Obtiene todos los usuarios registrados en la aplicación"
    )
    @ApiResponse(responseCode = "200", description = "Usuarios obtenidos correctamente")
    @GetMapping
    public List<UsuarioResponse> getAll() {
        return usuarioService.getAll();
    }

    // OBTENER USUARIO POR ID
    @Operation(
            summary = "Obtener usuario por ID",
            description = "Busca un usuario mediante su identificador"
    )
    @ApiResponse(responseCode = "200", description = "Usuario encontrado")
    @ApiResponse(responseCode = "404", description = "No se ha encontrado el usuario")
    @GetMapping("/{id}")
    public UsuarioResponse obtenerPorId(
            @Parameter(description = "Identificador del usuario")
            @PathVariable Long id) {

        return usuarioService.getById(id);
    }
}
