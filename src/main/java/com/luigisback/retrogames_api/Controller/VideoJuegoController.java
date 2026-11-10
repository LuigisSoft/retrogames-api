package com.luigisback.retrogames_api.Controller;

import com.luigisback.retrogames_api.Entity.EstadoVideojuego;
import com.luigisback.retrogames_api.Service.VideojuegoService;
import com.luigisback.retrogames_api.dto.VideojuegoRequest;
import com.luigisback.retrogames_api.dto.VideojuegoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.math.BigDecimal;
import java.util.List;


@Tag(
        name = "Videojuegos",
        description = "Operaciones para gestionar el catálogo de videojuegos"
)
@RestController
@RequestMapping("/api/videojuegos")
public class VideoJuegoController {

    private final VideojuegoService videojuegoService;

    public VideoJuegoController(VideojuegoService videojuegoService) {
        this.videojuegoService = videojuegoService;
    }

    @Operation(
            summary = "Crear un videojuego",
            description = "Registra un nuevo videojuego en el catálogo"
    )
    @ApiResponse(responseCode = "201", description = "Videojuego creado correctamente")
    @ApiResponse(responseCode = "400", description = "Los datos enviados no son válidos")
    @PostMapping
    public ResponseEntity<VideojuegoResponse> crearVideojuegos(
            @Valid @RequestBody VideojuegoRequest request) {

        VideojuegoResponse response = videojuegoService.crearVideojuego(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "Listar videojuegos",
            description = "Obtiene todos los videojuegos del catálogo"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Videojuegos obtenidos correctamente"
    )
    @GetMapping
    public List<VideojuegoResponse> obtenerVideojuegos() {
        return videojuegoService.obtenerVideojuego();
    }

    @GetMapping("/pagina")
    @Operation(
            summary = "Listar videojuegos paginados",
            description = "Obtiene los videojuegos del catálogo mediante paginación y ordenación"
    )
    @ApiResponse(responseCode = "200", description = "Página de videojuegos obtenida correctamente")
    public Page<VideojuegoResponse> obtenerVideojuegosPaginados(
            @ParameterObject Pageable pageable) {

        return videojuegoService.obtenerVideojuegosPaginados(pageable);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener videojuego por ID",
            description = "Busca un videojuego por su identificador"
    )
    @ApiResponse(responseCode = "200", description = "Videojuego encontrado")
    @ApiResponse(responseCode = "404", description = "No se ha encontrado el videojuego")
    public VideojuegoResponse obtenerVideojuegoPorId(@Parameter(description = "Identificador del videojuego")
                                                         @PathVariable Long id) {
        return videojuegoService.obtenerPorId(id);
    }


    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar videojuego",
            description = "Actualiza los datos de un videojuego existente"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Videojuego actualizado correctamente"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Los datos enviados no son válidos"
    )
    @ApiResponse(
            responseCode = "404",
            description = "No se ha encontrado el videojuego"
    )
    public VideojuegoResponse actualizarVideojuego(
            @Parameter(description = "Identificador del videojuego")
            @PathVariable Long id,
            @Valid @RequestBody VideojuegoRequest request) {

        return videojuegoService.actualizarVideojuego(id, request);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar videojuego", description= "Elimina un videojuego del catálogo mediante su identificador."
    )
    @ApiResponse(responseCode= "204", description = "Videojuego eliminado correctamente"
    )
    @ApiResponse(responseCode = "404", description = "No se ha encontrado videojuego")

    public ResponseEntity<Void> eliminarVideojuegos(
            @Parameter(description = "Identificador del videojuego")
            @PathVariable Long id) {
        videojuegoService.eliminarVidejuego(id);
        return ResponseEntity.noContent().build();
    }




    @GetMapping("/buscar")
    @Operation(summary = "Buscar videojuegos por título", description = "Busca videojuegos cuyo título contenga el texto indicado")
    @ApiResponse(responseCode = "200", description = "Búsqueda realizada correctamente")

    public List<VideojuegoResponse> buscarPorTitulo(@Parameter(description="Texto que se utilizará para buscar videojuegos por título")
            @RequestParam String titulo) {
        return videojuegoService.buscarPorTitulo(titulo);
    }

    @GetMapping("/plataforma")
    @Operation(summary = "Buscar videojuegos por plataforma", description = "Busca videojuegos de una plataforma determinada.")
    @ApiResponse(responseCode = "200", description = "Búsqueda realizada correctamente")

    public List<VideojuegoResponse> buscarPorPlataforma(@Parameter(description = "Plataforma que se desea buscar")
                                                            @RequestParam String plataforma) {
        return videojuegoService.buscarPorPlataforma(plataforma);
    }

    @GetMapping("/estado")
    @Operation(summary = "Buscar videojuegos por estado", description = "Busca videojuegos según estado de disponibilidad.")
    @ApiResponse(responseCode = "200", description = "Búsqueda realizada correctamente")
    public List<VideojuegoResponse> buscarPorEstado(@Parameter(description = "Estado del videojuego que se desea buscar")
                                                        @RequestParam EstadoVideojuego estado) {
        return videojuegoService.buscarPorEstado(estado);
    }

    @GetMapping("/precio")
    @Operation(
            summary = "Buscar videojuegos por rango de precio",
            description = "Busca videojuegos cuyo precio esté dentro del rango indicado"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Búsqueda realizada correctamente"
    )
    public List<VideojuegoResponse> buscarPorRangoPrecio(
            @Parameter(description = "Precio mínimo de búsqueda")
            @RequestParam BigDecimal precioMin,

            @Parameter(description = "Precio máximo de búsqueda")
            @RequestParam BigDecimal precioMax) {

        return videojuegoService.buscarPorRangoPrecio(precioMin, precioMax);
    }
}