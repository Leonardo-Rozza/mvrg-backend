package com.leonardorozza.mvgrreparacionesbackend.controller;


import com.leonardorozza.mvgrreparacionesbackend.service.dto.AuthResponseDto;
import com.leonardorozza.mvgrreparacionesbackend.service.dto.LoginRequestDto;
import com.leonardorozza.mvgrreparacionesbackend.service.impl.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Login", description = "Inicio de sesion")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto request) {

        String token = authService.login(request.username(), request.password());

        return ResponseEntity.ok(
                new AuthResponseDto(
                        token,
                        "Bearer",
                        request.username()
                )
        );
    }
}

