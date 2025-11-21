package com.leonardorozza.mvgrreparacionesbackend.controller;


import com.leonardorozza.mvgrreparacionesbackend.service.ClienteService;
import com.leonardorozza.mvgrreparacionesbackend.service.dto.cliente.ClienteRequestDTO;
import com.leonardorozza.mvgrreparacionesbackend.service.dto.cliente.ClienteResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
@Tag(name = "Clientes", description = "Gestión de clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Operation(summary = "Crear un cliente")
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> crear(@Valid @RequestBody ClienteRequestDTO request) {
        return ResponseEntity.ok(clienteService.crear(request));
    }

    @Operation(summary = "Actualizar un cliente")
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ClienteRequestDTO request
    ) {
        return ResponseEntity.ok(clienteService.actualizar(id, request));
    }

    @Operation(summary = "Obtener cliente por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.obtenerPorId(id));
    }

    @Operation(summary = "Listar todos los clientes")
    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listar() {
        return ResponseEntity.ok(clienteService.listar());
    }

    @Operation(summary = "Eliminar un cliente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        clienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

